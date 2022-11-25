// 필요한 모듈 가져오기
const express = require("express");
const morgan = require('morgan');
const compression = require('compression')
const path = require('path');
const mysql = require('mysql');
const cookieParser = require('cookie-parser');
const session = require("express-session");
const multer = require('multer');
const dotenv = require('dotenv');

// .env 설정파일의 내용 가져오기
dotenv.config();

// 서버 설정하기
const app = express();
app.set("port", process.env.PORT || 9000);

// 로그를 매일 기록하기 위한 설정
let FileStreamRotator = require("file-stream-rotator");
let fs = require("fs");
// 로그를 기록할 디렉토리 경로 생성
let logDir = path.join(__dirname, "log");
// 디렉토리 유무 확인후 없다면 디렉토리 생성
fs.existsSync(logDir) || fs.mkdirSync(logDir);
// 로그파일 옵션을 설정
let accessLogStream = FileStreamRotator.getStream({
    date_format: "YYYY-MM-DD",
    filename: path.join(logDir, "access-%DATE%.log"),
    frequency: "daily",
    verbose: false
});
// 로그 기록 설정
app.use(morgan("combined", {stream:accessLogStream}));

// 데이터를 압축해서 전송하는 옵션 설정
app.use(compression());

// POST 방식의 파라미터 읽을 수 있도록 설정
// POST 방식은 서버에서 파라미터 인코딩 설정을 반드시 해줘야 한다.
// GET은 URL의 데이터를 받아오지만 POST 방식은 본문에 받아오기 때문에 애플리케이션 서버가 처리해야 한다.
let bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

// 세션을 데이터베이스에 저장하는 작업
// 데이터베이스 접속정보
let options = {
    host: process.env.HOST,
    port: process.env.MYSQLPORT,
    user: process.env.USER,
    password: process.env.PASSWORD,
    database: process.env.DATABASE
}
// 세션을 저장하기 위한 mysql 데이터베이스 저장소 생성
const maridDBStore = require('express-mysql-session')(session);
// 세선 셜정
app.use(
    session({
        secret: process.env.COOKIE_SECRET,
        resave: false,
        saveUninitialized: true,
        store: new maridDBStore(options)
    })
);

// 파일 업로드 설정
const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'public/img');
        },
        filename(req, file, done) {
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits: { fileSize: 10 * 1024 * 1024 }
});

// 정적 파일 경로 설정
// 정적파일은 소스코드 제외, 서버의 데이터를 출력하는 용도가 아닌 파일을 뜻함.
// html, css, js, 이미지, 사운드, 동영상 등...
app.use("/", express.static('public'));

// 파일 다운로드를 위한 모듈 가져오기
let util = require('util');
let mime = require('mime');

// 데이터베이스 연결
let connection = mysql.createConnection(options);
connection.connect((err) => {
    if(err) {
        console.log(err, err.message);
        throw err;
    }
    else {
        console.log("connect success");
    }
});

// 기본 요청 처리, 메인 화면 출력
// 서버가 데이터만 리턴할 경우라면 이 설정은 필요없다.
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// 데이터 전체 가져오기
app.get("/item/all", (req, res) => {
    // HTML 출력한다면 res.sendFile(파일 경로)이며, 서버의 데이터 출력 못함
    // 템플릿 엔진을 사용한다면 res.render(파일경로, 데이터)
    // 템플릿 엔진에게 넘겨주는 데이터는 프로그래밍 언어의 데이터이다.

    // JSON 출력한다면 res.json(데이터)
    // JSON 문자열 형태로 데이터를 제공
    // frontend에서 데이터를 수신해서 출력하는 구조
    // 데이터는 primary key를 기준으로 정렬되어 있다. 
    // 전체 데이터를 가져올 때는(2개 이상) 정렬해서 가져와야 함.
    connection.query("select * from goods order by itemid desc", (error, results, fields) => {
        if(error) {
            // DB 연결 에러
            console.log(error);
            // 에러 발생시에도 데이터는 전송해야함.
            res.json({ 
                'result':false 
            });
        }
        else {
            // DB 연결 성공, 정상적으로 응답을 한 경우
            res.json({ 
                'result':true,
                'allList':results
            });
        }
    });
});

// 파라미터 읽기
// URL로 /item/list/pageNo=6 등으로 넘겨주어야 한다.
// 파라미터를 주지 않으면 pageNo는 undefined가 된다.
// const pageNo = req.query.pageNo;
// console.log(pageNo);

// URL에 포함된 파라미터 읽기
// 파라미터 주지 않으면 아무일도 하지 않는다.
// app.get("item/list/:pageNo") 식으로 작성
// const pageNo = req.params.pageNo;
// console.log(pageNo);

// 데이터 일부분 가져오기 (TOP-N) 
// URL: /item/list
// param: pageNo 1개, 파라미터에 pageNo가 없다면 1로 설정
app.get("/item/list", (req, res) => {
    // 파라미터 읽어오기
    let pageNo = req.query.pageNo;
    if(pageNo == undefined) pageNo = 1;
    // 브라우저에서 /item/list, /item/list?pageNo=7 등으로 테스트

    // 페이지 단위로 데이터 가져오기
    // select 구문에서 limit(시작번호, 건너뛸 데이터개수) 를 활용하여 가져오면 됨.
    // 데이터 개수:10, 1페이지의 시작번호 0, 2페이지:10, 3페이지:20, 4페이지:30 ...
    // 건너뛸 데이터 개수 = (페이지번호-1) * 데이터개수
    // 가지고 있는 데이터보다 이후의 데이터를 요구하면 클라이언트 단에서 서버에게 요청하지 못하도록 작업해야 하기에 항상 전체 데이터 개수를 전송해줘야한다.
    let start = (pageNo - 1) * 5; // 페이징 처리에 사용할 변수
    let result = true; // 성공 및 실패 여부 저장
    let dataList; // 성공시 데이터를 저장
    connection.query("select * from goods order by itemid desc limit ?, 5", [parseInt(start)], (err, results, fields) => {
        if(err) {
            console.log(err);
            result = false;
        }   
        else {
            dataList = results;
            
            // 전체 데이터 개수를 가져오기
            // node에서 select 구문을 실행했을 때의 결과는 무조건 배열이다.
            let count = 0;
            connection.query("select count(*) count from goods", (err, results, fields) => {
                if(err) {
                    console.log(err);
                    result = false;
                }
                else {
                    // 하나의 행이기에 인덱스로 접근한다.
                    count = results[0].count;
                    
                    // 응답을 전송
                    if(result === false) res.json({ "result":false });
                    else res.json({ "result":true,  "dataCnt":count, "dataList":dataList });
                }
            });
        }
    });    
});

// 데이터 상세보기
app.get("/item/view", (req, res) => {
    const iid = req.query.itemid;
    console.log(iid);
    let result = true;
    let data;
    // itemid를 이용해서 1개의 데이터를 가져오기
    connection.query("select * from goods where itemid=?", iid, (err, results, fields) => {
        if(err) {
            console.log(err);
            result = false;
        }
        else {
            data = results[0];
            console.log(data);
        }
        // 응답을 전송
        if(result === false) res.json({ "result":false });
        else res.json({ "result":true,  "data":data });
    });
});

// 이미지 다운로드 처리
app.get("/img/:pictureurl", (req, res) => {
    const pictureurl = req.params.pictureurl;
    console.log(pictureurl);

    // 이미지 파일의 절대 경로 생성
    let file = "C:/Users/user/Documents/frontend/node/connectMariaDB/public/img" + "/" + pictureurl;
    console.log(__dirname);
    
    // 파일의 이름을 가지고 타입을 생성
    let mimeType = mime.lookup(pictureurl);
    res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
    res.setHeader('Content-type', mimeType);
    
    // 파일의 내용을 읽어 res에 전송
    let fileStream = fs.createReadStream(file);
    fileStream.pipe(res);
});

// 현재날짜를 문자열로 리턴하는 함수
const getDate = () => {
    let date = new Date();
    let year = date.getFullYear();
    let mon = date.getMonth() + 1; // 월은 +1을 해야한다.
    let day = date.getDate();
    // 월, 일을 두자리수로 맞추기
    mon = mon >= 10 ? mon : "0" + mon;
    day = day >= 10 ? day : "0" + day;
    return year + "-" + mon + "-" + day
};

// 현재날짜 및 시간을 문자열로 리턴하는 함수
const getTime = () => {
    let date = new Date();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();
    hour = hour >= 10 ? hour : "0" + hour;
    min = min >= 10 ? min : "0" + min;
    sec = sec >= 10 ? sec : "0" + sec;
    return getDate() + " " + hour + ":" + min + ":" + sec;
};

// 데이터 삽입하기
// itemid중에서 가장 큰 itemid를 찾아서 +1
// itemname, price, description, pictureurl(파일)은 직접 입력
// updateadate는 현재 날짜를 문자열로 입력
// 삽입, 삭제, 수정 작업이 발생하면 update.txt 파일에 작업 시각 기록.
app.post("/item/insert", upload.single("pictureurl"), (req, res) => {
    // 파라미터 읽기
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;

    // 파일 이름
    let pictureurl;
    if(req.file) pictureurl = req.file.filename;
    else pictureurl = "default.jpg";

    // 가장 큰 itemid 찾기
    connection.query("select max(itemid) max from goods", (err, results, fields) => {
        if(err) {
            console.log(err);
        }
        else {
            let itemid;
            if(results.length > 0) itemid = results[0].max + 1;
            else itemid = 1;
            // 데이터 삽입 구문
            connection.query("insert into goods(itemid, itemname, price, description, pictureurl, updatedate) values (?, ?, ?, ?, ?, ?)", [itemid, itemname, price, description, pictureurl, getDate()], (err, results, fields) => {
                let result = true;
                if(err) {
                    console.log(err);
                    result = false;
                }
                if(result === false) res.json({ "result":false });
                else {
                    console.log("삽입 성공!!");
                    // 성공했을 때 현재 날짜 및 시간을 update.txt에 기록한다.
                    logWrite();
                    res.json({ "result":true });
                }
            });
        }
    });
});

// 데이터 삭제하기
app.post("/item/delete", (req, res) => {
    // post 방식으로 전송된 데이터를 읽기
    let itemid = req.body.itemid;
    connection.query("delete from goods where itemid=?", [itemid], (err, results, fields) => {
        let result = true;
        if(err) {
            console.log(err);
            result = false;
        }
        if(result === false) res.json({ "result":false });
        else {
            console.log("삭제 성공!!");
            // 삭제 성공했을 때 현재 날짜 및 시간을 update.txt에 기록한다.
            logWrite();
            res.json({ "result":true });
        }
    })
});

const logWrite = () => {
    // 현재 날짜 및 시간을 update.txt에 기록한다.
    const writeStream = fs.createWriteStream("./update.txt");
    writeStream.write(getTime());
    writeStream.end();
}

// 데이터 수정하기 - 수정화면으로 이동
app.get("/item/update", (req, res) => {
    // public 디렉토리의 update.html 읽어 리턴한다.
    fs.readFile("./public/update.html", (err, data) => {
        res.end(data);
    });
});


// 에러 발생시 처리
app.use((err, req, res, next) => {
    console.log(err);
    res.status(500).send(err.message);
});

// 서버 구동
app.listen(app.get("port"), () => {
    console.log(app.get("port"), "번 포트에서 대기 중입니다.");
});

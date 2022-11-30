const express = require('express');
const morgan = require('morgan');
const path = require('path');
const multer = require('multer');
const fs = require('fs');

// express 웹 애플리케이션 서버를 9000포트로 생성
const app = express();
app.set("port", process.env.PORT || 9000);

// 로그를 화면에 출력
app.use(morgan('dev'));

// 폼이 아닌 형태의 post방식의 파라미터를 읽기 위한 설정
let bodyParser = require('body-parser')
app.use( bodyParser.json() ); // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({ // to support URL-encoded bodies
    extended: true
})); 

//파일 다운로드를 위한 모듈 가져오기
let util = require('util')
let mime = require('mime');
const { MongoClient } = require('mongodb');

// 파일 업로드를 위한 디렉토리가 없다면 생성한다.
try {
    fs.readdirSync('img');
} catch (error) {
    console.error('img 폴더가 없어 img 폴더를 생성합니다.');
    fs.mkdirSync('img');
}

// 파일 업로드 설정
const upload = multer({
    storage: multer.diskStorage({
    destination(req, file, done) { // 업로드할 디렉토리 설정
        done(null, 'img/');
    },
    filename(req, file, done) { // 업로드 될 때의 파일 이름 설정
        const ext = path.extname(file.originalname);
        done(null, path.basename(file.originalname, ext) + Date.now() + ext);
    },
}),
    limits: { fileSize: 10 * 1024 * 1024 },
});

// 템플릿 엔진(서버의 데이터를 html에 출력하기 위한 모듈) 설정
app.set('view engine', 'html');
app.engine('html', require('ejs').renderFile);

// MongoDB 사용을 위한 모듈 가져오기
const mongo = require('mongodb').MongoClient;
// 접속할 데이터베이스 URL을 설정
let databaseURL = 'mongodb://localhost:27017/';

// node 데이터베이스 item 컬렉션에 존재하는 모든 데이터 리턴
app.get("/item/all", (req, res) => {
    // 데이터베이스 연결
    MongoClient.connect(databaseURL, {useNewUrlParser:true}, (error, database) => {
        if(error) {
            console.log(error);
            // throw error로 넘기는 것은 웬만하면 지양해야 한다.
            // res.json으로 무엇이 잘못되었는지 클라이언트에게 전달해주는 것이 좋다.
            // "message":error.message 방식으로 에러코드 그대로 전달하지 말고 별도로 파싱한 메시지를 전달해줘야 한다.
            res.json({ "result":false, "message":"reason" });
        }
        else {
            // 데이터베이스 전체 데이터 가져오기
            let db = database.db('node');
            // item 컬렉션의 모든 데이터 가져오기
            db.collection("item").find().sort({itemid:-1}).toArray((err, items) => {
                if(err) {
                    console.log(err);
                    res.json({ "result":false, "message":"reason" });
                }
                else {
                    res.json({ "result":true, "count":items.length, "list":items });        
                }
            });
        }
    });
});

// node DB의 item 컬렉션의 데이터를 페이지 단위로 가쟈오기
// 데이터페이스에서 페이지 단위로 데이터를 가져올 때 필요한 데이터: 건너뛸 개수(offset), 가져올 데이터 개수(limit)
// 클라이언트에서 넘겨주는 데이터: [페이지번호, 데이터갯수]
app.get("/item/page", (req, res) => {
    // 클라이언트 데이터 받아오기
    let pageNo = req.query.pageNo; // 페이지번호
    let count = req.query.count; // 데이터개수
    
    // 건너뛸 데이터 개수 연산하기
    if(pageNo === undefined) pageNo = 1;
    if(count === undefined) count = 2;
    // 클라리언트에서 받아온 데이터는 문자열이기에 연산할 때 정수로 변환해야함.
    let start = parseInt((pageNo - 1) * count);
    
    // DB 연결
    MongoClient.connect(databaseURL, {useNewUrlParser:true}, (error, database) => {
        if(error) {
            console.log(error);
            res.json({ "result":false, "message":"reason" });
        }
        else {
            let db = database.db('node');
            // item 컬렉션에서 페이지번호마다 count개 만큼 데이터 가져오기
            db.collection("item").find().sort({itemid:-1}).skip(start).limit(parseInt(count)).toArray((err, items) => {
                if(err) {
                    console.log(err);
                    res.json({ "result":false, "message":"reason" });
                }
                else {
                    res.json({ "result":true, "count":items.length, "list":items });        
                }
            });
        }
    });
});

// node DB의 item 컬렉션에서 하나의 데이터 상세보기
// 요즘에는 url?itemid=1 방식보다는 url/1 방식으로 매개변수가 1개일 경우는 생략하는 방식을 주로 사용한다.
app.get("/item/:itemid", (req, res) => {
    // 클라이언트 데이터 받아오기 - url에 포함된 데이터 받기
    const itemid = req.params.itemid;
    // DB 연결
    MongoClient.connect(databaseURL, {useNewUrlParser:true}, (error, database) => {
        if(error) {
            console.log(error);
            res.json({ "result":false, "message":"reason" });
        }
        else {
            let db = database.db('node');
            // item 컬렉션에서 특정 itemid를 가진 데이터 가져오기
            db.collection("item").findOne({ "itemid":Number(itemid) }, (err, item) => {
                if(err) {
                    console.log(err);
                    res.json({ "result":false, "message":"reason" });
                }
                else {
                    res.json({ "result":true, "item":item });        
                }
            });
        }
    });
});


// node DB의 item 컬렉션에 데이터 삽입하기
// 받을 데이터: [itemname, description, price, pictureurl(파일)]
app.post("/item/insert", upload.single("pictureurl"), (req, res) => {
    // 파라미터 읽어오기
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;
    let pictureurl;

    // 업로드 파일의 유무에 따라 파일의 이름 설정
    if(pictureurl === undefined) pictureurl = req.file.filename;
    else pictureurl = "default.jpg";

    MongoClient.connect(databaseURL, { useNewUrlParser:true }, (error, database) => {
        if(error) {
            console.log(error);
            res.json({ "result":false, "message":"reason" });
        }
        else {
            let db = database.db('node');
            // 가장 큰 itemid를 찾아오기
            db.collection("item").find({}, {projection:{_id:0, itemid:1}}).sort({itemid:-1}).limit(1).toArray((err, result) => {
                if(err) {
                    console.log(err);
                    res.json({ "result":false, "message":"reason" });
                }
                else {
                    let itemid = 1;
                    if(result[0] != null) itemid = result[0].itemid + 1;

                    db.collection('item').insertOne({ "itemid":itemid, "itemname":itemname, "description":description, "price":price, "pictureurl":pictureurl }, (err, result) => {
                        if(err) {
                            console.log(err);
                            res.json({ "result":false, "message":"reason" });
                        }
                        else {
                            res.json({ "result":true });
                        }
                    });
                }
            });
        }
    });
});

// node DB의 item 컬렉션의 데이터 하나 수정하기
// app.post("item/update/:itemid", (req, res) => {

// });

// node DB의 item 컬렉션의 데이터 하나 삭제하기


// 에러 처리를 위한 코드
app.use((err, req, res, next) => {
    console.log(err);
    res.status(500).send(message);
});

// 서버 구동
app.listen(app.get("port"), () => {
    console.log(app.get("port"), "번 포트에서 서버 대기중.");
});
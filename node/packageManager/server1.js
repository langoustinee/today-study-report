// http 모듈 가져오기
const http = require("http");
const fs = require("fs").promises;

// 서버 생성
// 포트번호는 일반적으로 1024번까지 예약되어 있기에 사용 불가함.
// 1521, 3306, 27017은 DB 포트번호임.
// 8080 톰캣 같은 Web Container가 사용함.
// 80을 사용한다면 생략 가능.
// 443을 사용한다면 https의 경우 생략 가능.

// http.createServer((req, res) => {
//     // 응답 생성
//     // res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
//     // res.write('<h1>Hello World!</h1>');
//     // res.end('<p>Hello Node Server!</p>');
// }).listen(8888, () => { // 서버 연결
//     console.log("8888 포트에서 서버 대기중...");
// });

http.createServer(async (req, res) => {
    try {
        // 파일의 내용을 읽어서 data에 저장
        // 다음 명령은 이 명령이 끝나고 나면 수행
        const data = await fs.readFile("./index.html");
        res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end(data);
    }catch(err) {
        res.writeHead(500,  { 'Content-Type': 'text/html; charset=utf-8' });
        res.end(err.message);
    }
}).listen(8888, () => { // 서버 연결
    console.log("8888 포트에서 서버 대기중...");
});
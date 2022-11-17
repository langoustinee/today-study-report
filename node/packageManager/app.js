// 웹 서버를 위한 외부 모듈
const express = require("express");

// 현재 디렉토리에 대한 절대경로를 알아내기 위한 내장모듈 (내장모듈은 별도로 설치할 필요가 없다.)
const path = require("path");

// 서버 준비
const app = express();
app.set("port", 8085);

// 일 단위 로그 기록을 위한 설정
// morgan, fire-stream-rotator 를 미리 설치해놓아야 함.
const morgan = require("morgan");
const fileStreamRotator = require("file-stream-rotator");
// 내장 모듈
const fs = require("fs");

// 로그 파일을 저장할 디렉토리 생성
let logDir = path.join(__dirname, "logs");
// 디렉토리 존재 여부를 확인하고 디렉토리가 없으면 생성
// 존재하면 true, 존재하지 않으면 false이기에 mkdir 한다.
fs.existsSync(logDir) || fs.mkdirSync(logDir);

// 일 단위 로그 기록 설정
const accesslogStream = fileStreamRotator.getStream({
    date_format:"YYYY-MM-DD",
    filename:path.join(logDir, "access-%DATE%.log"),
    frequency:"daily",
    verbose:true
});
// 모건 설정
app.use(morgan("combined", {stream:accesslogStream}));


// 사용자 요청 처리
// /는 url상의 포트번호까지의 요청(~~:8085)
// /는 index.html을 대체함.
app.get("/", (req, res) => {
    // 현재 디렉토리에 있는 main.html 출력
    res.sendFile(path.join(__dirname, "/main.html"));
});

// 서버 구동
app.listen(app.get("port"), () => {
    console.log(app.get("port"), " 번 포트에서 대기중...");
});


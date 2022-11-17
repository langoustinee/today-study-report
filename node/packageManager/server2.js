// node에서는 다른 모듈을 가져올 때 require를 사용
const express = require("express");

// 웹 서버를 생성할 수 있는 인스턴스 생성
const app = express();

// 1024 port 까지는 예약된 포트
// 80은 http의 기본 포트, 443은 https의 기본 포트
app.set("port", 4005);

// 요청을 처리하는 함수 외부에 만든 변수는 전역변수이기에 모든 사용자가 공유한다.
let num = 1;
// 세션을 사용하기 위한 모듈 가져오기
const session = require("express-session");
// 세션을 사용하기 위한 미들웨어 설정
// 세션은 클라이언트에게 키를 만들어서 매핑한다. 이 때, 키의 값을 알아보기 어렵게 하기 위해 연산을 수행할 secret 값을 준다.
app.use(session({
    secret: "secret key",
    resave: false,
    saveUninitialized: true
}));

// 사용자 요청을 처리
app.get("/session", (req, res) => {
    // 세션에 num이 없으면 1로 초기화, 있으면 1씩 증가
    if(!req.session.number) {
        req.session.number = 1;
        console.log(req.session.number);
    }
    else {
        req.session.number += 1;
        console.log(req.session.number);
    }
    
    // 내용을 화면에 출력
    res.send("num은 " + num + "입니다." + " session의 num은 " + req.session.number + " 입니다.");
    num += 1;
});

// 서버 구동시켜 사용자 요청 처리
app.listen(app.get("port"), () => {
    console.log("서버 대기 중");
});


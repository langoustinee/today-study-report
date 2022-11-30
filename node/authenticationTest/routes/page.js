const express = require('express');

// 로그인 여부를 판단하기 위해 만든 middlewares 모듈 가져오기
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const router = express.Router();

// 무조건 수행되는 공통 로직 작성
router.use((req, res, next) => {
    // 로그인한 사용자 정보를 res.locals.user에 저장
    res.locals.user = req.user;
    // 게시글을 follow한 개수와 follow 된 개수
    res.locals.followerCount = 0;
    res.locals.followingCount = 0;
    // 게시글을 follow하고 있는 사용자 목록
    res.locals.followerIdList = [];
    next();
});

// 메인화면
router.get('/', (req, res, next) => {
    const twits = [];
    // 템플릿 엔진을 활용한 html 출력
    // views 디렉토리의 main.html 출력
    res.render('main', { title: 'Node Authentication', twits });
});

// 회원가입 화면 - 로그인이 되어 있지 않을 때만 수행
router.get('/join', isNotLoggedIn, (req, res, next) => {
    res.render('join', { title: '회원가입 - Node Authentication' });
});

// 내 정보 화면 - 로그인 되어 있을 때만 수행
router.get('/profile', isLoggedIn, (req, res, next) => {
    res.render('profile', { title: '내 정보 - Node Authentication' });
});

module.exports = router;
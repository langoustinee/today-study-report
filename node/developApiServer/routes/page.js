const express = require('express');

// 로그인 여부를 판단하기 위해 만든 middlewares 모듈 가져오기
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

// 게시글, 사용자 모듈
const { Post, User, Hashtag } = require('../models');

const router = express.Router();

// 무조건 수행되는 공통 로직 작성
router.use((req, res, next) => {
    // 로그인한 사용자 정보를 res.locals.user에 저장
    res.locals.user = req.user;
    // 게시글을 follow한 개수와 follow 된 개수
    res.locals.followerCount = req.user ? req.user.Followers.length : 0;
    res.locals.followingCount = req.user ? req.user.Followings.length : 0;
    // 게시글을 follow하고 있는 사용자 목록
    res.locals.followerIdList = req.user ? req.user.Followings.map(f => f.id) : [];
    next();
});

// 메인 화면
// router.get('/', (req, res, next) => {
//     const twits = [];
//     // 템플릿 엔진을 활용한 html 출력
//     // views 디렉토리의 main.html 출력
//     res.render('main', { title: 'Node Authentication', twits });
// });
router.get("/", async (req, res, next) => {
    try {
        // Post 모델의 데이터를 찾아올 때 User 정보의 id와 nick 정보도 함께 가져오기
        const posts = await Post.findAll({
            include: {
                model: User,
                attributes: ['id', 'nick']
            },
            order: [ [ 'createdAt', 'DESC' ] ]
        });
        res.render("main", {
            title: "NodeAuthentication",
            twits: posts
        });
    }
    catch (error) {
        console.log(error);
        next(error);
    }
});

router.get('/hashtag', async (req, res, next) => {
    // 파라미터 읽어오기
    const query = req.query.hashtag;
    if (!query) {
        // 파라미터 없다면 리다이렉트
        return res.redirect('/');
    }
    try {
        const hashtag = await Hashtag.findOne({ where: { title: query } });
        let posts = [];
        if (hashtag) {
            posts = await hashtag.getPosts({ include: [{ model: User }] });
        }
        console.log("posts = ", posts);
        return res.render('main', {
            title: `${query} | NodeAuthentication`,
            twits: posts,
        });
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 회원가입 화면 - 로그인이 되어 있지 않을 때만 수행
router.get('/join', isNotLoggedIn, (req, res, next) => {
    res.render('join', { title: '회원가입 - NodeAuthentication' });
});

// 내 정보 화면 - 로그인 되어 있을 때만 수행
router.get('/profile', isLoggedIn, (req, res, next) => {
    res.render('profile', { title: '내 정보 - NodeAuthentication' });
});

module.exports = router;
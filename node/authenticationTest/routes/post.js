const express = require('express');

// 파일 업로드를 위한 모듈 가져오기
const multer = require('multer');
const path = require('path');
const fs = require('fs');

// 데이터 삽입을 위한 모듈
const { Post, Hashtag } = require('../models');

// 로그인 여부 판단하는 모듈
const { isLoggedIn } = require('./middlewares');

const router = express.Router();

// 업로드할 디렉토리가 없다면 새로 생성하기
try {
    fs.readdirSync('public/img');
} catch (error) {
    console.error('img 폴더가 없으면 img 폴더를 생성합니다.');
    fs.mkdirSync('public/img');
}

// 파일 업로드 객체 생성
const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'public/img/');
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
        },
    }),
    limits: { fileSize: 10 * 1024 * 1024 }
});

// 이미지 업로드
// 로그인 되어있을 경우에만 가능
router.post('/img', isLoggedIn, upload.single('img'), (req, res) => {
    console.log(req.file);
    res.json({ url: `/img/${req.file.filename}` });
});

// 게시글 업로드
const upload2 = multer();
router.post('/', isLoggedIn, upload2.none(), async (req, res, next) => {
    // DB 연동은 무조건 비동기 방식이기에 async await 방식을 이용해야 함.
    try {
        // 작성한 게시글 데이터베이스에 업로드
        const post = await Post.create({
            content: req.body.content,
            img: req.body.url,
            UserId: req.user.id // User 객체에서 가져옴.
        });
        // 해시태그 찾기 - #으로 시작하는 내용을 찾는다.
        const hashtags = req.body.content.match(/#[^\s#]*/g);
        // 해시태그가 있다면 데이터 삽입하기
        if (hashtags) {
            // 전부 실행
            const result = await Promise.all(
                // .map: 배열의 전체 데이터를 순서대로 하나씩 대입해서 { } 내부의 내용을 수행하는 것이다.
                // 항상 return이 있어야 하며, return한 데이터를 통해 배열을 만들어 반환한다.
                hashtags.map(tag => {
                    return Hashtag.findOrCreate({
                        where: { title: tag.slice(1).toLowerCase() }
                    })
                }),
            );
            await post.addHashtags(result.map(r => r[0]));
        }
        res.redirect('/');
    } catch (error) {
        console.error(error);
        next(error);
    }
});

module.exports = router;    
// 해당 파일의 코드는 page.js에 작성해도 된다.
// page.js는 화면을 보여주는 역할이고, auth.js는 처리하는 역할을 하도록 분리한 것이다.

const express = require('express');
// 로그인 및 로그아웃 처리를 위한 모듈
const passport = require('passport');
// 회원가입을 위한 암호화 모듈 가져오기 - 암호화해서 비교할 수 있지만 복호화는 불가능하다.
const bcrypt = require('bcrypt');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');
const User = require('../models/user');
const router = express.Router();

// 회원 가입 처리 - /auth/join인데 라우팅 할 때 /auth 포함됨.
router.post('/join', isNotLoggedIn, async (req, res, next) => {
    // 기존에는 const email = req.body.email; 등으로 파라미터를 찾아왔다.
    // 구조적 분해 할당 방식을 통해 재작성
    const { email, nick, password } = req.body;
    try {
        // 이메일 존재여부 확인
        const exUser = await User.findOne({ where: { email } });
        // 이메일이 중복이라면 회원가입 페이지로 리다이렉트하는데 error 키에 메시지를 가지고 이동한다.
        if (exUser) return res.redirect('/join?error=exist');
        else {
            // 가입가능한 이메일이라면 비밀번호 해싱
            const hash = await bcrypt.hash(password, 12);
            // 데이터베이스에 저장
            await User.create({ email, nick, password: hash });
            return res.redirect('/');
        }
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 로그인 처리
router.post('/login', isNotLoggedIn, (req, res, next) => {
    // passpoprt 모듈을 이용하여 로그인 처리
    passport.authenticate('local', (authError, user, info) => {
        if (authError) {
            console.error(authError);
            return next(authError);
        }
        // 일치하는 사용자가 없다면
        if (!user) return res.redirect(`/?loginError=${info.message}`);
        else {
            return req.login(user, (loginError) => {
                if (loginError) {
                    console.error(loginError);
                    return next(loginError);
                }
                // 로그인 성공하면 메인페이지로 리다이렉트
                return res.redirect('/');
            });    
        }
    })(req, res, next); // 미들웨어 내의 미들웨어에는 (req, res, next)를 붙입니다.
});

// 로그아웃 처리
router.get('/logout', isLoggedIn, (req, res) => {
    req.logout((err) => {
        if (err) { return next(err); }
        // 로그아웃 시에는 세션을 초기화후 메인페이지로 이동
        req.session.destroy();
        res.redirect('/');
    });
});

module.exports = router;
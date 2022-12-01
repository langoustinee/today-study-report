// 카카오 로그인 기능 구현
const passport = require('passport');
const KakaoStrategy = require('passport-kakao').Strategy;
const User = require('../models/user'); // 사용자 정보 가져오기

module.exports = () => {
    passport.use(new KakaoStrategy({
        clientID: process.env.KAKAO_ID,
        callbackURL: '/auth/kakao/callback'
    }, async (accessToken, refreshToken, profile, done) => {
        // 로그인 성공했을 경우 정보 출력
        console.log("kakao profile:", profile);
        
        try {
            // 이전에 로그인 기록이 있는지 찾기 위해
            // 카카오 id, provider가 kakao로 되어있는지
            // 데이터가 있는지 조회한다.
            const exUser = await(User.findOne({
                where:{ snsId:profile.id, provider:'kakao' }
            }));
            if(exUser) {
                // 이전에 로그인 기록이 있다면
                done(null, exUser);
            }
            else {
                // 이전에 로그인 기록이 없다면 새로운 데이터 저장
                const newUser = await User.create({
                    email: profile._json.kakao_account.email,
                    nick: profile.displayName,
                    snsId: profile.id,
                    provider: 'kakao'
                });
                done(null, newUser);
            }
        }
        catch(err) {
            console.log(err);
            done(err);
        }
    }
    ));
};
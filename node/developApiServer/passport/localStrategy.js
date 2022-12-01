// 로컬 로그인 관련된 기능을 구현

const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');
const User = require('../models/user');

module.exports = () => {
    passport.use(new LocalStrategy({ usernameField: "email", passwordField: "password" }, async (email, password, done) => {
        try {
            // 로그인 처리를 위해서 email에 해당하는 데이터 찾아오기
            const exUser = await User.findOne({ where:{ email }});
            if(exUser) {
                // 비밀번호 비교
                const result = await bcrypt.compare(password, exUser.password);
                if(result) done(null, exUser);
                else done(null, false, { message: "잘못된 비밀번호" });
            }
            else done(null, false, { message: "존재하지 않는 사용자" });
        }
        catch(err) {
            console.log(err);
            done(err);
        }
    }));
};
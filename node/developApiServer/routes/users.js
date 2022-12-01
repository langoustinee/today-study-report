// 팔로우 관련 처리

const express = require('express');
const { isLoggedIn } = require('./middlewares');
const User = require('../models/user');

const router = express.Router();

// 사용자 팔로우
router.post("/:id/follow", isLoggedIn, async (req, res, next) => {
    try {
        // 현재 로그인한 사용자를 찾는다.
        const user = await User.findOne({ where: { id: req.user.id } });
        if(user) {
            // 팔로우 추가
            // 사용자 id를 10진수로 변환하여 추가한다.
            await user.addFollowing(parseInt(req.params.id, 10));
            res.send("success");
        }
        else {
            req.status(404).send("No User");
        }
    }
    catch(error) {
        console.log(error);
        next(error);
    }
});

module.exports = router;
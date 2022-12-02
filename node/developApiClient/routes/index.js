const express = require('express');
const axios = require('axios');

// 매번 동일한 요청을 위한 URL을 상수로 설정
// const URL = 'http://localhost:/v1';
const URL = 'http://localhost:/v2';


// ajax 요청을 할 때 요청자의 정보를 확인하기 위해 origin 헤더 추가
axios.defaults.headers.origin = 'http://localhost:4000';

// 토큰 발급 코드
const request = async (req, api) => {
    try {
        // 세션에 토큰이 없으면
        if (!req.session.jwt) {
            const tokenResult = await axios.post(`${URL}/token`, {
                clientSecret: process.env.CLIENT_SECRET
            });
            // 세션에 토큰 저장
            req.session.jwt = tokenResult.data.token;
        }
        // API 요청
        const result = await axios.get(`${URL}${api}`, {
            headers: { authorization: req.session.jwt }
        });
        return result;
    }
    catch (error) {
        // 토큰 유효기간 만료시 토큰 재발급 받기
        if (error.response.status === 419) {
            // 기존 토큰 삭제
            delete req.session.jwt;
            // 토큰 재발급 요청
            return request(req, api);
        } // 419 외의 다른 에러면
        return error.response;
    }
};

const router = express.Router();


router.get('/', (req, res) => {
    res.render('main', { key: process.env.CLIENT_SECRET });
});

router.get('/mypost', async (req, res, next) => {
    try {
        const result = await request(req, '/posts/my');
        res.json(result.data);
    }
    catch (error) {
        console.error(error);
        next(error);
    }
});

// 토큰 발급 테스트
router.get('/test', async (req, res, next) => {
    try {
        // 세션에 토큰이 없으면 토큰 발급 시도
        if (!req.session.jwt) {
            const tokenResult = await axios.post('http://localhost/v1/token', {
                clientSecret: process.env.CLIENT_SECRET
            });
            if (tokenResult.data && tokenResult.data.code === 200) {
                // 토큰 발급 성공했다면 세션에 토큰을 저장한다.
                req.session.jwt = tokenResult.data.token;
            } else {
                // 토큰 발급 실패했다면 실패 사유 응답한다.
                return res.json(tokenResult.data);
            }
        }
        // 발급받은 토큰 내용 확인
        const result = await axios.get('http://localhost/v1/test', {
            headers: { authorization: req.session.jwt }
        });
        return res.json(result.data);
    } catch (error) {
        console.error(error);
        if (error.response.status === 419) { // 토큰 만료 시
            return res.json(error.response.data);
        }
        return next(error);
    }
});

module.exports = router;
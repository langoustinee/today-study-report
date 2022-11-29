// ORM 모듈 가져오기
const Sequelize = require('sequelize');

// Good 테이블 정보 가져오기
const Good = require('./good');

// 환경설정 - development
const env = process.env.NODE_ENV || 'development';
// 환경설정 내용 가져오기
const config = require('../config/config')[env];
// 내보낼 객체 생성
const db = {};
// 실제 ORM 설정
const sequelize = new Sequelize(config.database, config.username, config.password,
config);

db.sequelize = sequelize;

db.Sequelize = Sequelize;

// model 마다 아래 구문을 추가해야함.
db.Good = Good;
Good.init(sequelize);

module.exports = db;
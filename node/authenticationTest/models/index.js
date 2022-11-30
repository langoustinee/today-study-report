const Sequelize = require('sequelize');
const env = process.env.NODE_ENV || 'development';
const config = require('../config/config')[env];

// Model 가져오기
const User = require('./user');
const Post = require('./post');
const Hashtag = require('./hashtag');

const sequelize = new Sequelize(
  config.database, config.username, config.password, config,
);
const db = {};

db.sequelize = sequelize;
db.Sequelize = Sequelize;

// 관계 설정
db.User = User;
db.Post = Post;
db.Hashtag = Hashtag;

User.init(sequelize);
Post.init(sequelize);
Hashtag.init(sequelize);

User.associate(db);
Post.associate(db);
Hashtag.associate(db);

module.exports = db;

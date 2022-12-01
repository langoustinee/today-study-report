const Sequelize = require('sequelize');

module.exports = class Post extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            content: {
                type: Sequelize.STRING(200),
                allowNull: false,
            },
            img: {
                type: Sequelize.STRING(200),
                allowNull: true,
            },
        },
            {
                sequelize,
                timestamps: true,
                underscored: false,
                modelName: 'Post',
                tableName: 'posts',
                paranoid: true,
                charset: 'utf8mb4',
                collate: 'utf8mb4_general_ci',
            });
    }

    static associate(db) {
        // 1(User):N(Post)
        db.Post.belongsTo(db.User);
        // N(Post):M(Hashtag)
        db.Post.belongsToMany(db.Hashtag, { through: 'PostHashtag' });
    }
};
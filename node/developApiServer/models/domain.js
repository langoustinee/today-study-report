const Sequelize = require('sequelize');

module.exports = class Domain extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            host: {
                type: Sequelize.STRING(150),
                allowNull: false
            },
            clientSecret: {
                type: Sequelize.STRING(36),
                allowNull: false
            },
            type: {
                type: Sequelize.ENUM("free", "premium"),
                allowNull: false
            }
        }, {
            sequelize,
            timestamps: true,
            underscored: false,
            modelName: 'Domain',
            tableName: 'domains',
            paranoid: false
        });
    }
    static associate(db) {
        // User(1):Domain(N) - User의 PK가 Domain의 FK로 설정됨.
        db.Domain.belongsTo(db.User);
    }
};
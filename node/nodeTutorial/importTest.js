// exportTest.js 파일에서 내보낸 함수 가져오기
const { playerMove, playerHansUp } = require("./exportTest")

const cmdList = new Array("top", "down", "left", "right");

const playerEvent = () => {
    for(const cmd of cmdList) {
        playerMove("adam", cmd);
        playerHansUp("jooney", cmd);    
    }
}

module.exports = playerEvent;
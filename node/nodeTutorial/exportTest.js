const playerMove = (player, dir) => {
    console.log(player + " 사용자가 " + dir + " 방향으로 움직입니다.");
};

const playerHansUp = (player, dir) => {
    console.log(player + " 사용자가 " + dir + " 방향으로 손을 흔듭니다.");
};

// export
module.exports = {
    playerMove,
    playerHansUp
};
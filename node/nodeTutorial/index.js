// 중괄호로 받은 것은 이름을 동일하게 유지해야 한다.
// 하나만 받은 경우는 이름을 변경할 수 있다.
const { playerMove, playerHansUp } = require("./exportTest");
const newEvent = require("./importTest");

playerMove("hong", "위");
playerHansUp("JUN", "오른쪽");

newEvent();

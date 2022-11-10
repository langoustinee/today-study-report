import { userInfo, printUserInfo, grade } from "./export.js";

console.log(userInfo);

const print = printUserInfo(userInfo.name, userInfo.age);
console.log(print);

if(userInfo.age > 25) console.log(grade);
else console.log("나이 자격 미달입니다");
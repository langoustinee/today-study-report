// 다른 프로세스를 실행할 수 있는 모듈 가져오기
const exec = require("child_process").exec;
const os = require("os");
console.log(os.type(), os.platform());
let myType = os.type().toLowerCase().indexOf("windows");
if(myType >= 0) console.log("windows!!!");
else console.log("not windows!!!");



// 프로세스 준비
let process;
// 양수면 windows, 음수면 windows가 아님. 
// mac의 경우 ls가 실행됨.
if(myType >= 0) process = exec("dir");
else process = exec("ls");



// 프로세스가 정상적으로 수행되면
process.stdout.on("data", (data) => {
    console.log(data.toString());
});

// 프로세스가 수행되지 않는다면
process.stderr.on("data", (data) => {
    console.log(data.toString());
});
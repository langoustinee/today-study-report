// 파일 입출력 모듈 가져오기
const fs = require("fs");

let data = fs.readFileSync("./sampleData1.txt");
// console.log(data.toString());

let dataSplit = data.toString().split("\n");
console.log("첫번째 줄: " + dataSplit[0]);
console.log("두번째 줄: " + dataSplit[1]);


// 비동기식으로 파일 가져오기
// err는 에러의 내용이고 data는 Buffer이다.
fs.readFile("./sampleData1.txt", (err, data) => {
    if(err) {
        // 에러 발생시
        console.log(err);
    }
    else {
        // 정상일 경우
        console.log(data.toString());
    }
});

// promise 활용
const fs2 = require("fs").promises;
fs2.readFile("./sampleData1.txt")
.then((data) => {
    console.log("promise: " + data.toString());
})
.catch((err) => {
    console.log(err);
});

// 일기 전용 스트림 생성
const fs3 = require("fs");
const readStream = fs.createReadStream("./sampleData1.txt", {highWaterMark:16});

// 데이터를 저장하기 위한 객체 생성
const dataList = [];

// 읽는 동안 발생하는 이벤트를 처리
readStream.on("data", (chunk) => {
    // 읽는동안에는 읽어온 데이터를 추가
    dataList.push(chunk);
});

// 읽기가 끝나면 발생하는 이벤트 처리
readStream.on("end", () => {
    // 읽기가 끝났다면 지금까지 읽은 내용을 하나로 만든다.
    let res = Buffer.concat(dataList);
    console.log("stream : " + res.toString());
});

// 읽는동안 에러가 발생할 경우
readStream.on("error", (err) => {
    console.log(err);
});


// 용량이 큰 파일 생성
// const fs4 = require("fs");
// const file = fs4.createWriteStream("./sampleBigData.txt");

// for(let i=0; i<10000000; i++) {
//     file.write("Hello World! \n");
// }
// file.end();


// 스트림 사용없이 파일 복사하기
// const fs5 = require("fs");
// console.log("복사 전 메모리 사용량: " + process.memoryUsage().rss);
// const data1 = fs5.readFileSync("./sampleBigData.txt");
// fs5.writeFileSync("./sampleData2.txt", data1);
// console.log("복사 후 메모리 사용량: " + process.memoryUsage().rss);

// 스트림 사용하여 파일 복사하기
// const fs6 = require("fs");
// console.log("복사 전 메모리 사용량: " + process.memoryUsage().rss);
// // 읽기와 쓰기 스트림 생성
// const read = fs6.createReadStream("./sampleBigData.txt");
// const write = fs6.createWriteStream("./sampleData3.txt");
// // 읽고 쓰기
// read.pipe(write);
// read.on("end", () => {
//     console.log("복사 후 메모리 사용량: " + process.memoryUsage().rss);    
// });
// read.on("error", (err) => {
//     console.log(err);
// })


// 월(month)는 0부터 시작하는 경우가 있음
// 연, 월, 일을 문자열로 바꾸어야 20221116으로 문자열을 붙일 수 있다.
let today = new Date();
let year = today.getFullYear().toString();
let month = (today.getMonth()+1).toString();
let day = today.getDate().toString();
let dirName = year+month+day;
// let dirName = new String(year+month+day);
console.log(today, year, month, day);
console.log("디렉토리명: " + dirName);

// 오늘 날짜로 된 디렉토리(20221116)가 없다면 생성하기
const fs7 = require("fs");

// 디렉토리 존재여부 확인
fs7.access(dirName, (err) => {
    if(err) {
        console.log("not found directory " + err);
        // 오늘 날짜 디렉토리가 없다면 오늘날짜명으로 디렉토리 생성
        fs7.mkdir(dirName, (err) => {
            if(err) console.log("create directory failed... " + err);
            else console.log("create directory success!");
        });
    }
    else console.log("already exist dirctory");
});

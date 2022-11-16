const path = require("path");

console.log(__dirname);
console.log(path.join(__dirname, "public"));

const url = require("url");
const address = "http://www.naver.com/login?id=xmun777";

// url 분해
// pathname에는 서버 URL은 제외한 경로를 저장.
// query에는 query string을 저장.
const p = url.parse(address);
console.log(p, p.query);

// address에서 파라미터만 가져오기
// searchParams 속성을 호출하면 파라미터 부분에 해당하는 객체를 리턴한다.
const addressURL = new URL("http://www.naver.com/login?id=xmun777");
console.log(addressURL.searchParams);

// id값 추출하기
console.log(addressURL.searchParams.get("id"));
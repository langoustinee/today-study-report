// 암호화 모듈 가져오기
const crypto = require("crypto");

let pwd = "kakao1234!@#$";
let pwd2 = "muzi123!";

// 단방향 암호화
let p1 = crypto.createHash("sha256").update(pwd).digest("base64");
let p2 = crypto.createHash("sha256").update(pwd).digest("base64");
// 동일한 문자열로 암호화하면 동일한 결과기 때문에 p1과 p2는 같다.
console.log(p1, p1 === p2);

p2 = crypto.createHash("sha256").update(pwd2).digest("base64");
console.log(p2, p1 === p2);

// 양방향 암호화
const algorithm = "aes-256-cbc";
// Node의 cryto 모듈에서 key는 32자리, iv는 16자리이다.
// 아래와 같이 소스코드에 하드코딩해두면 절대 안된다. (파일이나 DB 이용해야 한다.)
const key = "01234567890123456789012345678901";
const iv = "0123456789012345";

const cipher = crypto.createCipheriv(algorithm, key, iv);
let result = cipher.update('kakao-cloud-school-test', 'utf8', 'base64');
result += cipher.final('base64');
console.log('암호화:', result);

const decipher = crypto.createDecipheriv(algorithm, key, iv);
let result2 = decipher.update(result, 'base64', 'utf8');
result2 += decipher.final('utf8');
console.log('복호화:', result2);

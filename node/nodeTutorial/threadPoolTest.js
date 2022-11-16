const crypto = require("crypto");

const pass = "pass";
const salt = "salt";

const start = new Date();

// thread pool test 1,2,3,4 순서가 보장되지 않음.
crypto.pbkdf2(pass, salt, 1000000, 128, "sha512", () => {
    console.log("1:", Date.now() - start);
});
crypto.pbkdf2(pass, salt, 1000000, 128, "sha512", () => {
    console.log("2:", Date.now() - start);
});
crypto.pbkdf2(pass, salt, 1000000, 128, "sha512", () => {
    console.log("3:",Date.now() - start);
});
crypto.pbkdf2(pass, salt, 1000000, 128, "sha512", () => {
    console.log("4:",Date.now() - start);
});
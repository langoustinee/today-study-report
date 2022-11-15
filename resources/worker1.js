onmessage = (e) => {
    // html에서 전송한 결과를 받는다.
    let num = e.data;
    let res = 0;
    // 작업을 수행한다.
    for(let i=1; i<=num; i++) {
        res += i;
    }
    // 작업 결과를 html에게 전송한다.
    postMessage(res);
};
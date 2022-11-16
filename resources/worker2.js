onmessage = (e) => {
    let res;
    setTimeout(() => {
        let start = e.data;
        for (let i = 0; i < 1000000000; i++) { }
        const edt = Date.now();
        res = (edt - start);
        // 작업 결과를 html에게 전송한다.
        postMessage(res);
    }, 0);
    
    
};
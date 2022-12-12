const myLogger = store => next => action => {
    // 동작을 로깅하기
    // console.log(action);

    // 다음 미들웨어나 리듀서에게 전달
    const result = next(action);

    // 작업이 끝난 후 확인
    // console.log(result);
    // 상태 확인
    // console.log(store.getState());

    return result;

};

export default myLogger;
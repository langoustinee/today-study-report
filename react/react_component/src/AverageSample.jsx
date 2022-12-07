import React, {useState, useMemo, useCallback} from "react";

// 배열의 평균을 구해서 리턴하는 함수 - 컴포넌트와 상관이 없는 함수이다.
const getAverage = (numbers) => {
    console.log("평균값을 계산합니다.");
    if(numbers.length === 0) return 0;
    // reduce: 배열을 순회하면서 연산을 수행한 후 하나의 값을 리턴한다.
    // 매개변수는 2개인데 첫번째 매개변수는 수행할 함수, 두번째 매개변수는 연산을 시작할 때의 초기값이다.
    // 두번째 매개변수를 생략하면 배열의 첫번쨰 요소로 설정한다.
    // 첫번째 매개변수인 함수는 매개변수를 4개까지 갖는다.(첫번쨰: 누적값, 두번째: 배열의 요소, 세번째: 배열의 인덱스, 네번째: 배열)
    const sum = numbers.reduce((a, b) => a + b);
    return sum / numbers.length;
};

const Average = () => {
    // 숫자 배열
    const [list, setList] = useState([]);
    // 입력받은 숫자
    const [number, setNumber] = useState("");
    
    // input 변경시 호출되는 메서드 - useCallback 사용
    const onChange = useCallback((e) => {
        setNumber(e.target.value);
    }, []);
    
    // 버튼 클릭시 호출되는 메서드 - useCallback 사용
    const onInsert = useCallback((e) => {
        const nextList = list.concat(parseInt(number));
        setList(nextList);
        setNumber("");
    }, [number, list]);

    // useMemo를 이용하여 getAverage 호출
    // list에 변화가 생겼을 경우에만 메서드를 호출하고, 변경이 없다면 결과 재사용한다.
    const avg = useMemo(() => getAverage(list), [list]);

    return(
        <div>
            <input 
                value={number}
                onChange={onChange}
            />
            <button
                onClick={onInsert}
            >추가하기</button>
            <br/>
            <ul>
                {list.map((value, idx) => {
                    // 반복문 도릴
                    <li key={idx}>{value}</li>
                })}
            </ul>
            <div>
                <h2>평균값: {avg}</h2>
            </div>
        </div>
    );

};

// React.memo 사용
// 컴포넌트의 props 가 바뀌지 않았다면 리렌더링을 방지하여 컴포넌트의 리렌더링 성능 최적화를 해줄 수 있는 함수
export default React.memo(Average);
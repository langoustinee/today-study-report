// class형 컴포넌트 생성
// import React, { Component } from 'react';
// class StateComponent extends Component {
//     state = {
//         number: 0,
//     };
//     render() {
//         // const number = this.state; // state 를 조회 할 때에는 this.state 로 조회한다.
//             return(
//                 <div>
//                     <h1>숫자 : {this.state.number}</h1>
//                     <button
//                         onClick={(e) => {
//                             this.setState(
//                                 // prevState => {return {number: prevState.number + 1}}
//                                 { number: this.state.number+1 },
//                                 () => {
//                                     console.log("state의 값 변경됨.", this.state);
//                                 }
//                             );
//                         }}
//                     >+1 증가</button>
//                 </div>
//             );
//     }
// }

// 함수형 컴포넌트 생성
import React, { useState } from "react";
const StateComponent = () => {
    // 함수형 컴포넌트에서 state를 생성
    const [ message, setMessage ] = useState();
    const onClickEnter = () => { setMessage("안녕하세요!"); };
    const onClickLeave = () => { setMessage("안녕히 가세요!"); };

    const [ color, setColor ] = useState("orange");

    // 객체나 배열 수정시에는 복사본을 만들고 수정해야 한다.
    // const obj = { "name":"lango", "age":28 };
    // console.log(obj.name);
    
    // obj 속성 직접 수정하는 방법
    // obj.name = "hong";

    // obj를 복제해서 name을 수정
    // const copyObj = {...obj, "name":"hong"};
    // console.log(copyObj.name);

    // let arr = [1,2,3];
    // 배열에 직접추가하는 것은 지양하자.
    // arr.push(4);

    // 기존 배열을 복제해서 추가하자.
    // let copyArr = arr.concat(4);
    // console.log(copyArr);


    return(
        <>
            <button onClick={onClickEnter}>입실</button>
            <button onClick={onClickLeave}>퇴실</button>
            <br/>
            <h1 style={{color}}>{message}</h1>
            <br/>
            <button style={{ color: 'red' }} onClick={() => setColor('red')}>빨간색</button>
            <button style={{ color: 'green' }} onClick={() => setColor('green')}>초록색</button>
            <button style={{ color: 'blue' }} onClick={() => setColor('blue')}>파란색</button>
        </>
    );
};

export default StateComponent;
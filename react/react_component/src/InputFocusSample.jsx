// state, ref 모두 사용하는 컴포넌트

import React, {useState, useRef} from "react";

const InputFocusSample = () => {
    // 2개의 속성을 가진 state 생성
    const [inputs, setInputs] = useState({
        name:"",
        nickName:""
    });

    // state를 편리하게 사용하기 위해서 비구조화 할당
    const {name, nickname} = inputs;

    // react에서 다른 컴포넌트나 DOM을 참조할 수 있는 변수를 생성
    const nameInput = useRef();

    const onChange = (e) => {
        setInputs({
            [e.target.name]: e.target.value
        });
    };

    const onReset = (e) => {
        setInputs({
            name:"",
            nickname:""
        });
        
        // 이름 입력란으로 focus 설정
        nameInput.current.focus();
    };

    return(
        <div>
            <input 
                name="name" 
                value={name}
                onChange={onChange}
                ref={nameInput}
            />
            
            <input 
                name="nickname"
                value={nickname}
                onChange={onChange}
            />
            
            <button onClick={onReset}>초기화</button>
        </div>
    );

};

export default InputFocusSample;
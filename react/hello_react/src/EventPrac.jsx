// class형 컴포넌트
// import React, { Component } from 'react';
// class EventPrac extends Component {
//     // input을 여러개 만들 경우 input의 name가 state의 name을 맞춰주면 값이 변경될 때 state의 저장하는 부분을 쉽게 구현할 수 있다.
//     state = {
//         name:"",
//         message:""
//     }

//     // babel이 이 인스턴스의 메서드로 변환을 자동으로 수행
//     // input onChange 이벤트에서 생성자없이 this.handeChange로 아래 메서드를 사용할 수 있다.

//     handleChange = (e) => {
//         this.setState({
//             // 이벤트가 발생한 객체는 e.target인데, e.target.name은 이벤트가 발생한 객체의 이름이다.
//             [e.target.name]: e.target.value
//         });
//     };

//     handleClick = (e) => {
//         alert(this.state.name + ":" + this.state.message);
//         this.setState({
//             name:"",
//             message:""
//         });
//     };

//     handleKeyPress = (e) => {
//         if(e.key === "Enter") {
//             this.handleClick();
//         }
//     };

//     // constructor(props) {
//     //     super(props);
//     //     this.handleChange = this.handleChange.bind(this);
//     //     this.handleClick = this.handleClick.bind(this);
//     // };

//     render() {    
//         return (
//             <div>
//                 <h1>이벤트 연습</h1>
//                 <input type="text"
//                 name="message"
//                 placeholder='메시지를 입력하세요.'
//                 value = { this.state.message }
//                 onChange = { this.handleChange }
//                 onKeyPress = { this.handleKeyPress }
//                 />
//                 <br/>
//                 <input type="text"
//                 name="name"
//                 placeholder='이름을 입력하세요.'
//                 value = { this.state.name }
//                 onChange = { this.handleChange }
//                 onKeyPress = { this.handleKeyPress }
//                 />

//                 <button onClick={ this.handleClick }>제출</button>
//             </div>
//         );
//     }
// };
// export default EventPrac;

// 함수형 컴포넌트 생성
import React, { useState } from 'react';
const EventPrac = () => {
    // const [ name, setName ] = useState("");
    // const [ message, setMessage ] = useState("");

    const [form, setForm] = useState({
        username:"",
        message:""
    });
    const {username, message} = form;

    const onChange = (e) => {
        // form을 복제해서 e.target.name에 해당하는 속성만 e.target.value로 수정한다.
        // readt에서는 state를 수정할 때 복제해서 수정하기 때문에 하나의 항목으로 만들어진 데이터는 바로 수정하면 되지만 
        // 여러 항목으로 구성된 객체나 배열을 수정할 때는 복제해서 수정해야 한다.
        // javascript에서 객체와 배열을 복제한 후 작업하는 방법은 꼭 알아두어야 한다.
        const nextForm = {
            ...form, // 기존의 form 내용을 이 자리에 복사 한 뒤
            [e.target.name]: e.target.value // 원하는 값을 덮어씌우기
        };

        setForm(nextForm);
    };

    const onClick = (e) => {
        alert(username + ":" + message);
        setForm({
            username:"",
            message:""
        });
    };
    
    const onKeyPress = (e) => {
        if(e.key === "Enter") onClick();
    };


    return(
        <>
            <input type="text" name="username" placeholder="이름을 입력" value={username} onChange={ onChange } />
            <br/>
            <input type="text" name="message" placeholder="메시지를 입력" value={message} onChange={ onChange } onKeyPress={ onKeyPress } />
            <button onClick={ onClick }>제출</button>
        </>
    );
};

export default EventPrac;


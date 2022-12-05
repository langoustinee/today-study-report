// class형 컴포넌트 생성
import React, { Component } from "react";
import './VaildationSample.css';

class VaildationSample extends Component {
    
    // Ref(다른 DOM 객체를 참조할 수 있는 속성) 생성
    // input = React.createRef();



    // state는 class 안의 멤버변수나 함수안의 지역변수처럼 생각하면 된다.
    // state는 변경이 되면 화면에 바로 적용된다.
    state = {
        password: "",
        clicked: false,
        vaildated: false
    };

    // input 입력값이 변경되었을 경우 처리하는 이벤트
    // 자신의 name과 
    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    // 버튼 클릭시 처리하는 이벤트
    handleButtonClick = (e) => {
        this.setState({
            clicked: true,
            vaildated: this.state.password === "0000"
        });

        // input을 createRef 함수로 만든 경우 input이 가리키는 객체에 focus를 설정한다.
        // this.input.current.focus();
        this.input.focus();
    };

    // 클래스형 컴포넌트에서 화면에 출력할 내용을 반환하는 함수
    render() {
        return(
            <div>
                <input type="password"
                name="password"
                // ref={ this.input }
                ref={ ref => this.input = ref }
                value={ this.state.password } 
                onChange={ this.handleChange } 
                className={ this.state.clicked?this.state.vaildated?"success":"failure":"" } 
                />
                <br/>
                <button onClick={ this.handleButtonClick }>검증</button>
            </div>
        );
    };
};

export default VaildationSample;
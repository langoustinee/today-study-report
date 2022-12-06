import React, { Component } from "react";

class ErrorBoundary extends Component {
    
    state = {
        error: false
    };

    // component에서 예외 발생시 호출되는 메서드
    componentDidCatch(error, info) {
        this.setState({
            error: true
        });
        console.log({ error, info });
    }

    render() {
        if(this.state.error) {
            return(
                <div>에러가 발생하였습니다.</div>
            );
        }
        // error가 false이면 하위 컴포넌트 출력
        else return this.props.children;
    }
}

export default ErrorBoundary;
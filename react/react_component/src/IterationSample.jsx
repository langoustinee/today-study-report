// class형 컴포넌트 생성
import React, { Component } from "react";
class IterationSample extends Component {

    // 내용이 변경되면 컴포넌트를 리렌더링하는 state를 생성
    state = {
        cafes: ["Twosome", "STARBUCKS", "angelinus", "Hollys", "EDIYA"],
        cafe:""
    };

    // input에 입력하면 cafe state의 값을 변경하는 이벤트 처리함수
    handleChange = (e) => {
        this.setState({
            cafe: e.target.value
        });
    };

    // cafe의 값을 cafes에 추가하는 함수 - 버튼 클릭 이벤트
    handleInsert = (e) => {
        this.setState({
            // 원본에 영향을 주는 push 대신, 배열을 복제해서 연결하는 concat 함수를 이용한다.
            cafes: this.state.cafes.concat(this.state.cafe),
            cafe:""
        });
    };

    // 데이터 삭제 함수 - idx를 매개변수로 받아서 삭제한다.
    handleRemove = (idx) => {
        // 삭제 확인 대화상자 출력
        if(window.confirm("정말로 삭제하시겠습니까?") === false) return;

        const { cafes }  = this.state; // const cafes = this.state.cafes
        // slice를 이용한 삭제 - 매개변수를 2개 받아서 배열을 잘라내고 복제하여 리턴
        // 2개의 매개변수는 시작위치와 마지막 위치를 대입
        // this.setState({
        //     cafes: [
        //         cafes.slice(0, idx), 
        //         cafes.slice(idx+1, cafes.length)
        //     ]
        // });

        // filter 함수 적용
        // 받은 인덱스 값과 배열의 인덱스가 다른 것만 추출한다.
        this.setState({
            cafes: cafes.filter((item, e) => e !== idx)
        });
    };

    render() {
        const cafeList = this.state.cafes.map((cafe, idx) => 
            <li key={idx} onDoubleClick={ () => this.handleRemove(idx) }>
                {cafe}
                <button onClick={ () => this.handleRemove(idx) }>삭제</button>
            </li>);

        return(
            <div>
                { this.state.missing.value }
                <input
                    value={this.state.cafe}
                    onChange={this.handleChange}
                />
                <button onClick={this.handleInsert}>추가</button>
                <ul>{cafeList}</ul>
            </div>
        );
    }
}


// import React from "react";

// const IterationSample = () => {
//     const domains = ["naver", "google", "kakao", "daum", "yahoo"];
//     // 배열을 순회하면서 li 태그로 감싸기
//     // 보통 key는 Component를 렌더링 했을 때 어떤 원소에 변동이 있었는지 알아내기 위해 사용된다.
//     const domainList = domains.map((domain, idx) => <li key={idx}>{domain}</li>);
//     return(
//         <ul>
//             {domainList}
//         </ul>
//     );
// };

export default IterationSample;

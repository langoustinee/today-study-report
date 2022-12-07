import React, {Component, useEffect, useState, useRef, useMemo, useCallback} from "react";
import InputFocusSample from "./InputFocusSample";
import UserList from "./UserList";
import UserCreation from "./UserCreation";
import Average from "./AverageSample";

// 클래스형 컴포넌트 생성
// import React, { Component } from "react";
// import ErrorBoundary from "./ErrorBoundary";
// import IterationSample from "./IterationSample";

// class App extends Component {
//   render() {
//     return(
//       <div>
//         <IterationSample />
//       </div>
//     );
//   }
// }
// export default App;

// 클래스형 컴포넌트에서의 state 사용
class ClassState extends Component {
  // 아래 코드는 자바스크립트 문법이 아니다.
  // 리액트의 babel이 this를 자동으로 제공해준다.
  // 생성자를 만들지 않고도 state를 초기화 할 수 있다.
  // state = {
  //   count: 0
  // };
  constructor(props) {
    super(props);
    this.state = {
      count: 0
    };
  };
  render() {
    return(
      <div>
        <h1>클릭을 {this.state.count} 번 하였습니다!</h1>
        <button onClick={
          e => this.setState({
            count: this.state.count+1
          })
        }>누르세요</button>
      </div>
    );
  }
}

// 함수형 컴포넌트에서 state 사용하기
// 함수형 컴포넌트에서는 this를 사용할 수 없다.
const FunctionState = () => {
  const [count, setCount] = useState(0);
  return(
    <div>
       <h1>클릭을 {count} 번 하였습니다!</h1>
        <button onClick={ e => setCount(count+1) }>누르세요</button>
    </div>
  );
}

// class형 컴포넌트 생성
class ClassEffect extends React.Component {
  // 생성자
  constructor(props) {
    super(props);
    console.log("생성자 호출");
    this.state = {
      count:0
    };
  };
  // component가 mount된 후 호출되는 메서드
  componentDidMount() {
    console.log("mount 이후 호출");
    document.title = `You Clicked ${this.state.count} times.`;
  }
  // component가 update된 후 호출되는 메서드
  componentDidUpdate() {
    console.log("update 이후 호출");
    document.title = `You Clicked ${this.state.count} times.`;
  }
  render() {
    return(
      <div>
        <h2>You CLicked {this.state.count} times</h2>
        <button onClick={
          (e) => this.setState({ count: this.state.count+1 })  
        }>+1</button>
      </div>
    );
  }
}

// 함수형 컴포넌트 생성
const FunctionEffect = () => {
  // class 컴포넌트의 생성자와 동일한 기능을 한다.
  const [count, setCount] = useState(0);

  // component가 mount된 후 호출되는 메서드
  useEffect(() => {
    console.log("마운트와 업데이트가 끝난 후 호출");
    document.title = `You Clicked ${count} times`;
  }, [count]);

  return(
    <div>
      <h2>You CLicked {count} times</h2>
      <button onClick={
        (e) => setCount(count+1)
      }>+1</button>
    </div>
  );
};

// active가 true인 데이터의 개수를 가져오는 함수형 컴포넌트 생성
const countActiveUser = (users) => {
  console.log("사용자 수를 카운트합니다.");
  return users.filter(user => user.active).length;
};

// App()
const App = () => {
  // 배열의 데이터를 수정하면 컴포넌트가 리렌더링 될 수 있도록 state로 생성
  const [users, setUsers] = useState([
    {id:1, username:"lango", email:"lango@google.com", active: false},
    {id:2, username:"hong", email:"hong@naver.com", active: false},
    {id:3, username:"hoihoi", email:"hoi2@kakao.com", active: true}
  ]);

  const [inputs, setInputs] = useState({
    username:"",
    email:""
  });

  const {username, email} = inputs;

  const onChange = useCallback((e) => {
    setInputs({
      ...inputs,
      [e.target.name]: e.target.value
    });
  }, [inputs]);

  // 최신 id 변수 생성
  const nextId = useRef(4);

  // 데이터 삽입 메서드
  const onCreate = useCallback(() => {
    // 하나의 객체 생성
    const user = {
      id:nextId.current,
      username,
      email
    };
    // users에 user 추가
    setUsers([...users, user]);

    // 입력요소 초기화
    setInputs({
      username:"",
      email:""
    });

    // 최신 id를 부여하기 위해 id 증가
    nextId.current += 1;
  }, [users, username, email]);

  // 데이터 삭제 메서드
  const onRemove = useCallback((id) => {
    // users state에서 동일한 id를 가진 데이터 삭제
    // id가 일치하지 않는 데이터만 삭제
    // 실제로 id가 일치하지 않는 데이터만 가지고 배열을 만들어서 수정한다.
    setUsers(users.filter(user => user.id !== id));
  }, [users]);

  // 데이터 수정 메서드
  // id에 해당하는 데이터의 active 속성의 값을 반전시킨다.
  const onToggle = useCallback((id) => {
    setUsers(users.map(
        user => user.id === id ? {...user, active: !user.active} : user
      ));
  }, [users]);

  // 활성화된 user 개수를 세는 함수 호출
  // users에 변화가 생길 때만 함수를 호출하고 그 이외의 경우는 결과를 복사하도록 수정.
  // const count = countActiveUser(users);
  const count = useMemo(() => countActiveUser(users), [users]);

  return(
    <>
      {/* <ClassState/> */}
      <br/>
      {/* <FunctionState/> */}
      <br/>
      {/* <InputFocusSample/> */}
      <br/>
      {/* <ClassEffect/> */}
      <br/>
      {/* <FunctionEffect/> */}
      <br/>
      <br/>

      <div>
        {/* <UserCreation 
          username={username} 
          email={email} 
          onChange={onChange} 
          onCreate={onCreate}
        /> */}
        {/* <UserList users={users} onRemove={onRemove} onToggle={onToggle} />  
        <h4>활성화된 사용자 수: {count}</h4> */}
      </div>

      <br/>
      <br/>
      <Average/>
    </>
  )
}

export default App;
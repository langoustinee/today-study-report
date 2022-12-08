import React, { useState, useRef, useCallback, useReducer } from "react";
import ToDoTemplate from "./components/ToDoTemplate"
import ToDoInsert from "./components/ToDoInsert";
import ToDoList from "./components/ToDoList";


// 약 1000개 대량의 데이텅를 생성해서 리턴하는 함수
const createBulkTodos = () => {
  const arr = [];
  for (let i = 0; i < 1000; i++) {
    arr.push({
      id: i,
      text: `${i}번째 할 일`,
      checked: false
    })
  }
  return arr;
};

// state를 조작할 reducer 함수
const todoReducer = (todos, action) => {
  switch (action.type) {
    case 'INSERT':
      return todos.concat(action.todo);
    case 'REMOVE': 
      return todos.filter(todo => todo.id !== action.id);
    case 'TOGGLE':
      return todos.map(todo => todo.id === action.id ? { ...todo, checked: !todo.checked } : todo);
    default:
      return todos;
  }
};

// useRef는 변수를 생성하여 DOM에 할당하기 위함
// useCallback은 함수를 효율적으로 생성하기 위함.
// 데이터를 처리하는 기능은 state가 App.js에 존재하기에 App.js에서 만들어서 넘겨주는 구조로 구현해야 한다.
const App = () => {
  // useState에 데이터 생성하는 함수 대입시 함수() 형식으로 구문을 대입하면 데이터 생성할 때마다 리렌러딩을 하는 문제가 발생한다.
  // 함수 이름만 구문으로 대입해야 함수를 전부 수행하고 1번만 리렌더링을 수행한다.
  // const [todos, setToDos] = useState(createBulkTodos);
  
  // useReducer 사용
  const [todos, dispatch] = useReducer(todoReducer, undefined, createBulkTodos);

  // 고유값으로 사용될 ID
  const nextId = useRef(1001);

  // 일정 추가를 처리할 메서드
  // todos에 변경점 발생시 함수를 만들지만 변경점이 없다면 기존함수를 이용한다.
  const onInsert = useCallback((text) => {
    const todo = { id: nextId, text, checked: false };
    // setToDos(todos => todos.concat(todo));
    dispatch({type:'INSERT', todo});
    nextId.current += 1;
  }, []);

  // 일정 삭제를 처리할 메서드
  const onRemove = useCallback((id) => {
    // setToDos(todos => todos.filter(todo => todo.id !== id));
    dispatch({type:'REMOVE', id});
  }, []);

  // 일정 수정을 처리할 메서드 - 체크박스 클릭시 checked의 상태 변경
  // 해당 함수가 업데이트 되지 않도록 하려면 
  // useState의 setter에 함수형 업데이트를 사용하던가, useReducer를 이용해서 함수를 컴포넌트 외부에 생성하는 방법이 있다
  const onToggle = useCallback((id) => {
    // todos를 복제해서 하나씩 순회하여 todo의 id값과 매개변수로 받은 id가 같다면 checked를 반전시킨다.
    // setToDos(todos => todos.map(todo => todo.id === id ? { ...todo, checked: !todo.checked } : todo));
    dispatch({type:'TOGGLE', id});

  }, []);

  return (
    <ToDoTemplate>
      <ToDoInsert onInsert={onInsert} />
      <ToDoList todos={todos} onRemove={onRemove} onToggle={onToggle} />
    </ToDoTemplate>
  );
};

export default App;
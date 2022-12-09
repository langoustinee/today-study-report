// 타입 생성
const ADD_TODO = 'todos/ADD_TODO';
const TOGGLE_TODO = 'todos/TOGGLE_TODO';

// todo 데이터에서 사용 할 고유 id
let nextId = 1;

// action 생성 함수
export const addTodo = text => ({
    type: ADD_TODO,
    todo: {
        id: nextId++,
        text
    }
});
export const toggleTodo = id => ({
    type: TOGGLE_TODO,
    id
});

// 초기 상태 선언
// 리듀서의 초기 상태는 꼭 객체타입일 필요는 없다.
// 배열이여도 되고, 원시 타입 (숫자, 문자열, 불리언 이여도 상관 없다.
const initialState = [];

const todos = (state=initialState, action) => {
    switch (action.type) {
        case ADD_TODO:
            return state.concat(action.todo);
        case TOGGLE_TODO:
            return state.map(
                todo => todo.id === action.id // id 가 일치하면
                    ? { ...todo, done: !todo.done } // done 값을 반전시키고
                    : todo // 아니라면 그대로 둠
            );
        default:
            return state;
    }
};
export default todos;
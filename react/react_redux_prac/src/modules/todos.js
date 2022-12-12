// 타입 생성
const CHANGE_INPUT = "todos/CHANGE_INPUT";
const INSERT = "todos/INSERT";
const TOGGLE = "todos/TOGGLE";
const REMOVE = "todos/REMOVE";

// action 생성 함수
export const changeInput = input => ({
    type: CHANGE_INPUT,
    input
}
);
// 샘플 데이터 5개 삽입해놓았기에 6번부터
let id = 6;
export const insert = text => ({
    type: INSERT,
    todo: {
        id: id++,
        text,
        done:false
    }
});
export const toggle = id => ({
    type: TOGGLE,
    id
});
export const remove = id => ({
    type:REMOVE,
    id
});

// 초기 상태 선언
const initialState = {
    input:"",
    todos:[
        {
            id:1,
            text:"Spring",
            done:true
        },
        {
            id:2,
            text:"Java",
            done:true
        },
        {
            id:3,
            text:"Node.js",
            done:false
        },
        {
            id:4,
            text:"Python",
            done:false
        },
        {
            id:5,
            text:"React.js",
            done:false
        }
    ]
};

// 리듀서 함수
const todos = (state=initialState, action) => {
    switch (action.type) {
        case CHANGE_INPUT:
            return {...state, input: action.input};
        case INSERT:
            return {...state, todos: state.todos.concat(action.todo)};
        case TOGGLE:
            return {...state,
                todos: state.todos.map(todo => todo.id === action.id 
                    ? { ...todo, done:!todo.done }
                    : todo 
                )
            };
        case REMOVE:
            return {...state, todos:state.todos.filter(todo => todo.id !== action.id)};
        default:
            return state;
    }
};
export default todos;
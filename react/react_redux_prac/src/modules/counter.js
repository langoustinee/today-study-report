// 1. 타입 생성 - 매개변수를 받아서 증감, INCREASE, DECREASE
const INCREASE = "counter/INCREASE";
const DECREASE = "counter/DECREASE";

// action 생성 함수
export const increase = () => (
    { type: INCREASE }
);
export const decrease = () => (
    { type: DECREASE }
);

// 초기 상태 선언
const initialState = {
    number: 0,
    diff: 1
};

// reducer 선언
const counter = (state = initialState, action) => {
    switch (action.type) {
        case INCREASE:
            return {
                ...state,
                number: state.number + state.diff
            };
        case DECREASE:
            return {
                ...state,
                number: state.number - state.diff
            };
        default:
            return state;
    }
};

export default counter;


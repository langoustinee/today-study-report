// counter, todos 두개의 reducer를 합쳐서 하나로 export 해준다.
import { combineReducers } from 'redux';
import counter from './counter';
import todos from './todos';

const rootReducer = combineReducers({
    counter,
    todos
});

export default rootReducer;
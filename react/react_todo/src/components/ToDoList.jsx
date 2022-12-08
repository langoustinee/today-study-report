import React, {useCallback} from "react";
import ToDoListItem from "./ToDoListItem";
import "./ToDoList.scss";
import {List} from "react-virtualized";

const ToDoList = ({todos, onRemove, onToggle}) => {
    // 하나의 항목을 렌더링하기 위한 함수
    const rowRenderer = useCallback(({index, key, style}) => {
        // 출력할 데이터 가져오기
        const todo = todos[index];
        return(
            <ToDoListItem todo={todo} key={key} onRemove={onRemove} onToggle={onToggle} style={style}/>
        );
    }, [onRemove, onToggle, todos]);

    return(
        // <div className="ToDoList">
        //     {
        //         todos.map(todo => (
        //             <ToDoListItem todo={todo} key={todo.id} onRemove={onRemove} onToggle={onToggle}/>
        //         ))
        //     }
        // </div>
        <List 
        className="ToDolist" 
        width={512} 
        height={513} 
        rowCount={todos.length} 
        rowHeight={57}
        rowRenderer={rowRenderer}
        list={todos}
        style={{outline:"none"}}
        />
    );
};

export default React.memo(ToDoList);
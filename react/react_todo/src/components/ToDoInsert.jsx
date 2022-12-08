import React, {useState, useCallback} from "react";
// react-icons의 Meterial Design의 MdAdd라는 아이콘을 사용하기 위해 import한다.
import {MdAdd} from "react-icons/md";
import './ToDoInsert.scss';

const ToDoInsert = ({onInsert}) => {
    // 입력된 데이터를 저장하기 위한 state
    const [value, setValue] = useState("");

    // 입력 내용이 변경된다면 호출될 메서드
    const onChange = useCallback((e) => {
        setValue(e.target.value);
    }, []);

    // form에서 submit 이벤트가 발생했을때 호출될 메서드
    // form 안에서 submit을 하거나,  enter를 눌러도 submit 이벤트가 발생한다.
    const onSubmit = useCallback((e) => {
        const result = window.confirm("정말로 일정을 등록하시겠습니까? 추가할 일정의 내용은 " + `${value}` + "입니다.");
        if(result === false) {
            e.preventDefault();
            return;
        }

        onInsert(value);
        setValue("");
        // 기본 이벤트 제거 - form의 submit이나 a태그는 화면 전체를 갱신하기 때문에 이전 내용이 초기화됨.
        e.preventDefault();
    }, [onInsert, value]);

    return(
        <form className="ToDoInsert" onSubmit={onSubmit}>
            <input placeholder="해야할 일정을 입력하세요." value={value} onChange={onChange} />
            <button type="submit"><MdAdd/></button>
        </form>
    );
};

export default ToDoInsert;

import React, {useCallback} from "react";
import {MdCheckBoxOutlineBlank, MdCheckBox, MdRemoveCircleOutline} from "react-icons/md";
import cn from 'classnames';
import "./ToDoListItem.scss";

const ToDoListItem = ({todo, onRemove, onToggle, style}) => {
    // 넘어온 todo를 id, text, checked로 분해할당
    const {id, text, checked} = todo;

    // 실제로 일정을 삭제할 메서드
    const onDelete = useCallback(() => {
        const result = window.confirm("정말로 일정을 삭제하시겠습니까?");
        if(result === false) return;
        else onRemove(id);
    }, [onRemove, id, text]);

    return(
        <div className="ToDoListItem-virtualized" style={style}>
            <div className="ToDoListItem">
                <div className={cn("checkbox", {checked})} onClick={()=> onToggle(id)}>
                    {checked ? <MdCheckBox/> : <MdCheckBoxOutlineBlank />}
                    <div className="text">{text}</div>
                </div>
                <div className="remove" onClick={onDelete}>
                    <MdRemoveCircleOutline />
                </div>
            </div>
        </div>
    );
};

// 자신이 변경될 때만 리렌더링 되도록
export default React.memo(ToDoListItem);
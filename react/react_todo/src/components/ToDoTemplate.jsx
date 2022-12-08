// Main Component
import React from "react";
import "./ToDoTemplate.scss";

// 함수형 컴포넌트에서 매개변수(children)는 상위 컴포넌트에서 하위 컴포넌트를 만들 때 태그에서 넘겨준 속성들
// App.js 에서 <ToDoTemplate chilren="xxx" ...> 등으로 태그이름으로 넘겨주는 것을 해당 파일에서 매개변수로 받는다.
const ToDoTemplate = ({children}) => {
    return(
        <div className="ToDoTemplate">
            <div className="app-title">일정 관리</div>
            <div className="content">
                {children}
            </div>
        </div>
    );
};

export default ToDoTemplate;
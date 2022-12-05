import React from 'react';
import PropTypes from 'prop-types';

// props를 통째로 받는 것 - 비구조화 할당
// 

// name, ciildren을 각각 별도로 받아오는 것 - 구조화 할당
// 인덱스가 아닌 이름으로 접근할 수 있다.
const MyComponent = ({name, children, year}) => {
    return (
        <div>
            내가 만든 첫번째 컴포넌트의 이름은 {name} 입니다. <br/>
            태그 안의 내용은 {children} 입니다. <br/>
            제가 태어난 해는 {year} 년 입니다.

        </div>
    );
}

MyComponent.defaultProps = {
    name: "lango",
    year: 2022
}

MyComponent.propTypes = {
    name: PropTypes.string,
    year:PropTypes.number.isRequired
}

export default MyComponent;

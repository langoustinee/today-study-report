import React from 'react';
import styles from './Button.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

// props를 받아오는데 children으로 넘겨준 이유는
//  
const Button = ({ children, ...rest }) => {
    return (
        <div className={cx('button')} {...rest}>
            {children}
        </div>
    );
};

export default Button;
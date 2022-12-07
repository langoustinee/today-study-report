// CSSModule.module.css 파일의 스타일을 적용할 컴포넌트
import styles from './CSSModule.module.scss';
import classNames from 'classnames/bind';

// cs안에서는 styles를 생략하는 것이 가능하다.
const cs = classNames.bind(styles);

const CSSModule = () => {
    return(
        <div className={cs('wrapper', 'inverted')}>
            My First <span className="something">CSS Module!</span>
        </div>
    );
};

export default CSSModule;
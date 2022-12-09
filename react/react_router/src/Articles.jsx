import { Link } from 'react-router-dom';

const Articles = () => {
    return (
        <ul>
            <li>
                <Link to="/articles/1">손으로 코딩하고 뇌로 컴파일하고 눈으로 디버깅한다.</Link>
            </li>
            <li>
                <Link to="/articles/2">코드 수를 기준으로 프로그램의 진도를 측정하는 것은 비행기 제작 진도를 무게로 측정하는 것과 같다.</Link>
            </li>
            <li>
                <Link to="/articles/3">아무리 구조가 잘 되어 있더라도 프로그래머가 나쁜 프로그램을 만드는 걸 막아주는 프로그래밍 언어는 없다.</Link>
            </li>
            <li>
                <Link to="/articles/4">아무리 구조가 잘 되어 있더라도 프로그래머가 나쁜 프로그램을 만드는 걸 막아주는 프로그래밍 언어는 없다.</Link>
            </li>
        </ul>
    );
};

export default Articles;
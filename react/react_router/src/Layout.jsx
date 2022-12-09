// 공통된 레이아웃을 위한 컴포넌트
import { Outlet, useNavigate } from 'react-router-dom';

const Layout = () => {
    const navigate = useNavigate();

    // 뒤로가기 함수
    const goBack = () => {
        navigate(-1);
    };

    // articles로 이동하는 함수
    const goArticles = () => {
        navigate("/articles", {replace:true});
    };

    return (
        <div>
            <header style={{ background: 'orange', padding: 16, fontSize: 24, color: 'white'}}>
                <button onClick={goBack}>뒤로가기</button>
                <button onClick={goArticles}>게시글 목록</button>
            </header>
            <main>
                <Outlet />
            </main>
        </div>
    );
};

export default Layout;
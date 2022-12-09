import { Navigate } from "react-router-dom";

const MyPage = () => {
    const isLoggedIn = false;
    if(!isLoggedIn) return <Navigate to="/login" replace={true} />

    return (
        <div>
            <h2>My Page</h2>
        </div>
    );
};

export default MyPage;
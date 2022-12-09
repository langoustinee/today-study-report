import { useParams } from "react-router-dom"

// 출력할 데이터 생성
const data = {
    banana: {
        name: "바나나",
        description: "노란색 과일 바나나입니다."
    },
    grape: {
        name: "포도",
        description: "보라색 괴일 포도입니다."
    }
};

const Profile = () => {
    // URL 파라미터 읽기
    const params = useParams();
    // 데이터 찾아오기 - username이라는 파라미터를 찾는다.
    const profile = data[params.username];

    return (
        <div>
            <h2>Fruit Profile</h2>
            {
                profile ? (
                    <div>
                        <h3>{profile.name}</h3>
                        <p>{profile.description}</p>
                    </div>
                ) : (
                    <p>존재하지 않는 과일입니다.</p>
                )
            }
        </div>
    );
};

export default Profile;
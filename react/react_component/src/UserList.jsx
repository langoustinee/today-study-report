import React, {useEffect} from "react";

const User = ({user, onRemove, onToggle}) => {
    // 마운트 될 때, state 변경될 때 모두 호출된다.
    useEffect(() => {
        // 삽입/수정하면 호출된다.
        // 해당 데이터의 id가 존재하면 수정이고 id가 존재하지 않는다면 삽입 처리
        console.log("컴포넌트를 화면에 출력!");
        console.log(user);
        // 함수를 리턴하면 컴포넌트가 사라질 때 호출된다.
        return () => {
            // 데이터가 삭제되면 호출된다.
            console.log("컴포넌트가 사라짐..");
            console.log(user);
        };
    }, [user]);

    return(
        <div>
            <b 
                onClick={ () => onToggle(user.id) }
                style={{
                    cursor:"pointer",
                    color:user.active?"orange":"gray"
                }}
            >{user.username}</b>
            <span> ( {user.email} )</span>
            <button onClick={ () => onRemove(user.id) }>삭제</button>
        </div>
    );
};

const UserList = ({users, onRemove, onToggle}) => {
    return(
        <div>
            {users.map(
                user => <User user={user} key={user.id} onRemove={onRemove} onToggle={onToggle} />
            )};
        </div>
    );
}

export default UserList;
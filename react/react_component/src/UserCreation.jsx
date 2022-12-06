import React from "react";

const UserCreation = ({username, email, onChange, onCreate}) => {

    return(
        <div>
            <p>
                <input
                    name="username"
                    placeholder="이름을 입력하세요."
                    value={username}
                    onChange={onChange}
                    
                />
            </p>

            <p>
                <input
                    name="email"
                    placeholder="이메일을 입력하세요."
                    value={email}
                    onChange={onChange}
                />
            </p>

            <p>
                <button
                    onClick={onCreate}
                >추가하기</button>
            </p>
        </div>
    );
}

export default UserCreation;
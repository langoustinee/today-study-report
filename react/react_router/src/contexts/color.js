import { createContext } from "react";

// 공유할 데이터 생성
const ColorContext = () => {
    createContext({
        color:"orange"
    });
};

export default ColorContext;
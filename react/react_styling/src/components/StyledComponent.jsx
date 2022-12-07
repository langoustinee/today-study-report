import React from "react";
import styled from 'styled-components';

const Box = styled.div
`
    background: ${props => props.color || "blue"};
    padding: 1rem;
    display: flex;
`;

const Button = styled.button
`
    background: orange;
    color: white;
    border-radius: 5px;
    padding: 0.5rem;
    display: flex;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
    font-size: 1rem;
    font-weight: 600;

    &:hover {
        background: rgba(255, 255, 255, 0.9);
    }

    & +button {
        margin-left: 1rem;
    }

`;

const StyledComponent = () => {
    <Box color="black">
        <Button>Hello</Button>
        <Button>World</Button>
    </Box>
};
export default StyledComponent;
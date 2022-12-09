import React from "react";
import {Link} from "react-router-dom";

const Home = () => {
    return(
        <div>
            <h1>Hello World!</h1>
            <p>Main Page</p>
            {/* <Link to="/about">Introduction</Link> */}
            <ul>
                <li>
                    <Link to="/about">Introduction</Link>
                </li>
                <li>
                    <Link to="/profiles/banana">Banana is ...</Link>
                </li>
                <li>
                    <Link to="/profiles/grape">Grape is ...</Link>
                </li>
                <li>
                    <Link to="/profiles/apple">Unkwown Fruit</Link>
                </li>
                <li>
                    <Link to="/articles">Article List</Link>
                </li>
            </ul>
        </div>
    );
};

export default Home;
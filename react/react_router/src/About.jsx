import React from "react";
import { useLocation } from "react-router-dom";
import qs from "qs";

const About = () => {
    // const [searchParams, setSearchParams] = useSearchParams();
    // const detail = searchParams.get("detail");
    // const mode = searchParams.get("mode");

    // Query String을 읽을 수 있는 Hook
    const location = useLocation();

    const queryString = qs.parse(location.search, {
        ignoreQueryPrefix: true
    });

    console.log(queryString);

    return(
        <div>
            <h2>About</h2>
            <p>this project is React-Router Practice.</p>
            <hr/>
            <p>Query String: {location.search}</p>
        </div>
    );
};

export default About;
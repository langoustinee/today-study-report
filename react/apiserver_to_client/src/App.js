import React from "react";
import axios from "axios";

const App = () => {
  return(
    <div>
      <button onClick={(e) => {
          // Ajax
          let request = new XMLHttpRequest();
          request.open("GET", "https://jsonplaceholder.typicode.com/users");
          request.send("");
          
          request.addEventListener("load", () => {
            let data = JSON.parse(request.responseText);
            console.log(data);
          });
          request.addEventListener("error", (err) => {
            console.log(err);
          });
        }}>Ajax download</button>
        <br/>
        <button onClick={(e) => {
          // Fetch API -> ajax보다 가독성이 좋다.
          fetch("https://jsonplaceholder.typicode.com/users")
          .then((response) => response.json())
          .then((data) => console.log(data))
          .catch((err) => console.log(err, err.message));
        }}>Fetch API download</button>
        <br/>
        <button onClick={(e) => {
          // Axios 라이브러리 사용 -> 설치하여 import하고 사용 가능.
          axios.get("https://jsonplaceholder.typicode.com/users")
          .then(response => console.log(response.data))
          .catch((err) => console.log(err, err.message));
          
        }}>Axios download</button>
    </div>
  );
};

export default App;
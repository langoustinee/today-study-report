import CSSModule from "./CSSModule";
import styles from "./App.scss";
import classNames from "classnames/bind";
import Button from "./components/Button";
import "./App.css";
// import "./materialize-v1.0.0";
import StyledComponent from "./components/StyledComponent";
import axios from "axios";

const cs = classNames.bind(styles);

const App = () => {
  const isBlue = true;

  return(
    <div>
      <div className={cs("box", {blue: isBlue})}>
        <div className={cs("box-inside")} />
      </div>
      <CSSModule/>
      <br/>
      <br/>
      <div>
        <Button>여기를 클릭하세요.</Button>
      </div>
      <br/>
      <br/>
      <div className="nav-wrapper">
        <div>React.js</div>
      </div>
      <div>Meterial design</div>
      <br/>
      <br/>
      <div>
        <StyledComponent />
      </div>
    </div>
  );
}

export default App;
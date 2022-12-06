import React, { Component } from 'react';
import MyComponent from './MyComponent';
import StateComponent from './StateComponent';
import EventPrac from './EventPrac';
import VaildationSample from './VaildationSample';
import ScrollBox from './ScrollBox';


// 클래스형 컴포넌트
class App extends Component {
  render() {
    return (
      <div>
        <MyComponent name="lango" year={2022} >Inner Content</MyComponent>
        <br/>
        <br/>
        <StateComponent/>
        <br/>
        <br/>
        <EventPrac/>
        <br/>
        <br/>
        <VaildationSample/>
        <br/>
        <br/>
        <ScrollBox ref = { ref => {this.box = ref;} } />
        <button onClick={ (e) => {this.box.scrollToBottom()} }>맨 아래로</button>
      </div>
    );
  }
}

export default App;
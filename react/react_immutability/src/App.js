import React, { useCallback, useRef, useState } from "react";
import produce from "immer";

const App = () => {

  // 컴포넌트 내부에서 사용할 변수 생성
  const nextId = useRef(1);

  // state(수정하면 리렌더링 발생)를 생성하고 setter 함수 설정
  const [form, setForm] = useState({ name: "", username: "" });
  const [data, setData] = useState({ array: [], uselessValue: null });

  // input에 입력하는 경우 입력하는 데이터가 변경될 때 state를 수정해주는 함수
  // const onChange = useCallback((e) => {
  //   setForm({
  //     ...form,
  //     [e.target.name]: [e.target.value]
  //   });
  // }, [form]);
  // immer 사용
  const onChange = useCallback((e) => {
    setForm(
      produce(draft => {
        // draft가 form의 복제본이 되며 draft를 수정하면 immer가 알아서 form에 데이터를 전송한다.
        draft[e.target.name] = e.target.value;
      })
    );
  }, []);

  // 입력받은 데이터를 등록하는 함수 - form에서 submit 이벤트가 발생할 떄 호출됨.
  // 컴포넌트 내에서 함수를 만들 때 useCallback 안에 만드는게 웬만하면 좋다.
  // 왜냐하면 useCallback을 이용하면 두번째 매개변수로 대입된 deps 배열 안의 데이터가 변경점이 있을 경우만 새로 만들어지기 때문이다.
  // 만약 useCallback을 사용하지 않는다면 리렌더링 될때마다 함수를 새로 만들기에 비효율적이다.
  // const onSubmit = useCallback((e) => {
  //   // form submit의 기본 이벤트 제거 -> a 태그나 form의 submit을 통한 페이지 이동은 화면 전체를 새로 생성하기에 이전에 가지고 있던 내용이 초기화된다.
  //   // react, vue, angular는 SPA이기 때문에 화면에 출력된 내용과 가상의 DOM을 비교하여 변경점만 리렌더링하기 때문에 화면 전체를 다시 렌더링하는 일은 없어야 한다.
  //   e.preventDefault();
  //   const info = {
  //     id: nextId.current,
  //     name: form.name,
  //     username: form.username
  //   };
  //   setData({
  //     ...data,
  //     array: data.array.concat(info)
  //   });
  //   setForm({
  //     name: "",
  //     username: ""
  //   });
  //   nextId.current += 1;
  // }, [data, form.name, form.username]);
  // immer 활용
  const onSubmit = useCallback((e) => {
    e.preventDefault();
    const info = {
      id: nextId.current,
      name: form.name,
      username: form.username
    };
    setData(
      produce(draft => {
        draft.array.push(info);
      })
    );
    setForm({
      name:"",
      username:""
    });
    nextId.current += 1;
  }, [form.name, form.username]);


  // 데이터 삭제 함수
  // const onRemove = useCallback((id) => {
  //   setData({
  //     ...data,
  //     array: data.array.filter(info => info.id !== id)
  //   });
  // }, [data]);
  // immer 활용
  const onRemove = useCallback((id) => {
    setData(
      produce(draft => {
        draft.array.splice(draft.array.findIndex(info => info.id === id), 1);
      })
    );
  }, []);

  return (
    <div>
      <form onSubmit={onSubmit}>
        <input
          name="username"
          placeholder="아이디"
          value={form.username}
          onChange={onChange}
        />
        <input
          name="name"
          placeholder="이름"
          value={form.name}
          onChange={onChange}
        />
        <button type="submit">등록</button>
      </form>
      <div>
        <ul>
          {data.array.map(info => (
            <li key={info.id} onClick={() => onRemove(info.id)}>
              {info.username} ({info.name})
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default App;
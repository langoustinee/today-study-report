function getId(id){
    return document.getElementById(id);
}
let data = {};//전송 데이터(JSON)
let ws;
let mid = getId('mid');
let btnLogin = getId('btnLogin');
let btnSend = getId('btnSend');
let talk = getId('talk');
let msg = getId('msg');

btnLogin.onclick = function(){
    ws = new WebSocket("ws://" + "192.168.0.22:9999" + "/chat");
    console.log(ws)
    ws.onmessage = function(msg){
        let data = JSON.parse(msg.data);
        let css;

        // 전송한 사용자가 자신인지 아닌지에 따라 다르게 클래스를 적용하기
        if(data.mid === mid.value){
            css = 'class=me';
        }else{
            css = 'class=other';
        }
        let item = `<div ${css} >
                        <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
                        <span>${data.msg}</span>
                    </div>`;
        talk.innerHTML += item;
        talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
    }
}

msg.onkeyup = function(ev){
    if(ev.keyCode === 13){
        send();
    }
}

btnSend.onclick = function(){
    send();
}

function send(){
    if(msg.value.trim() !== ''){
        data.mid = getId('mid').value;
        data.msg = msg.value;
        data.date = new Date().toLocaleString();
        let temp = JSON.stringify(data);
        ws.send(temp);
    }
    msg.value ='';
}
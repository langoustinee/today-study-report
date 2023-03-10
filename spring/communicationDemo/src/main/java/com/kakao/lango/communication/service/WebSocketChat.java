package com.kakao.lango.communication.service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value="/chat")
public class WebSocketChat {

    // clients: 접속한 클라이언트 세션 정보를 저장하는 목록
    // Collections.synchronizedSet 메소드를 통해 동기화된 Set으로 만들어준다.
    // 알아서 싱글톤으로 작업한다는 뜻이다.
    // static 키워드를 붙여서 더더욱 싱글톤으로 작업하게끔 한다.
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    // 일반적으로 사용자와 관련하여서는 아래와 같이 map으로 만드는게 일반적이다.
    // private Map<String, Session> users = new HashMap<>();

    // 클라리언트가 접속 했을 때의 메소드
    @OnOpen
    public void onOpen(Session s) {
        // 클라이언트 세션 정보 출력하기
        System.out.println("open session: " + s.toString());

        // 세션 존재여부 확인하기
        if (!clients.contains(s)) {
            clients.add(s);
            System.out.println("new session open: " + s);
        } else {
            System.out.println("이미 연결된 session 입니다.");
        }
    }
    
    // 클라이언트가 메시지를 전송했을 때의 메소드
    @OnMessage
    public void onMessage(String msg, Session session) throws Exception{
        // 클라이언트가 보낸 메시지 출력
        System.out.println("receive message: " + msg);
        // BroadCast로 통신하기
        for(Session s : clients) {
            System.out.println("send data: " + msg);
            // 클라이언트에게 받은 문자열 메시지를 그대로 전송(echo)
            s.getBasicRemote().sendText(msg);
        }
    }
    
    // 클라리언트의 접속이 종료되었을 때의 메소드
    @OnClose
    public void onClose(Session s) {
        // 삭제로그 남기기
        System.out.println("session close : " + s);
        // Set에서 삭제하기
        clients.remove(s);
    }
}
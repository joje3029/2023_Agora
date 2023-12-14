package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	
	//	메시지  타입 : 입장, 채팅
    //	메시지 타입에 따라서 동작하는 구조가 달라진다.
    //	입장과 퇴장 ENTER 과 LEAVE 의 경우 입장/퇴장 이벤트 처리가 실행되고,
    //	TALK 는 말 그대로 내용이 해당 채팅방을 SUB 하고 있는 모든 클라이언트에게 전달된다.
    public enum MessageType {
        ENTER, TALK, LEAVE, BAN, DELETE;
    }
    
    private int id; //chat id
    private LocalDateTime regDate; //chat 시간
    private int discussionId; //chat이 들어가야하는 방 번호
    private int memberId; //chat 쓴 멤버 id
    private String message; // chat 내용
    private int recipientId; // 수신자 아이디 : 메시지 받는 놈 아이디
    private int banMemberId; // 강퇴 강한 놈 id
    private MessageType messageType; // 메세지 타입 -> BAN / LEAVE 그런거다 기억남.
    
    private String memberNickname; // chat 쓴놈 닉네임
    private String formatRegDate; // 날짜 및 시간 형식에 맞춰 정리된 형식의 등록 날짜
    private String sessionId; // session id
    private String recipientNickname; // 수신자 닉네임
    private String banMemberNickname; // 강퇴당한놈 닉네임
    private int changeHostId; // 위임할때 -> 난 필요 없음.
    
}
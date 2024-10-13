package com.mate.payment.service;

import com.mate.mate.chatroom.vo.ChatRoomListVO;
import com.mate.mate.chatroom.vo.ChatRoomVO;
import com.mate.mate.chatroom.vo.WriteChatRoomVO;

public interface ChatRoomService {

    // 모든 채팅방 목록을 가져오는 메소드
    public ChatRoomListVO selectAllChatRooms();

    // 새로운 채팅방을 만드는 메소드
    public boolean createNewChatRoom(WriteChatRoomVO writeChatRoomVO);

    // id 로 특정 채팅방 하나를 가져오는 메소드
    public ChatRoomVO selectOneChatRoomById(String id);

    // id 로 특정 채팅방 하나를 삭제하는 메소드
    public boolean deleteOneChatRoom(String id);
}

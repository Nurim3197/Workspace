package com.mate.bbs.dao;

import com.mate.mate.chatroom.vo.ChatRoomVO;
import com.mate.mate.chatroom.vo.WriteChatRoomVO;

import java.util.List;

public interface ChatRoomDao {

    // 채팅방 수를 반환하는 메서드 정의
    public int selectChatRoomAllCount();

    // 모든 채팅방 목록을 반환하는 메서드 정의
    public List<ChatRoomVO> selectAllChatRoom();

    // 새로운 채팅방을 생성하는 메서드 정의
    public int insertNewChatRoom(WriteChatRoomVO writeChatRoomVO);

    // 특정 ID 를 가진 채팅방의 정보를 반환하는 메서드 정의
    public ChatRoomVO selectChatRoomById(String id);

    // 특정 ID 를 가진 채팅방을 삭제(soft delete)하는 메서드 정의
    public int deleteChatRoomById(String id, boolean isDeleted);

}

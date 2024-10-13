package com.mate.review.service.impl;

import com.mate.mate.chatroom.dao.ChatRoomDao;
import com.mate.mate.chatroom.service.ChatRoomService;
import com.mate.mate.chatroom.vo.ChatRoomListVO;
import com.mate.mate.chatroom.vo.ChatRoomVO;
import com.mate.mate.chatroom.vo.WriteChatRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

    @Override
    public ChatRoomListVO selectAllChatRooms() {
        ChatRoomListVO chatRoomListVO = new ChatRoomListVO();

        chatRoomListVO.setChatCnt(chatRoomDao.selectChatRoomAllCount());

        chatRoomListVO.setChatRoomVOList(chatRoomDao.selectAllChatRoom());

        return chatRoomListVO;
    }

    @Override
    public boolean createNewChatRoom(WriteChatRoomVO writeChatRoomVO) {
        int createCount = chatRoomDao.insertNewChatRoom(writeChatRoomVO);
        return createCount > 0;
    }

    @Override
    public ChatRoomVO selectOneChatRoomById(String id) {

        ChatRoomVO chatRoomVO = this.chatRoomDao.selectChatRoomById(id);
        if(chatRoomVO == null){
            throw new IllegalArgumentException("잘못된 접급입니다.");
        }
        return chatRoomVO;
    }

    @Override
    public boolean deleteOneChatRoom(String id) {

        ChatRoomVO chatRoomVO = this.chatRoomDao.selectChatRoomById(id);

        int deleteCount = this.chatRoomDao.deleteChatRoomById(id, true);

        return deleteCount > 0;
    }
}

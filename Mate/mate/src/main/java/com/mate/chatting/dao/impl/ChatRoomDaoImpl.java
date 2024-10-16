package com.mate.chatting.dao.impl;

import com.mate.mate.chatroom.dao.ChatRoomDao;
import com.mate.mate.chatroom.vo.ChatRoomVO;
import com.mate.mate.chatroom.vo.WriteChatRoomVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomDaoImpl extends SqlSessionDaoSupport implements ChatRoomDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int selectChatRoomAllCount() {
        return getSqlSession().selectOne("selectChatRoomAllCount");
    }

    @Override
    public List<ChatRoomVO> selectAllChatRoom() {
        return getSqlSession().selectList("selectAllChatRoom");
    }

    @Override
    public int insertNewChatRoom(WriteChatRoomVO writeChatRoomVO) {
        return getSqlSession().insert("insertNewChatRoom", writeChatRoomVO);
    }

    @Override
    public ChatRoomVO selectChatRoomById(String id) {
        return getSqlSession().selectOne("selectChatRoomById", id);
    }

    @Override
    public int deleteChatRoomById(String id, boolean isDeleted) {
        return getSqlSession().update("deleteChatRoomById", id);
    }

}

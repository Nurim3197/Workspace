package com.mate.message.service.impl;

import com.mate.mate.message.dao.MessageDao;
import com.mate.mate.message.service.MessageService;
import com.mate.mate.message.vo.MessageVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    public MessageVO selectOneMesssage(String id) {
        return null;
    }

    public List<MessageVO> selectAllMesssage() {

        List<MessageVO> messageList = new ArrayList<MessageVO>();
        return messageList;
    }

    @Override
    public boolean createNewMessage(MessageVO messageVO) {
        int createCount = MessageDao.insertNewMessage(messageVO);
        return createCount > 0;
    }

    @Override
    public boolean deleteOneMessage(String id) {
        return false;
    }
}

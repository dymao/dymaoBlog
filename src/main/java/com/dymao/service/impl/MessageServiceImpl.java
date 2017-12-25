package com.dymao.service.impl;

import com.dymao.dao.mapper.MessageMapper;
import com.dymao.model.Message;
import com.dymao.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-23 21:27
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageMapper messageMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public Message selectByPrimaryKey(String id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Message message) {
        return messageMapper.updateByPrimaryKey(message);
    }

    @Override
    public List<Message> findAllByCondition(Map map) {
        return messageMapper.findAllByCondition(map);
    }
}

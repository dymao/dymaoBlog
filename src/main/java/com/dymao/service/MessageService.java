package com.dymao.service;

import com.dymao.model.Message;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-23 21:27
 */
public interface MessageService {

    int deleteByPrimaryKey(String id);

    int insert(Message message);

    Message selectByPrimaryKey(String id);

    int updateByPrimaryKey(Message message);

    Integer deleteByMessageIds(List<String> messageIds);

    List<Message> findAllByCondition(Map map);

    int findAllByConditionCount(Map map);
}

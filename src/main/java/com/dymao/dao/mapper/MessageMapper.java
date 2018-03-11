package com.dymao.dao.mapper;

import com.dymao.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    int deleteByPrimaryKey(String id);

    int insert(Message message);

    Message selectByPrimaryKey(String id);

    int updateByPrimaryKey(Message message);

   Integer deleteByMessageIds(List<String> messageIds);

    List<Message> findAllByCondition(Map map);

    int findAllByConditionCount(Map map);
}
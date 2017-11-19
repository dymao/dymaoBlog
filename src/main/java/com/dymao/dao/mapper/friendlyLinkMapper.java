package com.dymao.dao.mapper;

import com.dymao.model.friendlyLink;

public interface friendlyLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(friendlyLink record);

    int insertSelective(friendlyLink record);

    friendlyLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(friendlyLink record);

    int updateByPrimaryKey(friendlyLink record);
}
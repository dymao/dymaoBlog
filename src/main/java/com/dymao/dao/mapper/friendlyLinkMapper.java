package com.dymao.dao.mapper;

import com.dymao.model.FriendlyLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendlyLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendlyLink friendlyLink);

    int insertSelective(FriendlyLink friendlyLink);

    FriendlyLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendlyLink friendlyLink);

    int updateByPrimaryKey(FriendlyLink friendlyLink);
}
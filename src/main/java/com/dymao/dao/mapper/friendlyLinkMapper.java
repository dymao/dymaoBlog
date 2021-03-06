package com.dymao.dao.mapper;

import com.dymao.model.FriendlyLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendlyLinkMapper {

    int deleteByPrimaryKey(String id);

    int insert(FriendlyLink friendlyLink);

    FriendlyLink selectByPrimaryKey(String id);

    int updateByPrimaryKey(FriendlyLink friendlyLink);

    List<FriendlyLink> findFriendlinkList(Map map);

    int findFriendlinkListCount(Map map);

    int deleteByFriendlinkIds(List<String> friendlinkIds);
}
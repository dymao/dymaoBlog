package com.dymao.service;

import com.dymao.model.FriendlyLink;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/8 16:54
 */
public interface FriendLinkService {

    int deleteByPrimaryKey(String id);

    int insert(FriendlyLink friendlyLink);

    FriendlyLink selectByPrimaryKey(String id);

    int updateByPrimaryKey(FriendlyLink friendlyLink);

    List<FriendlyLink> findFriendlinkList(Map map);

    int findFriendlinkListCount(Map map);

    int deleteByFriendlinkIds(List<String> friendlinkIds);
}

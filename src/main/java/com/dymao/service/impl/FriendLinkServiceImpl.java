package com.dymao.service.impl;

import com.dymao.dao.mapper.FriendlyLinkMapper;
import com.dymao.model.FriendlyLink;
import com.dymao.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/8 16:59
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return friendlyLinkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FriendlyLink friendlyLink) {
        return friendlyLinkMapper.insert(friendlyLink);
    }

    @Override
    public FriendlyLink selectByPrimaryKey(String id) {
        return friendlyLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(FriendlyLink friendlyLink) {
        return friendlyLinkMapper.updateByPrimaryKey(friendlyLink);
    }

    @Override
    public List<FriendlyLink> findFriendlinkList(Map map) {
        return friendlyLinkMapper.findFriendlinkList(map);
    }

    @Override
    public int findFriendlinkListCount(Map map) {
        return friendlyLinkMapper.findFriendlinkListCount(map);
    }

    @Override
    public int deleteByFriendlinkIds(List<String> friendlinkIds) {
        return friendlyLinkMapper.deleteByFriendlinkIds(friendlinkIds);
    }
}

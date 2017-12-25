package com.dymao.dao.mapper;

import com.dymao.model.AdminUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdminUser adminUser);

    AdminUser selectByPrimaryKey(String id);

    int updateByPrimaryKey(AdminUser adminUser);

    List<AdminUser> queryUserByMap(Map map);
}
package com.dymao.dao.mapper;

import com.dymao.model.Blog;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog blog);

    int insertSelective(Blog blog);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog blog);

    int updateByPrimaryKeyWithBLOBs(Blog blog);

    int updateByPrimaryKey(Blog blog);
}
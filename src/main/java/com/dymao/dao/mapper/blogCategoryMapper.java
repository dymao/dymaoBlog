package com.dymao.dao.mapper;

import com.dymao.model.blogCategory;

public interface blogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(blogCategory record);

    int insertSelective(blogCategory record);

    blogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(blogCategory record);

    int updateByPrimaryKey(blogCategory record);
}
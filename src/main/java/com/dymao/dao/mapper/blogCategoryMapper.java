package com.dymao.dao.mapper;

import com.dymao.model.BlogCategory;

public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory blogCategory);

    int insertSelective(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogCategory blogCategory);

    int updateByPrimaryKey(BlogCategory blogCategory);
}
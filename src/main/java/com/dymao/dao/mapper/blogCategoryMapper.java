package com.dymao.dao.mapper;

import com.dymao.model.BlogCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BlogCategory blogCategory);

    List<BlogCategory> findCategoryList(@Param("level") Integer level);
}
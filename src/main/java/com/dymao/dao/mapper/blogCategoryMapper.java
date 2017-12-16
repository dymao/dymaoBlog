package com.dymao.dao.mapper;

import com.dymao.model.BlogCategory;
import com.dymao.vo.CategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogCategoryMapper {
    Integer deleteByPrimaryKey(String id);

    int insert(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(String id);

    int updateByPrimaryKey(BlogCategory blogCategory);

    List<BlogCategory> findCategoryList(@Param("level") Integer level);

    List<CategoryInfo> queryCategoryInfoList(@Param("level") Integer level);

    int queryCategoryInfoListCount(@Param("level") Integer level);

    Integer deleteByCategoryIds(List<String> categoryIds);

    List<BlogCategory> findCategoryListByMap(Map map);


}
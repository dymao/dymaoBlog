package com.dymao.service;

import com.dymao.model.BlogCategory;
import com.dymao.vo.CategoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/6 22:57
 */
public interface BlogCategoryService {

    int deleteByPrimaryKey(String id);

    int insert(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(String id);

    int updateByPrimaryKey(BlogCategory blogCategory);

    List<BlogCategory> findCategoryList(Integer level);

    List<CategoryInfo> queryCategoryInfoList(@Param("level") Integer level);

    int  queryCategoryInfoListCount(Integer level);

    Integer deleteByCategoryIds(List<String> categoryIds);
}

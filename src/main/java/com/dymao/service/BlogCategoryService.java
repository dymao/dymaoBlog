package com.dymao.service;

import com.dymao.model.BlogCategory;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/6 22:57
 */
public interface BlogCategoryService {

    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BlogCategory blogCategory);

    List<BlogCategory> findCategoryList(Integer level);
}

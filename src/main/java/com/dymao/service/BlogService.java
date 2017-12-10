package com.dymao.service;

import com.dymao.model.Blog;
import com.dymao.vo.BlogVo;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/9 21:20
 */
public interface BlogService {

    int deleteByPrimaryKey(String id);

    int insert(Blog blog);

    Blog selectByPrimaryKey(String id);

    int updateByPrimaryKey(Blog blog);

    List<BlogVo> selectBlogList(Map map);
}

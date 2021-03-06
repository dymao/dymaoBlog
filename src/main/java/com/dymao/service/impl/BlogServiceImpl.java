package com.dymao.service.impl;

import com.dymao.dao.mapper.BlogMapper;
import com.dymao.model.Blog;
import com.dymao.service.BlogService;
import com.dymao.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/9 21:23
 */
@Service
public class BlogServiceImpl  implements BlogService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Blog blog) {
        return blogMapper.insert(blog);
    }

    @Override
    public Blog selectByPrimaryKey(String id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Blog blog) {
        return blogMapper.updateByPrimaryKey(blog);
    }

    @Override
    public List<BlogVo> selectBlogList(Map map) {
        return blogMapper.selectBlogList(map);
    }

    @Override
    public List<Blog> selectHotBlogList(Map map) {
        return blogMapper.selectHotBlogList(map);
    }

    @Override
    public Blog selectPreBlog(Map map) {
        return blogMapper.selectPreBlog(map);
    }

    @Override
    public Blog selectNextBlog(Map map) {
        return blogMapper.selectNextBlog(map);

    }

    @Override
    public int updateViewNumByPrimaryKey(Map map) {
        return blogMapper.updateViewNumByPrimaryKey(map);
    }

    @Override
    public int updateLikeNumByPrimaryKey(Map map) {
        return blogMapper.updateLikeNumByPrimaryKey(map);
    }

    @Override
    public int updateTreadNumByPrimaryKey(Map map) {
        return blogMapper.updateTreadNumByPrimaryKey(map);
    }

    @Override
    public List<Map> selectBlogArchiveList(Map map) {
        return blogMapper.selectBlogArchiveList(map);
    }
}

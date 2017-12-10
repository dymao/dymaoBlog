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
}

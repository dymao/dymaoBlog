package com.dymao.service.impl;

import com.dymao.dao.mapper.BlogCategoryMapper;
import com.dymao.model.BlogCategory;
import com.dymao.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/6 23:03
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

   public int deleteByPrimaryKey(Integer id){
        return blogCategoryMapper.deleteByPrimaryKey(id);
    }

    public int insert(BlogCategory blogCategory){
        return blogCategoryMapper.insert(blogCategory);
    }

    public BlogCategory selectByPrimaryKey(Integer id){
        return blogCategoryMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(BlogCategory blogCategory){
        return blogCategoryMapper.updateByPrimaryKey(blogCategory);
    }

    public List<BlogCategory> findCategoryList(Integer level){
        return blogCategoryMapper.findCategoryList(level);
    }
}

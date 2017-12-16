package com.dymao.service.impl;

import com.dymao.dao.mapper.BlogCategoryMapper;
import com.dymao.model.BlogCategory;
import com.dymao.service.BlogCategoryService;
import com.dymao.vo.CategoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/6 23:03
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

   public int deleteByPrimaryKey(String id){
        return blogCategoryMapper.deleteByPrimaryKey(id);
    }

    public int insert(BlogCategory blogCategory){
        return blogCategoryMapper.insert(blogCategory);
    }

    public BlogCategory selectByPrimaryKey(String id){
        return blogCategoryMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(BlogCategory blogCategory){
        return blogCategoryMapper.updateByPrimaryKey(blogCategory);
    }

    public List<BlogCategory> findCategoryList(Integer level){
        return blogCategoryMapper.findCategoryList(level);
    }

    public List<CategoryInfo> queryCategoryInfoList(Integer level){
        return blogCategoryMapper.queryCategoryInfoList(level);
    }

    public int queryCategoryInfoListCount(Integer level){
        return blogCategoryMapper.queryCategoryInfoListCount(level);
    }

    public Integer deleteByCategoryIds(List<String> categoryIds){
        return blogCategoryMapper.deleteByCategoryIds(categoryIds);
    }

    @Override
    public List<BlogCategory> findCategoryListByMap(Map map) {
        return blogCategoryMapper.findCategoryListByMap(map);
    }
}

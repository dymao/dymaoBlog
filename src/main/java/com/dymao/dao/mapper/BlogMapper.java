package com.dymao.dao.mapper;

import com.dymao.model.Blog;
import com.dymao.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {

    int deleteByPrimaryKey(String id);

    int insert(Blog blog);

    Blog selectByPrimaryKey(String id);

    int updateByPrimaryKey(Blog blog);

    List<BlogVo> selectBlogList(Map map);
}
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

    BlogVo selectByPrimaryKey(String id);

    int updateByPrimaryKey(Blog blog);

    List<BlogVo> selectBlogList(Map map);

    List<BlogVo> selectHotBlogList(Map map);

    List<BlogVo> selectRandomBlogList(Map map);

    BlogVo selectPreBlog(Map map);

    BlogVo selectNextBlog(Map map);

    int updateViewNumByPrimaryKey(Map map);

    int updateLikeNumByPrimaryKey(Map map);

    int updateTreadNumByPrimaryKey(Map map);

    List<Map> selectBlogArchiveList(Map map);

    int saveBlogLabels(Map map);

    int delBlogLabels(String blogId);

    List<String> queryBlogIdByLabelId(Map map);

}
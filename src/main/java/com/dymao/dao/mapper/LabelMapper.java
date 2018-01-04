package com.dymao.dao.mapper;

import com.dymao.model.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Label label);

    Label selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Label label);

    List<Label> findAllLabel(Map map);
}
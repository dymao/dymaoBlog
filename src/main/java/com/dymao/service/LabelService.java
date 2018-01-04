package com.dymao.service;

import com.dymao.model.Label;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-05 00:23
 */
public interface LabelService {

    int deleteByPrimaryKey(Integer id);

    int insert(Label label);

    Label selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Label label);

    List<Label> findAllLabel(Map map);
}

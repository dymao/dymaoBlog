package com.dymao.service.impl;

import com.dymao.dao.mapper.LabelMapper;
import com.dymao.model.Label;
import com.dymao.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-05 00:23
 */
@Service
public class LabelServiceImpl implements LabelService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return labelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Label label) {
        return labelMapper.insert(label);
    }

    @Override
    public Label selectByPrimaryKey(Integer id) {
        return labelMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Label label) {
        return labelMapper.updateByPrimaryKey(label);
    }

    @Override
    public List<Label> findAllLabel(Map map) {
        return labelMapper.findAllLabel(map);
    }
}

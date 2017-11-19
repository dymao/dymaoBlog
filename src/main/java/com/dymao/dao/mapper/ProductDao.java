package com.dymao.dao.mapper;

import com.dymao.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/12 22:04
 */
@Mapper
public interface ProductDao {

    List<Product> findByName(String name);
}

package com.lxl.common.mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    int getTotal(T record);

    List<T> selectAll(T record);

    List<T> findById(Integer id);
}

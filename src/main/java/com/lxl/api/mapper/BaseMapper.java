package com.lxl.api.mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
public interface BaseMapper<T> {

    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectAll();

    List<T> getMessById(String id);
}

package com.lxl.common.mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
public interface BaseMapper<T> {

    int deleteOneById(Integer id);

    int insert(T record);

    int insertByParams(T record);

    int updateByIdAndParams(T record);

    int findTotalByParams(T record);

    List<T> findByParams(T record);

    T findOneById(Integer id);
}

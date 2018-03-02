package com.lxl.common.mapper;

import java.util.List;
import java.util.Map;

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

    List<T> findByParamsAndPage(Map map);

    T findOneById(Integer id);
}

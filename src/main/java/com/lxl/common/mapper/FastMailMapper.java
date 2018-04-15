package com.lxl.common.mapper;

import com.lxl.common.models.FastMail;

import java.util.List;

public interface FastMailMapper extends BaseMapper<FastMail> {
    Integer receiveById(Integer id);
    List<FastMail> findByPhone(String phone);
}
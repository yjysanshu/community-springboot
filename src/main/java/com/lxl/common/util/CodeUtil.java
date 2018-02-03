package com.lxl.common.util;

import com.lxl.common.consts.CharacterConst;

import java.util.UUID;

public class CodeUtil {

    /**
     * 创建一个唯一的ID
     * @return string
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll(CharacterConst.CHARACTER_LINE, CharacterConst.CHARACTER_NULL);
    }
}

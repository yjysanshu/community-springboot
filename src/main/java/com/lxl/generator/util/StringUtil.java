package com.lxl.generator.util;

public class StringUtil {

    /**
     * 格式化数据字段，转成驼峰命名
     * @param fieldName 字段名称
     * @return 把字段转为驼峰命名
     */
    public static String formatField(String fieldName) {
        int index = fieldName.indexOf('_');
        if (index == -1) {
            return fieldName;
        }
        fieldName = fieldName.substring(0, index) +
                fieldName.substring(index + 1, index + 2).toUpperCase() +
                fieldName.substring(index + 2);

        return formatField(fieldName);
    }

    /**
     * 首字母大写
     * @param fieldName 字段名称
     * @return 首字母大写的字符串
     */
    public static String firstCharacterToUpper(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * 首字母小写
     * @param fieldName 字段名称
     * @return 首字母小写的字符串
     */
    public static String firstCharacterToLower(String fieldName) {
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }
}

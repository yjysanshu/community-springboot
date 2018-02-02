package com.lxl.generator.models;

import com.lxl.generator.consts.CommonConst;
import com.lxl.generator.consts.ModelConst;
import com.lxl.generator.util.StringUtil;

public class TableExtend extends Table {
    private String dataTypeModel;
    private String dataTypeJdbc;
    private String columnNameModel;
    private String columnNameGSet;
    private String columnNameRe;
    private String columnNameReGSet;

    public String getDataTypeModel() {
        if (this.dataTypeModel == null) {
            this.dataTypeModel = getModelType(this.getDataType());
        }
        return this.dataTypeModel;
    }

    public String getDataTypeJdbc() {
        if (this.dataTypeJdbc == null) {
            this.dataTypeJdbc = getJdbcType(this.getDataType());
        }
        return this.dataTypeJdbc;
    }

    public String getColumnNameModel() {
        if (this.columnNameModel == null) {
            this.columnNameModel = StringUtil.formatField(this.getColumnName());
        }
        return this.columnNameModel;
    }

    public String getColumnNameGSet() {
        if (this.columnNameGSet == null) {
            this.columnNameGSet = StringUtil.firstCharacterToUpper(StringUtil.formatField(this.getColumnName()));
        }
        return columnNameGSet;
    }

    public String getColumnNameRe(String modelClass) {
        if (this.columnNameRe == null) {
            this.columnNameRe = StringUtil.firstCharacterToLower(
                    StringUtil.formatField(this.getColumnName()).replaceAll(modelClass, CommonConst.CHARACTER_NULL));
        }
        return this.columnNameRe;
    }

    public String getColumnNameReGSet(String modelClass) {
        if (this.columnNameReGSet == null) {
            this.columnNameReGSet = StringUtil.formatField(this.getColumnName()).replaceAll(modelClass, CommonConst.CHARACTER_NULL);
        }
        return columnNameReGSet;
    }

    /**
     * 获取数据库类型对应的JAVA类型
     * @param dataType 数据类型
     * @return JAVA类型
     */
    private static String getModelType(String dataType) {
        switch (dataType) {
            case ModelConst.DATA_TYPE_INT:
            case ModelConst.DATA_TYPE_TINYINT:
                return ModelConst.JAVA_TYPE_INTEGER;
            case ModelConst.DATA_TYPE_ENUM:
            case ModelConst.DATA_TYPE_VARCHAR:
                return ModelConst.JAVA_TYPE_VARCHAR;
            case ModelConst.DATA_TYPE_DATETIME:
            case ModelConst.DATA_TYPE_TIMESTAMP:
                return ModelConst.JAVA_TYPE_TIMESTAMP;
        }
        return ModelConst.TYPE_ERROR;
    }

    /**
     * 获取数据库类型对应的JDBC类型
     * @param dataType 数据类型
     * @return JDBC类型
     */
    private static String getJdbcType(String dataType) {
        switch (dataType) {
            case ModelConst.DATA_TYPE_INT:
            case ModelConst.DATA_TYPE_TINYINT:
                return ModelConst.JDBC_TYPE_INTEGER;
            case ModelConst.DATA_TYPE_ENUM:
            case ModelConst.DATA_TYPE_VARCHAR:
                return ModelConst.JDBC_TYPE_VARCHAR;
            case ModelConst.DATA_TYPE_DATETIME:
            case ModelConst.DATA_TYPE_TIMESTAMP:
                return ModelConst.JDBC_TYPE_TIMESTAMP;
        }
        return ModelConst.TYPE_ERROR;
    }
}

package com.lxl.generator.service.generator;

import com.lxl.generator.consts.ModelConst;
import com.lxl.generator.models.TableExtend;
import com.lxl.generator.util.StringUtil;

import java.util.Arrays;

public class ModelResponseService extends BaseService {

    @Override
    protected void generator() {
        sb.append(ModelConst.TXT_CLASS_EXTEND_LINE.replaceAll(ModelConst.REPLACE_CLASS, this.getClassName())
                .replaceAll(ModelConst.REPLACE_PARENT, ModelConst.PARENT_CLASS_RESPONSE));
        //private行
        for (TableExtend tableExtend : list) {
            if (Arrays.asList(ModelConst.TIME_FIELD).contains(tableExtend.getColumnName()
                    .replaceAll(tableName + ModelConst.CHARACTER_UNDERLINE, ModelConst.CHARACTER_NULL))) {
                continue;
            }
            sb.append(ModelConst.TXT_FIELD_NAME
                    .replaceAll(ModelConst.REPLACE_TYPE, tableExtend.getDataTypeModel())
                    .replaceAll(ModelConst.REPLACE_COLUMN_NAME, tableExtend.getColumnNameRe(this.getLowerModelClass())));
        }
        sb.append(ModelConst.ESCAPE_WARP);

        //GETTER & SETTER
        for(TableExtend tableExtend : list) {
            if (Arrays.asList(ModelConst.TIME_FIELD).contains(tableExtend.getColumnName()
                    .replaceAll(tableName + ModelConst.CHARACTER_UNDERLINE, ModelConst.CHARACTER_NULL))) {
                continue;
            }
            //GETTER
            sb.append(ModelConst.TXT_FIELD_GETTER.replaceAll(ModelConst.REPLACE_TYPE, tableExtend.getDataTypeModel())
                    .replaceAll(ModelConst.REPLACE_UPPER_COLUMN_NAME, tableExtend.getColumnNameReGSet(this.getLowerModelClass()))
                    .replaceAll(ModelConst.REPLACE_COLUMN_NAME, tableExtend.getColumnNameRe(this.getLowerModelClass())));
            //SETTER
            sb.append(ModelConst.TXT_FIELD_SETTER.replaceAll(ModelConst.REPLACE_TYPE, tableExtend.getDataTypeModel())
                    .replaceAll(ModelConst.REPLACE_UPPER_COLUMN_NAME, tableExtend.getColumnNameReGSet(this.getLowerModelClass()))
                    .replaceAll(ModelConst.REPLACE_COLUMN_NAME, tableExtend.getColumnNameRe(this.getLowerModelClass())));
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(ModelConst.TXT_END_LINE);
    }

    @Override
    protected String getClassName() {
        if (this.className == null) {
            this.className = StringUtil.firstCharacterToUpper(StringUtil.formatField(tableName)) + ModelConst.CLASS_RESPONSE;
        }
        return this.className;
    }

    @Override
    protected String getPath() {
        if (this.path == null) {
            this.path = System.getProperty("user.dir") + ModelConst.DIR_SPLIT + ModelConst.PATH_INNER_JAVA
                    + ModelConst.DIR_SPLIT + formatPackageDir(packageName) + ModelConst.PATH_INNER_ADMIN_RESPONSE_MODELS;
        }
        return this.path;
    }

    @Override
    protected String getFullPackageName() {
        return packageName + ModelConst.PACKAGE_ADMIN_RESPONSE;
    }
}

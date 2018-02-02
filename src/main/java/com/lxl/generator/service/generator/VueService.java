package com.lxl.generator.service.generator;

import com.lxl.generator.consts.VueConst;
import com.lxl.generator.models.TableExtend;
import com.lxl.generator.util.StringUtil;

public class VueService extends BaseService {
    @Override
    protected void generator() {
        StringBuilder sbGridColumn = new StringBuilder();
        StringBuilder sbTableForm = new StringBuilder();
        StringBuilder sbCreateFormColumn = new StringBuilder();
        StringBuilder sbCreateFormDefault = new StringBuilder();

        for (TableExtend tableExtend : list) {
            sbGridColumn.append(this.getAfterReplace(VueConst.TXT_TABLE_GRID_COLUMN, tableExtend));
            sbTableForm.append(this.getAfterReplace(VueConst.TXT_TABLE_FORM_COLUMN, tableExtend));
            sbCreateFormColumn.append(this.getAfterReplace(VueConst.TXT_CREATE_FORM_COLUMN, tableExtend));
            sbCreateFormDefault.append(this.getAfterReplace(VueConst.TXT_CREATE_FORM_DEFAULT, tableExtend));
        }

        sb.append(VueConst.TXT_VUE.replaceAll(VueConst.REPLACE_MODEL_CLASS, this.getLowerModelClass())
                .replaceAll(VueConst.REPLACE_TABLE_GRID_COLUMN, sbGridColumn.toString())
                .replaceAll(VueConst.REPLACE_TABLE_FORM_COLUMN, sbTableForm.toString())
                .replaceAll(VueConst.REPLACE_CREATE_FORM_COLUMN, sbCreateFormColumn.toString())
                .replaceAll(VueConst.REPLACE_CREATE_FORM_DEFAULT, sbCreateFormDefault.toString()));
    }

    @Override
    protected String getClassName() {
        if (this.className == null) {
            this.className = StringUtil.firstCharacterToUpper(StringUtil.formatField(tableName));
        }
        return this.className;
    }

    @Override
    protected String getPath() {
        if (this.path == null) {
            this.path = System.getProperty("user.dir") + VueConst.DIR_SPLIT + VueConst.PATH_INNER_RESOURCE
                    +  VueConst.PATH_INNER_MAPPER_VUE;
        }
        return this.path;
    }

    @Override
    protected String getFullPackageName() {
        return "";
    }

    @Override
    protected String getFileType() {
        return VueConst.FILE_TYPE_VUE;
    }

    private String getAfterReplace(String replaceStr, TableExtend tableExtend) {
        return replaceStr.replaceAll(VueConst.REPLACE_COLUMN_COMMENT, tableExtend.getColumnComment())
                .replaceAll(VueConst.REPLACE_COLUMN_NAME_RE, tableExtend.getColumnNameRe(this.getLowerModelClass()));
    }
}

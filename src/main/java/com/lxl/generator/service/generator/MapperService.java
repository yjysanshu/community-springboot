package com.lxl.generator.service.generator;

import com.lxl.generator.consts.MapperConst;
import com.lxl.generator.util.StringUtil;

public class MapperService extends BaseService {
    @Override
    protected void generator() {
        sb.append(this.getModelAfterReplace(MapperConst.TXT_IMPORT_CUSTOM_INFO));
        sb.append(this.getModelAfterReplace(MapperConst.TXT_MIDDLE_CLASS));
    }

    @Override
    protected String getClassName() {
        if (this.className == null) {
            this.className = StringUtil.firstCharacterToUpper(StringUtil.formatField(tableName)) + MapperConst.CLASS_NAME_MAPPER;
        }
        return this.className;
    }

    @Override
    protected String getPath() {
        if (this.path == null) {
            this.path = System.getProperty("user.dir") + MapperConst.DIR_SPLIT + MapperConst.PATH_INNER_JAVA
                    + MapperConst.DIR_SPLIT + formatPackageDir(packageName) + MapperConst.PATH_INNER_COMMON_MAPPER;
        }
        return this.path;
    }

    @Override
    protected String getFullPackageName() {
        return packageName + MapperConst.COMMON_MAPPER_PACKAGE;
    }
}

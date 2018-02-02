package com.lxl.generator.service.generator;

import com.lxl.generator.consts.ControllerConst;
import com.lxl.generator.util.StringUtil;

public class ControllerService extends BaseService {
    @Override
    protected void generator() {
        sb.append(this.getModelAfterReplace(ControllerConst.TXT_IMPORT_CUSTOM_INFO));
        sb.append(this.getModelAfterReplace(ControllerConst.TXT_IMPORT_FRAMEWORK_INFO));
        sb.append(this.getModelAfterReplace(ControllerConst.TXT_IMPORT_SYSTEM_INFO));
        sb.append(this.getModelAfterReplace(ControllerConst.TXT_CONTROLLER));
    }

    @Override
    protected String getClassName() {
        if (this.className == null) {
            this.className = StringUtil.firstCharacterToUpper(StringUtil.formatField(tableName)) + ControllerConst.CLASS_NAME_CONTROLLER;
        }
        return this.className;
    }

    @Override
    protected String getPath() {
        if (this.path == null) {
            this.path = System.getProperty("user.dir") + ControllerConst.DIR_SPLIT + ControllerConst.PATH_INNER_JAVA
                    + ControllerConst.DIR_SPLIT + formatPackageDir(packageName) + ControllerConst.PATH_INNER_ADMIN_CONTROLLER;
        }
        return this.path;
    }

    @Override
    protected String getFullPackageName() {
        return packageName + ControllerConst.ADMIN_CONTROLLER_PACKAGE;
    }
}

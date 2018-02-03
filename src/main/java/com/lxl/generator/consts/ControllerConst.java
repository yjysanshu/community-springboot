package com.lxl.generator.consts;

public class ControllerConst extends CommonConst {

    public static final String ADMIN_CONTROLLER_PACKAGE = ".admin.controllers";
    public static final String CLASS_NAME_CONTROLLER = "Controller";

    public static final String TXT_IMPORT_CUSTOM_INFO = "import com.lxl.admin.models.request.[ModelClass]Request;\n" +
            "import com.lxl.common.services.[ModelClass]Service;\n" +
            "import com.lxl.common.util.FormatUtil;\n";

    public static final String TXT_IMPORT_FRAMEWORK_INFO = "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.web.bind.annotation.RequestBody;\n" +
            "import org.springframework.web.bind.annotation.RequestMapping;\n" +
            "import org.springframework.web.bind.annotation.RestController;\n\n";

    public static final String TXT_IMPORT_SYSTEM_INFO = "import java.util.HashMap;\n" +
            "import java.util.Map;\n\n";

    public static final String TXT_CONTROLLER =
            "@RestController\n" +
            "@RequestMapping(\"/[modelClass]\")\n" +
            "public class [ModelClass]Controller {\n" +
            "\n" +
            "    @Autowired\n" +
            "    private [ModelClass]Service [modelClass]Service;\n" +
            "\n" +
            "    @RequestMapping(\"/list\")\n" +
            "    public Map list(@RequestBody [ModelClass]Request request) {\n" +
            "        Map<String, Object> map = new HashMap<>();\n" +
            "        map.put(\"list\", [modelClass]Service.getList(request));\n" +
            "        map.put(\"total\", [modelClass]Service.getTotal(request));\n" +
            "        return FormatUtil.success(map);\n" +
            "    }\n" +
            "\n" +
            "    @RequestMapping(\"/save\")\n" +
            "    public Map save(@RequestBody [ModelClass]Request request) {\n" +
            "        return FormatUtil.success([modelClass]Service.save(request));\n" +
            "    }\n" +
            "\n" +
            "    @RequestMapping(\"/del\")\n" +
            "    public Map delete(@RequestBody [ModelClass]Request request) {\n" +
            "        if ([modelClass]Service.delete(request.getId()) > 0) {\n" +
            "            return FormatUtil.success();\n" +
            "        }\n" +
            "        return FormatUtil.fail();\n" +
            "    }\n" +
            "}";
}

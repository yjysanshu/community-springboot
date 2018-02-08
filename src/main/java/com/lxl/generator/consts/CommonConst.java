package com.lxl.generator.consts;

public class CommonConst {

    public static final String[] TIME_FIELD = {"create_at", "update_at"};
    public static final String[] SYSTEM_DATABASE = {"information_schema", "performance_schema", "mysql"};

    public static final String SYSTEM_DATABASE_INFORMATION_SCHEMA = "information_schema";
    public static final String SYSTEM_DATABASE_PERFORMANCE_SCHEMA = "performance_schema";
    public static final String SYSTEM_DATABASE_MYSQL = "mysql";


    //文件路径
    public static final String DIR_SPLIT = "/";
    public static final String PATH_INNER_JAVA = "src/main/java";
    public static final String PATH_INNER_RESOURCE = "src/main/resources";
    public static final String PATH_INNER_ADMIN_MODELS = "/admin/models";
    public static final String PATH_INNER_ADMIN_CONTROLLER = "/admin/controllers";
    public static final String PATH_INNER_ADMIN_REQUEST_MODELS = "/admin/models/request";
    public static final String PATH_INNER_ADMIN_RESPONSE_MODELS = "/admin/models/response";
    public static final String PATH_INNER_COMMON_MODELS = "/common/models";
    public static final String PATH_INNER_COMMON_SERVICE = "/common/services";
    public static final String PATH_INNER_COMMON_MAPPER = "/common/mapper";
    public static final String PATH_INNER_MAPPER_XML = "/mappers";
    public static final String PATH_INNER_MAPPER_VUE = "/vue";

    //文件类型
    public static final String FILE_TYPE_JAVA = "java";
    public static final String FILE_TYPE_XML = "xml";
    public static final String FILE_TYPE_VUE = "vue";

    //错误类型
    public static final String TYPE_ERROR = "ERROR";

    //数据库字段类型
    public static final String DATA_TYPE_INT = "int";
    public static final String DATA_TYPE_ENUM = "enum";
    public static final String DATA_TYPE_CHAR = "char";
    public static final String DATA_TYPE_VARCHAR = "varchar";
    public static final String DATA_TYPE_TINYINT = "tinyint";
    public static final String DATA_TYPE_DATETIME = "datetime";
    public static final String DATA_TYPE_TIMESTAMP = "timestamp";

    //数据库KEY
    public static final String DATA_KEY_PRI = "PRI";
    public static final String DATA_PRIMARY_KEY = "id";

    //JAVA字段类型
    public static final String JAVA_TYPE_TIMESTAMP = "Date";
    public static final String JAVA_TYPE_VARCHAR = "String";
    public static final String JAVA_TYPE_INTEGER = "Integer";

    //JDBC字段类型
    public static final String JDBC_TYPE_TIMESTAMP = "TIMESTAMP";
    public static final String JDBC_TYPE_VARCHAR = "VARCHAR";
    public static final String JDBC_TYPE_INTEGER = "INTEGER";

    //待替换的文本
    public static final String REPLACE_PACKAGE = "\\[package\\]";
    public static final String REPLACE_CLASS = "\\[class\\]";
    public static final String REPLACE_PARENT = "\\[parent\\]";
    public static final String REPLACE_TYPE = "\\[type\\]";
    public static final String REPLACE_COLUMN_NAME = "\\[columnName\\]";
    public static final String REPLACE_COLUMN_NAME_RE = "\\[columnNameRe\\]";
    public static final String REPLACE_UPPER_COLUMN_NAME = "\\[ColumnName\\]";
    public static final String REPLACE_UPPER_COLUMN_NAME_RE = "\\[ColumnNameRe\\]";
    public static final String REPLACE_MODEL_CLASS = "\\[modelClass\\]";
    public static final String REPLACE_UPPER_MODEL_CLASS = "\\[ModelClass\\]";
    public static final String REPLACE_TABLE_NAME = "\\[tableName\\]";

    //替换的文本
    public static final String TXT_PACKAGE_LINE = "package [package];\n\n";
    public static final String TXT_IMPORT_LINE_DATE = "import java.util.Date;\n\n";
    public static final String TXT_CLASS_LINE = "public class [class] {\n";
    public static final String TXT_CLASS_EXTEND_LINE = "public class [class] extends [parent] {\n";
    public static final String TXT_END_LINE = "}";

    //转义字符
    public static final String ESCAPE_WARP = "\n";

    //其他字符
    public static final String CHARACTER_NULL = "";
    public static final String CHARACTER_UNDERLINE = "_";
    public static final String CHARACTER_SPACE = " ";
    public static final String CHARACTER_COMMA = ",";
}

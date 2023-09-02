import lombok.Data;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/20 - 04 - 20
 * description:
 */
@Data
public class DatasetSqlField {
    /**
     * 数据源
     */
    private String drillStorage;
    /**
     * 数据源类型
     */
    private String drillStorageType;
    /**
     * 数据库
     */
    private String database;
    /**
     * 表
     */
    private String table;
    /**
     * 表别名
     */
    private String tableAlias;
    /**
     * 字段
     */
    private String field;
    /**
     * 字段别名
     */
    private String fieldAlias;
}

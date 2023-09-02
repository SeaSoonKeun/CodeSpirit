import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.util.SqlBasicVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/20 - 04 - 20
 * description:
 */
public class getSqlParam {
    public List<DatasetSqlField> getDatasetSqlField(String sql) throws SqlParseException {
        List<DatasetSqlField> datasetSqlFields = new ArrayList<>();
        SqlNode sqlNode = SqlParser.create(sql, SqlParser.config().withLex(Lex.MYSQL)).parseQuery();
        sqlNode.accept(new SqlBasicVisitor<String>() {
            public String visit(SqlCall call) {
                if (call.getKind().equals(SqlKind.SELECT)) {
                    SqlSelect select = (SqlSelect) call;

                    System.out.println("--------------查询列名----------------------------------------");
                    select.getSelectList().forEach(colum -> {
                        if (SqlKind.AS.equals(colum.getKind())) {
                            SqlBasicCall basicCall = (SqlBasicCall) colum;
                            String field = basicCall.getOperandList().get(0).toString();
                            String fieldAlias = basicCall.getOperandList().get(1).toString();
                            String[] fieldSplit = field.split("\\.");
                            if (fieldSplit.length == 2) {
                                DatasetSqlField datasetSqlField = new DatasetSqlField();
                                datasetSqlField.setTableAlias(fieldSplit[0]);
                                datasetSqlField.setField(fieldSplit[1]);
                                datasetSqlField.setFieldAlias(fieldAlias);
                                datasetSqlFields.add(datasetSqlField);
                            } else if (fieldSplit.length == 1){
                                DatasetSqlField datasetSqlField = new DatasetSqlField();
                                datasetSqlField.setField(fieldSplit[0]);
                                datasetSqlField.setFieldAlias(fieldAlias);
                                datasetSqlFields.add(datasetSqlField);
                            } else {
                                throw new RuntimeException("sql parse error");
                            }
                            System.out.printf("%s as %s %n", field, basicCall.getOperandList().get(1).toString());
                        } else if (SqlKind.IDENTIFIER.equals(colum.getKind())) {
                            // log.info(colum.toString());
                            String field = colum.toString();
                            String[] fieldSplit = field.split("\\.");
                            if (fieldSplit.length == 2) {
                                DatasetSqlField datasetSqlField = new DatasetSqlField();
                                datasetSqlField.setField(fieldSplit[1]);
                                datasetSqlFields.add(datasetSqlField);
                            } else if (fieldSplit.length == 1){
                                DatasetSqlField datasetSqlField = new DatasetSqlField();
                                datasetSqlField.setField(fieldSplit[0]);
                                datasetSqlFields.add(datasetSqlField);
                            } else {
                                throw new RuntimeException("sql parse error");
                            }
                            System.out.printf(((colum) + "%n"));
                        }
                    });
                    System.out.println("--------------From Table Info----------------------------------------");
                    select.getFrom().accept(new SqlBasicVisitor<String>() {
                        public String visit(SqlCall call) {
                            if (call.getKind().equals(SqlKind.AS)) {
                                SqlBasicCall basicCall = (SqlBasicCall) call;
                                String table = basicCall.getOperandList().get(0).toString();
                                String tableAlias = basicCall.getOperandList().get(1).toString();
                                String[] tableSplit1 = table.split("\\.");
                                if (tableSplit1.length == 3) {
                                    DatasetSqlField datasetSqlField = new DatasetSqlField();
                                    datasetSqlField.setDrillStorage(tableSplit1[0]);
                                    datasetSqlField.setDatabase(tableSplit1[1]);
                                    datasetSqlField.setTable(tableSplit1[2]);
                                    datasetSqlField.setTableAlias(tableAlias);
                                    datasetSqlFields.add(datasetSqlField);
                                } else {
                                    throw new RuntimeException("sql parse error");
                                }
                                System.out.println(String.format("table:{%s},alias:{%s}", table, tableAlias));
                            } else if (call.getKind().equals(SqlKind.IDENTIFIER)) {
                                // log.info("table:{}", call.toString());
                                String table = call.toString();
                                String[] tableSplit2 = table.split("\\.");
                                if (tableSplit2.length == 3) {
                                    DatasetSqlField datasetSqlField = new DatasetSqlField();
                                    datasetSqlField.setDrillStorage(tableSplit2[0]);
                                    datasetSqlField.setDatabase(tableSplit2[1]);
                                    datasetSqlField.setTable(tableSplit2[2]);
                                    datasetSqlFields.add(datasetSqlField);
                                } else {
                                    throw new RuntimeException("sql parse error");
                                }
                                System.out.println(String.format("table:{%s}", table));
                            } else if (call.getKind().equals(SqlKind.JOIN)) {
                                SqlJoin join = (SqlJoin) call;
                                // log.info("join.getRight:{},join.getCondition{}", join.getRight().toString(), join.getCondition().toString());
                                // System.out.println(String.format("join.getRight:{%s},join.getCondition{%s}", join.getRight().toString(), join.getCondition().toString()));
                                if (!join.getLeft().getKind().equals(SqlKind.JOIN)) {
                                    // log.info("join.getLeft:{}", join.getLeft().toString());
                                    // System.out.println(String.format("join.getLeft:{%s}", join.getLeft().toString()));
                                    if (join.getLeft().getKind().equals(SqlKind.AS)) {
                                        SqlBasicCall basicCall = (SqlBasicCall) join.getLeft();
                                        // log.info("table:{},alias:{}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString());
                                        // System.out.println(String.format("join table:{%s},alias:{%s}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString()));
                                    } else if (join.getLeft().getKind().equals(SqlKind.IDENTIFIER)) {
                                        // log.info("table:{}", join.getLeft().toString());
                                        // System.out.println(String.format("join table:{%s}", join.getLeft().toString()));
                                    }
                                }
                            }
                            // }
                            return call.getOperator().acceptCall(this, call);
                        }
                    });
                }
                return call.getOperator().acceptCall(this, call);
            }
        });
        return datasetSqlFields;
    }

    public static void main(String[] args) {
        getSqlParam getSqlParam = new getSqlParam();
        try {
            // List<DatasetSqlField> datasetSqlFields = getSqlParam.getDatasetSqlField("select a as b, t2.a as c from test.test.test as t1 join test.test.test2 as t2 on t1.id = t2.id");
            List<DatasetSqlField> datasetSqlFields = getSqlParam.getDatasetSqlField("select a as b from test.test.test");
            System.out.println(datasetSqlFields);
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}

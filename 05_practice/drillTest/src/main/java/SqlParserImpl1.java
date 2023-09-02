import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.util.SqlBasicVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/19 - 04 - 19
 * description:
 */
public class SqlParserImpl1 {
    public static void main(String[] args) throws SqlParseException {
        Logger log = LoggerFactory.getLogger(SqlParserImpl1.class);
        String sqlStr = "SELECT\n" +
                "\tsu.dept_id `deptId`,\n" +
                "\tsu.user_id,\n" +
                "\tsr.role_id,\n" +
                "\tsu.user_name,\n" +
                "\tsd.dept_name,\n" +
                "\tsr.role_name\n" +
                "FROM\n" +
                "\tmysql130.test.sys_user AS su\n" +
                "JOIN sys_dept sd ON su.dept_id = sd.dept_id\n" +
                "JOIN sys_user_role sur ON sur.user_id = su.user_id\n" +
                "JOIN sys_role sr ON sur.role_id = sr.role_id\n" +
                "WHERE\n" +
                "\tsd.dept_name = '研发部门'\n" +
                "\tand su.user_name = 'admin'\n" +
                "\tand su.dept_id = 103\n" +
                "\tor sr.role_name = '超级管理员'\n" +
                "ORDER BY\n" +
                "\tsd.create_time DESC";
        SqlNode sqlNode = SqlParser.create(sqlStr, SqlParser.config().withLex(Lex.MYSQL)).parseQuery();
        sqlNode.accept(new SqlBasicVisitor<String>() {
            public String visit(SqlCall call) {
                if (call.getKind().equals(SqlKind.SELECT)) {
                    SqlSelect select = (SqlSelect) call;
                    // log.info("--------------查询列名----------------------------------------");
                    System.out.println("--------------查询列名----------------------------------------");
                    select.getSelectList().forEach(colum -> {
                        if (SqlKind.AS.equals(colum.getKind())) {
                            SqlBasicCall basicCall = (SqlBasicCall) colum;
                            // log.info("{} as {}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString());
                            System.out.println(String.format("%s as %s", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString()));
                        } else if (SqlKind.IDENTIFIER.equals(colum.getKind())) {
                            // log.info(colum.toString());
                            System.out.println(String.format((colum.toString())));
                        }
                    });
                    // log.info("--------------From Table Info----------------------------------------");
                    System.out.println("--------------From Table Info----------------------------------------");
                    select.getFrom().accept(new SqlBasicVisitor<String>() {
                        public String visit(SqlCall call) {
                            if (call.getKind().equals(SqlKind.JOIN)) {
                                // SqlJoin join = (SqlJoin) call;
                                // // log.info("join.getRight:{},join.getCondition{}", join.getRight().toString(), join.getCondition().toString());
                                // System.out.println(String.format("join.getRight:{%s},join.getCondition{%s}", join.getRight().toString(), join.getCondition().toString()));
                                // if (!join.getLeft().getKind().equals(SqlKind.JOIN)) {
                                //     // log.info("join.getLeft:{}", join.getLeft().toString());
                                //     // System.out.println(String.format("join.getLeft:{%s}", join.getLeft().toString()));
                                //     if (join.getLeft().getKind().equals(SqlKind.AS)) {
                                //         SqlBasicCall basicCall = (SqlBasicCall) join.getLeft();
                                //         // log.info("table:{},alias:{}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString());
                                //         System.out.println(String.format("join table:{%s},alias:{%s}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString()));
                                //     } else if (join.getLeft().getKind().equals(SqlKind.IDENTIFIER)) {
                                //         // log.info("table:{}", join.getLeft().toString());
                                //         System.out.println(String.format("join table:{%s}", join.getLeft().toString()));
                                //     }
                                // }
                            } else if (call.getKind().equals(SqlKind.AS)) {
                                SqlBasicCall basicCall = (SqlBasicCall) call;
                                // log.info("table:{},alias:{}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString());
                                System.out.println(String.format("table:{%s},alias:{%s}", basicCall.getOperandList().get(0).toString(), basicCall.getOperandList().get(1).toString()));
                            } else if (call.getKind().equals(SqlKind.IDENTIFIER)) {
                                // log.info("table:{}", call.toString());
                                System.out.println(String.format("table:{%s}", call.toString()));
                            }
                            return call.getOperator().acceptCall(this, call);
                        }
                    });
                    // log.info("--------------Where  Info-----------------------------------------");
                    System.out.println("--------------Where  Info-----------------------------------------");
                    select.getWhere().accept(new SqlBasicVisitor<String>() {
                        public String visit(SqlCall call) {
                            if (call.getKind().equals(SqlKind.AND) || call.getKind().equals(SqlKind.OR)) {
                                SqlBasicCall sql = (SqlBasicCall) call;
                                SqlBasicCall left = (SqlBasicCall) sql.getOperandList().get(0);
                                SqlBasicCall right = (SqlBasicCall) sql.getOperandList().get(1);
                                // log.info("kind:{},right:{}", sql.getKind(), right);
                                System.out.println(String.format("kind:{%s},right:{%s}", sql.getKind(), right));
                                if (!left.getKind().equals(SqlKind.AND) && !left.getKind().equals(SqlKind.OR)) {
                                    // log.info("left:{}", left);
                                    System.out.println(String.format("left:{%s}", left));
                                }
                            }
                            return call.getOperator().acceptCall(this, call);
                        }
                    });
                    // log.info("--------------增加查询条件----------------------------------------");
                    System.out.println("--------------增加查询条件----------------------------------------");
                    try {
                        SqlNode condition = SqlParser.create("1=1").parseExpression();
                        SqlNode where = SqlUtil.andExpressions(select.getWhere(), condition);
                        select.setWhere(where);
                    } catch (SqlParseException e) {
                        throw new RuntimeException(e);
                    }
                    // log.info("语句:{}", select);
                    System.out.println(String.format("语句:{%s}", select));
                }
                return call.getOperator().acceptCall(this, call);
            }
        });
    }
}

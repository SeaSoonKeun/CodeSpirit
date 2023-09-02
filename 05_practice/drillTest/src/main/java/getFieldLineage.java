import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.util.SqlBasicVisitor;
import org.apache.calcite.sql.util.SqlVisitor;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorScope;
import org.apache.calcite.sql.validate.SqlValidatorUtil;

public class getFieldLineage {
    public String getFieldLineage1(String sql) throws SqlParseException {
        SqlParser.Config config = SqlParser.configBuilder()
                .setCaseSensitive(false)
                .setQuoting(Quoting.BACK_TICK)
                .setQuotedCasing(Casing.UNCHANGED)
                .setParserFactory(SqlParserImpl.FACTORY)
                .build();
        SqlParser parser = SqlParser.create(sql, config);
        SqlNode sqlNode = parser.parseStmt();

        final StringBuilder sb = new StringBuilder();
        SqlValidator validator =
                SqlValidatorUtil.newValidator(null, null, null);
        sqlNode.accept(new SqlBasicVisitor<Void>() {
            public Void visit(SqlNode node) {
                if (node instanceof SqlIdentifier) {
                    RelDataType type = validator.getValidatedNodeType(node);
                    if (type != null) {
                        sb.append("\nField: " + node.toString() +
                                ", From: " + type.getSqlTypeName().getName() +
                                ", Table: " + type.getSqlIdentifier().toString());
                    }
                }
                if (node instanceof SqlJoin) {
                    ((SqlJoin) node).getOperandList().forEach(this::visit);
                }
                if (node instanceof SqlSelect) {
                    // SqlValidator validator =
                    //         SqlValidatorUtil.newValidator(SqlValidatorUtil.precedingSelect(sqlNode, node).getCatalogReader(), null, null);
                    // validator.validate(node);
                    // ((SqlSelect) node).getOperandList().forEach(this::visit);
                    SqlValidatorScope scope = validator.getSelectScope((SqlSelect) node);
                    ((SqlSelect) node).getOperandList().forEach(this::visit);
                }
                return null;
            }

        });
        return sb.toString();
    }

    public static void main(String[] args) {
        String sql = "select a, b, c from (select a, b, c from t1) t2";
        try {
            System.out.println(new getFieldLineage().getFieldLineage1(sql));
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}

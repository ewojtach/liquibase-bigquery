package liquibase.ext.bigquery.sqlgenerator;

import liquibase.Scope;
import liquibase.database.Database;
import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.CreateTableGenerator;
import liquibase.statement.DatabaseFunction;
import liquibase.statement.core.CreateTableStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static liquibase.ext.bigquery.database.BigqueryDatabase.BIGQUERY_PRIORITY_DATABASE;

public class BigQueryCreateTableGenerator extends CreateTableGenerator {

    @Override
    public int getPriority() {
        return BIGQUERY_PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(CreateTableStatement statement, Database database) {
        return database instanceof BigqueryDatabase;
    }

    @Override
    public Sql[] generateSql(CreateTableStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        List<Sql> additionalSql = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        buffer.append("CREATE TABLE ").append(database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getTableName())).append(" ");
        buffer.append("(");
        Iterator<String> columnIterator = statement.getColumns().iterator();

        String column;
        while (columnIterator.hasNext()) {
            column = columnIterator.next();
            DatabaseDataType columnType = null;
            if (statement.getColumnTypes().get(column) != null) {
                columnType = statement.getColumnTypes().get(column).toDatabaseDataType(database);
            }

            if (columnType == null) {
                buffer.append(database.escapeColumnName(statement.getCatalogName(), statement.getSchemaName(), statement.getTableName(), column, false));
            } else {
                buffer.append(database.escapeColumnName(statement.getCatalogName(), statement.getSchemaName(), statement.getTableName(), column, !statement.isComputed(column)));
                buffer.append(" ").append(columnType);
            }

            if (columnType != null && !columnType.isAutoIncrement() && statement.getDefaultValue(column) != null) {
                Object defaultValue = statement.getDefaultValue(column);

                if (defaultValue instanceof DatabaseFunction) {
                    buffer.append(database.generateDatabaseFunctionValue((DatabaseFunction) defaultValue));
                } else {
                    buffer.append(statement.getColumnTypes().get(column).objectToSql(defaultValue, database));
                }
            }

            if (statement.getNotNullColumns().get(column) != null) {
                Scope.getCurrentScope().getLog(this.getClass()).fine("Not null constraints are not supported by BigQuery");
            }
            if (columnIterator.hasNext()) {
                buffer.append(", ");
            }
        }


        buffer.append(",");
        String sql = buffer.toString().replaceFirst(",\\s*$", "") + ")";

        additionalSql.add(0, new UnparsedSql(sql, this.getAffectedTable(statement)));
        return additionalSql.toArray(new Sql[additionalSql.size()]);
    }
}

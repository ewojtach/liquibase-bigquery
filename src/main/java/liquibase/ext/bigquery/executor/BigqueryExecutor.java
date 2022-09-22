package liquibase.ext.bigquery.executor;

import liquibase.Scope;
import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.executor.jvm.JdbcExecutor;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.visitor.SqlVisitor;
import liquibase.statement.AbstractSqlStatement;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.RawParameterizedSqlStatement;

import java.util.List;

import static liquibase.ext.bigquery.database.BigqueryDatabase.BIGQUERY_PRIORITY_DATABASE;

public class BigqueryExecutor extends JdbcExecutor {

    @Override
    public int getPriority() {
        return BIGQUERY_PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(Database database) {
        return database instanceof BigqueryDatabase;
    }

    @Override
    public void execute(SqlStatement sql) throws DatabaseException {
        Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+sql);
        if (sql instanceof  RawParameterizedSqlStatement)
            Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+((RawParameterizedSqlStatement)sql).getSql());
        if (sql instanceof AbstractSqlStatement)
            Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+sql);

        super.execute(sql);
    }

    @Override
    public void execute(SqlStatement sql, List<SqlVisitor> sqlVisitors) throws DatabaseException {
        Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+sql);
        if (sql instanceof  RawParameterizedSqlStatement)
            Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+((RawParameterizedSqlStatement)sql).getSql());
        if (sql instanceof AbstractSqlStatement)
            Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Executing sql: "+sql);

        for (SqlVisitor v : sqlVisitors) {
            Scope.getCurrentScope().getLog(this.getClass()).info("[BQExecutor] Visitor sql: "+v.getName());
        }
        super.execute(sql, sqlVisitors);
    }
}

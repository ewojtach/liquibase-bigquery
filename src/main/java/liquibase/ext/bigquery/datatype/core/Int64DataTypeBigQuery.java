package liquibase.ext.bigquery.datatype.core;

import liquibase.change.core.LoadDataChange;
import liquibase.database.Database;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;

import static liquibase.ext.bigquery.database.BigqueryDatabase.BIGQUERY_PRIORITY_DATABASE;


@DataTypeInfo(
        name = "int64",
        minParameters = 0,
        maxParameters = 0,
        priority = 1
)
public class Int64DataTypeBigQuery extends LiquibaseDataType {
    public Int64DataTypeBigQuery() {
    }

    public boolean supports(Database database) {
        return database instanceof BigqueryDatabase;
    }

    public DatabaseDataType toDatabaseDataType(Database database) {
        if(database instanceof BigqueryDatabase){

            BigqueryDatabase bqd = (BigqueryDatabase) database;
            DatabaseDataType type =  new DatabaseDataType("INT64", this.getParameters());
            type.setType("INT64");
            return type;
        }else{
            return super.toDatabaseDataType(database);
        }

    }

    public LoadDataChange.LOAD_DATA_TYPE getLoadTypeName() {
        return LoadDataChange.LOAD_DATA_TYPE.NUMERIC;
    }
}

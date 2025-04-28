package task8;

import sql.SelectAllFromTable;
import java.sql.SQLException;
import task8.Main;

public class ShowStudents {
    public void execute() throws SQLException {
        SelectAllFromTable.selectAllFromTable(Main.tableName, Main.conn);
    }
}

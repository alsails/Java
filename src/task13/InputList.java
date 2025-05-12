package task13;

import sql.InsertIntoTable;
import task13.Main;

import java.sql.SQLException;
import java.util.List;

public class InputList extends Listik {
    public void execute() throws SQLException {
        List<String> inputList = input();
        for (String item : inputList) {
            InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Input", 0d, 0d, 0d, item});
        }
        System.out.println("Список сохранён в БД.");
    }
}

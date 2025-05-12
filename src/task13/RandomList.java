package task13;

import sql.InsertIntoTable;
import task13.Main;

import java.sql.SQLException;
import java.util.List;

public class RandomList extends Listik {
    public void execute() throws SQLException {
        List<Integer> randomNumbers = random();
        for (Integer num : randomNumbers) {
            // сохраняем каждое число, остальное можно нулями или null
            InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {
                    "Random", (double) num, 0d, 0d, null
            });
        }
        System.out.println("1000 случайных чисел сохранены в MySQL.");
    }
}

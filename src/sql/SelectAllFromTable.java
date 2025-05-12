package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAllFromTable {

    public static void selectAllFromTable(String tableName, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nСодержимое таблицы: " + tableName);

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            int columnWidth = 20;

            // Заголовки
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-" + columnWidth + "s", meta.getColumnName(i));
            }
            System.out.println();
            System.out.println("-".repeat(columnWidth * columnCount));

            // Данные
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    System.out.printf("%-" + columnWidth + "s", value);
                }
                System.out.println();
            }

            System.out.println("-".repeat(columnWidth * columnCount) + "\n");

        } catch (SQLException e) {
            System.out.println("Ошибка при чтении данных из таблицы: " + e.getMessage());
        }
    }
}

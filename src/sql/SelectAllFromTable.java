package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAllFromTable {

    public static void selectAllFromTable(String tableName, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nСодержимое таблицы: " + tableName);

            //заголовки
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + "\t\t");
            }
            System.out.println("\n---------------------------------------");

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }

            System.out.println("---------------------------------------\n");

        } catch (SQLException e) {
            System.out.println("Ошибка при чтении данных из таблицы: " + e.getMessage());
        }
    }
}

package sql;

import task1.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoTable {

    public static void insertIntoTable(Object[] values) throws SQLException {
        if (values.length != Main.columns.length) {
            throw new IllegalArgumentException("Количество значений не совпадает с количеством колонок");
        }

        StringBuilder sql = new StringBuilder("INSERT INTO `" + Main.tableName + "` (");
        StringBuilder placeholders = new StringBuilder();

        for (int i = 0; i < Main.columns.length; i++) {
            sql.append(Main.columns[i][0]);
            placeholders.append("?");
            if (i < Main.columns.length - 1) {
                sql.append(", ");
                placeholders.append(", ");
            }
        }

        sql.append(") VALUES (").append(placeholders).append(")");

        PreparedStatement ps = Main.conn.prepareStatement(sql.toString());

        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]); // 💡 Универсальная установка параметров
        }

        ps.executeUpdate();
        System.out.println("Данные записаны");
    }
}

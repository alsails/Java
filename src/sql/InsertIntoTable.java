package sql;

import java.sql.*;

public class InsertIntoTable {

    public static int insertIntoTable(String tableName, Connection conn, String[][] columns, Object[] values) throws SQLException {
        if (values.length != columns.length) {
            throw new IllegalArgumentException("Количество значений не совпадает с количеством колонок");
        }

        StringBuilder sql = new StringBuilder("INSERT INTO `" + tableName + "` (");
        StringBuilder placeholders = new StringBuilder();

        for (int i = 1; i < columns.length; i++) {
            sql.append(columns[i][0]);
            placeholders.append("?");
            if (i < columns.length - 1) {
                sql.append(", ");
                placeholders.append(", ");
            }
        }

        sql.append(") VALUES (").append(placeholders).append(")");

        PreparedStatement ps = conn.prepareStatement(
                sql.toString(),
                Statement.RETURN_GENERATED_KEYS
        );

        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }

        ps.executeUpdate();
        System.out.println("Данные записаны");

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Не удалось получить сгенерированный ключ.");
            }
        }
    }
}

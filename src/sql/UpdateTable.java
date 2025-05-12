package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTable {
    public static void updateColumns(String tableName, Connection conn, int ID, String[] cols, Object[] values) throws SQLException {
        if (cols.length != values.length) {
            throw new IllegalArgumentException("Массивы колонок и значений разной длины");
        }
        StringBuilder sb = new StringBuilder("UPDATE `" + tableName + "` SET ");
        for (int i = 0; i < cols.length; i++) {
            sb.append("`").append(cols[i]).append("` = ?");
            if (i < cols.length - 1) sb.append(", ");
        }
        sb.append(" WHERE `id` = ?");

        try (PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            int idx = 1;
            for (Object v : values) {
                ps.setObject(idx++, v);
            }
            ps.setInt(idx, ID);
            ps.executeUpdate();
            System.out.println("Запись ID=" + ID + " обновлена");
        }
    }
}

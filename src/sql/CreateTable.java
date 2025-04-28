package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    public static void createTable(Connection conn, String tableName, String[][] columns) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS `" + tableName + "` (");
        sql.append("ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY");

        for (String[] col : columns) {
            sql.append(", ").append(col[0]).append(" ").append(col[1]);
        }

        sql.append(")");

        PreparedStatement ps = conn.prepareStatement(sql.toString());
        ps.executeUpdate();

        System.out.println("Таблица создана: " + tableName);
    }
}


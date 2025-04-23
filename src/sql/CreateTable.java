package sql;

import task1.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    public static void createTable() throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS `" + Main.tableName + "` (");
        sql.append("ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY");

        for (String[] col : Main.columns) {
            sql.append(", ").append(col[0]).append(" ").append(col[1]);
        }

        sql.append(")");

        PreparedStatement ps = Main.conn.prepareStatement(sql.toString());
        ps.executeUpdate();

        System.out.println("Таблица создана: " + Main.tableName);
    }
}


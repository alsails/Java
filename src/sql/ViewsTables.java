package sql;

import task1.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewsTables {
    public static void viewsTables() throws SQLException {
        String sql = "SHOW TABLES";
        PreparedStatement ps = Main.conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        System.out.println("Название таблиц:");

        while (rs.next()){
            String tableName = rs.getString(1);
            System.out.println(tableName);
        }
    }
}






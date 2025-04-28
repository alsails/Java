package sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewsTables {
    public static void viewsTables(Connection conn) throws SQLException {
        String sql = "SHOW TABLES";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        System.out.println("Название таблиц:");

        while (rs.next()){
            String tableName = rs.getString(1);
            System.out.println(tableName);
        }
    }
}






package sql;

import config.Config;
import task1.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExportToExcel {
    public static void exportToExcel() throws SQLException {
        String fileurl = Config.EXCEL_URL + Main.fileName + ".xls";

        // Формируем SELECT 'col1', 'col2' ...
        StringBuilder header = new StringBuilder("SELECT 'ID'");
        // Формируем SELECT ID, REPLACE(CAST(...)...
        StringBuilder select = new StringBuilder("SELECT ID");

        for (String[] col : Main.columns) {
            header.append(", '").append(col[0]).append("'");
            select.append(", REPLACE(CAST(").append(col[0]).append(" AS CHAR), '.', ',')");
        }

        String sql = header + " UNION ALL " + select +
                " FROM " + Main.tableName +
                " INTO OUTFILE '" + fileurl.replace("\\", "/") + "' " +
                "CHARACTER SET cp1251 " +
                "FIELDS TERMINATED BY '\\t' " +
                "LINES TERMINATED BY '\\n'";

        try {
            PreparedStatement stmt = Main.conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

            System.out.println("Данные успешно сохранены в файл: " + fileurl);
        } catch (SQLException e) {
            System.out.println("Ошибка при экспорте в Excel: " + e.getMessage());
            throw e;
        }
    }
}

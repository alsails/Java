package task10;

import java.sql.*;
import java.util.Scanner;

public class StudentByID {
    public static void findStudentByID() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID студента: ");
        int id = Integer.parseInt(sc.nextLine());

        String query = "SELECT * FROM " + Main.tableName + " WHERE student_id = ?";
        try (PreparedStatement stmt = Main.conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("ID: %d | Направление: %s | Имя: %s | Группа: %s\n",
                        rs.getInt("student_id"),
                        rs.getString("direction"),
                        rs.getString("name"),
                        rs.getString("group_name"));
            }
        }
    }
}

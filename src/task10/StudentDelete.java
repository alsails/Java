package task10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDelete {
    public static void deleteByID() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID студента для удаления: ");
        int id = Integer.parseInt(sc.nextLine());

        String query = "DELETE FROM " + Main.tableName + " WHERE student_id = ?";

        try (PreparedStatement stmt = Main.conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Студент с ID " + id + " успешно удалён.");
            } else {
                System.out.println("Студент с ID " + id + " не найден.");
            }
        }
    }
}

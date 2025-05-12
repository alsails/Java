package task12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteStudentById {
    public static void deleteById(Connection conn, String tableName) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID студента для удаления: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM `" + tableName + "` WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Студент удалён.");
            } else {
                System.out.println("Студент с таким ID не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении: " + e.getMessage());
        }
    }
}

package task12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SelectStudentById {
    public static void selectById(Connection conn, String tableName) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID студента: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM `" + tableName + "` WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nНайден студент:");
                    System.out.printf("%-5s %-25s %-25s %-15s\n", "ID", "ФИО", "Направление", "Группа");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.printf("%-5d %-25s %-25s %-15s\n",
                            rs.getInt("id"),
                            rs.getString("fio"),
                            rs.getString("direction"),
                            rs.getString("studentGroup"));
                } else {
                    System.out.println("Студент с таким ID не найден.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении данных: " + e.getMessage());
        }
    }
}

package task13;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import task13.Main;

public class DeleteById {
    public void execute() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ID для удаления: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM " + Main.tableName + " WHERE id = ?";
        try (PreparedStatement stmt = Main.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Элемент с ID " + id + " удалён.");
            } else {
                System.out.println("Элемент не найден.");
            }
        }
    }
}

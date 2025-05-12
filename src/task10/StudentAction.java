package task10;

import sql.InsertIntoTable;
import sql.SelectAllFromTable;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentAction {
    public static void inputAndSaveStudents() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (count < 5) {
            System.out.print("Введите количество студентов (не менее 5): ");
            try {
                count = Integer.parseInt(sc.nextLine());
                if (count < 5) {
                    System.out.println("Число студентов должно быть не менее 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода. Введите целое число.");
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Студент #" + (i + 1));
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Направление: ");
            String direction = sc.nextLine();

            System.out.print("Имя: ");
            String name = sc.nextLine();

            System.out.print("Группа: ");
            String group = sc.nextLine();

            Object[] data = new Object[]{id, direction, name, group};
            InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, data);
        }

        System.out.println("Данные успешно сохранены.\n");
        SelectAllFromTable.selectAllFromTable(Main.tableName, Main.conn);
    }
}

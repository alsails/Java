package task3;

import sql.InsertIntoTable;
import java.sql.SQLException;
import java.util.Scanner;
import task3.Main;

public class Action {
    public static void checkNumbers() throws SQLException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Введите число (или введите 'exit' для выхода в меню): ");
            String input = sc.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                double number = Double.parseDouble(input);
                boolean isInteger = (number == Math.floor(number));
                boolean isEven = false;

                if (isInteger) {
                    isEven = ((long) number) % 2 == 0;
                }

                InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[]{number, isInteger, isEven});

                System.out.println("Число: " + number +
                        ", Целое: " + isInteger +
                        ", Четное: " + (isInteger ? isEven : "N/A"));

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число.");
            }
        }
    }
}

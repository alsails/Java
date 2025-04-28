package task5;

import sql.InsertIntoTable;
import java.sql.SQLException;
import java.util.Scanner;
import task5.Main;

public class Action {
    private static StringBuffer str1;
    private static StringBuffer str2;
    private static final Scanner sc = new Scanner(System.in);

    private static void inputStrings() {
        while (true) {
            System.out.println("Введите первую строку (минимум 50 символов): ");
            String input1 = sc.nextLine();
            if (input1.length() >= 50) {
                str1 = new StringBuffer(input1);
                break;
            } else {
                System.out.println("Ошибка: строка должна содержать минимум 50 символов!");
            }
        }

        while (true) {
            System.out.println("Введите вторую строку (минимум 50 символов): ");
            String input2 = sc.nextLine();
            if (input2.length() >= 50) {
                str2 = new StringBuffer(input2);
                break;
            } else {
                System.out.println("Ошибка: строка должна содержать минимум 50 символов!");
            }
        }
    }

    public static void reverseStrings() throws SQLException {
        if (str1 == null || str2 == null) {
            inputStrings();
        }

        String reversed1 = new StringBuffer(str1).reverse().toString();
        String reversed2 = new StringBuffer(str2).reverse().toString();

        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[]{"Обратный порядок первой строки", reversed1});
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[]{"Обратный порядок второй строки", reversed2});

        System.out.println("Первая строка в обратном порядке: " + reversed1);
        System.out.println("Вторая строка в обратном порядке: " + reversed2);
    }

    public static void appendStrings() throws SQLException {
        if (str1 == null || str2 == null) {
            inputStrings();
        }

        String appended = str1.toString() + str2.toString();

        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[]{"Склеивание строк", appended});

        System.out.println("Результат объединения двух строк: " + appended);
    }
}

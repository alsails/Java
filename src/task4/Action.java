package task4;

import sql.InsertIntoTable;
import sql.UpdateTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Action {
    public static String line1;
    public static String line2;

    public static Scanner sc = new Scanner(System.in);

    public static void getLine() throws SQLException {
        System.out.println("Введите первую строку: ");
        line1 = sc.nextLine();
        System.out.println("Введите вторую строку: ");
        line2 = sc.nextLine();

        Main.ID = InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {line1, line2, 0, 0, null, null});
    }

    public static void getIndex() throws SQLException {
        System.out.println("Введите первый индекс: ");
        int index1 = sc.nextInt();
        System.out.println("Введите второй индекс: ");
        int index2 = sc.nextInt();
        String lineindex1 = line1.substring(index1, index2);
        String lineindex2 = line2.substring(index1, index2);
        String result = "1 подстрока с индексами (" + index1 + ", " + index2 +
                "): " + lineindex1 + ", 2 подстрока: " + lineindex2;
        System.out.println(result);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"lineindex"},
                new Object[] {result}
        );
    }

    public static void changeCase() throws SQLException {
        String upper1 = line1.toUpperCase();
        String upper2 = line2.toUpperCase();

        String lower1 = line1.toLowerCase();
        String lower2 = line2.toLowerCase();

        String result1 = "1 верхний регистр: " + upper1 + ", 2 верхний регистр: " + upper2;
        String result2 = "1 нижний регистр: " + lower1 + ", 2 нижний регистр: " + lower2;

        System.out.println(result1);
        System.out.println(result2);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"uppercase", "lowercase"},
                new Object[] {result1, result2}
        );
    }

    public static void searchSubstring() throws SQLException {
        System.out.println("Введите подстроку: ");
        String sub = sc.nextLine();

        int index1 = line1.indexOf(sub);
        int index2 = line2.indexOf(sub);
        String result;

        if (index1 != -1) {
            result = "В первой ндекс начала подстроки: " + index1 + ", индекс окончания: " + (index1 + sub.length()) + ". ";
        } else result = "В первой подстрока не найдена. ";

        if (index2 != -1) {
            result += "Во второй индекс начала подстроки: " + index2 + ", индекс окончания: " + (index2 + sub.length());
        } else result += "Во второй подстрока не найдена";

        System.out.println(result);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"substring"},
                new Object[] {result}
        );
    }
}

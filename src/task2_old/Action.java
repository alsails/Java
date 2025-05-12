package task2_old;

import sql.InsertIntoTable;
import sql.UpdateTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Action {
    public static String line1;
    public static String line2;

    public static void getLine() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите первую строку: ");
        line1 = sc.nextLine();
        System.out.println("Введите вторую строку: ");
        line2 = sc.nextLine();

        Main.ID = InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {line1, line2, 0, 0, null, null});
    }

    public static void getLength() throws SQLException {
        int length1 = line1.length();
        int length2 = line2.length();
        System.out.println("Длина первой строки: " + length1 + ", длина второй строки: " + length2);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] { "length1", "length2" },
                new Object[] { length1,    length2    }
        );
    }

    public static void unionLine() throws SQLException {
        String lineAll = line1 + line2;
        System.out.println("Объединение двух строк: " + lineAll);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"line3"},
                new Object[] {lineAll}
        );
    }

    public static void compareLine() throws SQLException {
        String result;
        if (line1.compareTo(line2) == 0) result = "Строки равны";
        else result = "Строки не равны";
        System.out.println(result);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"compare"},
                new Object[] {result}
        );
    }


}

package task4;

import config.Config;
import sql.CreateTable;
import sql.ExportToExcel;
import sql.ViewsTables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    protected final static Scanner sc = new Scanner(System.in);
    protected final static String mysqlurl = Config.DB_URL + Config.DB_NAME;
    public static Connection conn;
    public static int ID;

    static {
        System.out.println("Введите название таблицы: ");
    }

    public static String tableName = sc.nextLine();

    static {
        System.out.println("Введите название Excel: ");
    }

    public static String fileName = sc.nextLine();

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] columns = {
            {"line1", "text"},
            {"line2", "text"},
            {"lineindex", "text"},
            {"uppercase", "text"},
            {"lowercase", "text"},
            {"substring", "text"}
    };

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";
        while (!"8".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в\n" +
                    "консоль\n" +
                    "4. Возвращение подстроки по индексам, результат сохранить в MySQL с последующим выводом в\n" +
                    "консоль\n" +
                    "5. Перевод строк в верхний и нижний регистры, результат сохранить в MySQL с последующим выводом\n" +
                    "в консоль\n" +
                    "6. Поиск подстроки и определение окончания подстроки, результат сохранить в MySQL с последующим\n" +
                    "выводом в консоль\n" +
                    "7. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран\n" +
                    "8. Выход");
            s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> Action.getLine();
                case 4 -> Action.getIndex();
                case 5 -> Action.changeCase();
                case 6 -> Action.searchSubstring();
                case 7 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}

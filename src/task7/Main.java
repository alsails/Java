package task7;

import config.Config;
import sql.CreateTable;
import sql.ExportToExcel;
import sql.ViewsTables;
import utils.Utils;

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

    public static String tableName = Utils.validateTableName();

    public static String fileName = Utils.validateFileName();

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] columns = {
            {"Array", "text"},
            {"Ascending", "text"},
            {"Descending", "text"},
    };

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";

        Sort sort = new Sort();

        while (!"6".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести одномерный массив и сохранить в MySQL с последующим выводом в консоль\n" +
                    "4. Отсортировать массив и сохранить в MySQL с последующим выводом в консоль\n" +
                    "5. Сохранить результаты из MySQL в Excel и вывести их в консоль\n" +
                    "6. Выход");
            s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> sort.inputMatrices();
                case 4 -> {
                    sort.ascendingSort();
                    sort.descendingSort();
                }
                case 5 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}

// 72 5 89 34 18 91 47 6 53 77 20 61 2 84 39 11 95 24 66 30 43 8 58 16 50 80 14 98 41 1 69 21 87 32 75

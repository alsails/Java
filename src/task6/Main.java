package task6;

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
            {"Array1", "text"},
            {"Array2", "text"},
            {"Result", "text"},
    };

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";

        Matrix matrix = new Matrix();

        while (!"6".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести две матрицы с клавиатуры и каждую из них сохранить в MySQL с последующим выводом в\n" +
                    "консоль\n" +
                    "4. Перемножить матрицу, сохранить перемноженную матрицу в MySQL и вывести в консоль\n" +
                    "5. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран\n" +
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
                case 3 -> matrix.inputMatrices();
                case 4 -> {
                    matrix.multiplyMatrices();
                    matrix.printResult();
                }
                case 5 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}

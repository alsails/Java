package task13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import config.Config;
import sql.*;

public class Main {
    protected final static Scanner sc = new Scanner(System.in);
    protected final static String mysqlurl = Config.DB_URL + Config.DB_NAME;
    public static Connection conn;

    static {
        System.out.println("Введите название таблицы: ");
    }
    public static String tableName = sc.nextLine();

    static {
        System.out.println("Введите название Excel файла: ");
    }
    public static String fileName = sc.nextLine();

    public static final String[][] columns = {
            {"action", "VARCHAR(50)"},
            {"number_1", "DOUBLE(10,2)"},
            {"number_2", "DOUBLE(10,2)"},
            {"result", "DOUBLE(10,2)"},
            {"text", "VARCHAR(255)"}
    };


    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Подключение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";
        while (!"7".equals(s)) {
            System.out.println("""
                1. Вывести все таблицы из MySQL
                2. Создать таблицу в MySQL
                3. Ввести список строк и сохранить в MySQL
                4. Сгенерировать список случайных чисел и сохранить в MySQL
                5. Удалить элемент по ID
                6. Сохранить данные из MySQL в Excel и вывести
                7. Выход
            """);

            s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> new InputList().execute();
                case 4 -> new RandomList().execute();
                case 5 -> new DeleteById().execute();
                case 6 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}


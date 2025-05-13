package task3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import config.Config;
import sql.*;
import utils.Utils;

public class Main {
    protected final static Scanner sc = new Scanner(System.in);
    protected final static String mysqlurl = Config.DB_URL + Config.DB_NAME;
    public static Connection conn;

    static {
        System.out.println("Введите название таблицы: ");
    }

    public static String tableName = Utils.validateTableName();

    public static String fileName = Utils.validateFileName();

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Подключение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }

    // Структура таблицы
    public static final String[][] columns = {
            {"number", "DOUBLE(10,2)"},
            {"is_integer", "BOOLEAN"},
            {"is_even", "BOOLEAN"}
    };

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";
        while (!"5".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Проверка чисел на целостность и четность, сохранить результат\n" +
                    "4. Сохранить все данные из MySQL в Excel и вывести на экран\n" +
                    "5. Выход");
            s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> Action.checkNumbers();
                case 4 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}

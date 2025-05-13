package task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import config.Config;
import sql.*;
import utils.Utils;

public class Main {
    protected final static Scanner sc = new Scanner(System.in);
    protected final static String mysqlurl = Config.DB_URL + Config.DB_NAME;
    public static Connection conn;

    public static String tableName = Utils.validateTableName();

    public static String fileName = Utils.validateFileName();

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Подлкючение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }

    static String col1;
    static String col2;
    static String col3;
    static String col4;

    static {
        Set<String> usedNames = new HashSet<>();

        col1 = Utils.getUniqueColumnName("Введите название для первой колонки:", usedNames);
        col2 = Utils.getUniqueColumnName("Введите название для второй колонки:", usedNames);
        col3 = Utils.getUniqueColumnName("Введите название для третьей колонки:", usedNames);
        col4 = Utils.getUniqueColumnName("Введите название для четвертой колонки:", usedNames);
    }

    public static final String[][] columns = {
            {col1, "LONGTEXT"},
            {col2, "LONGTEXT"},
            {col3, "LONGTEXT"},
            {col4, "LONGTEXT"}
    };




    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";
        while (!"11".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "4. Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "5. Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "6. Деление чисел, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "7. Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "8. Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "9. Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль\n" +
                    "10. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран\n" +
                    "11. Выход");
           s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> Action.sum();
                case 4 -> Action.minus();
                case 5 -> Action.multi();
                case 6 -> Action.div();
                case 7 -> Action.del();
                case 8 -> Action.modul();
                case 9 -> Action.stepen();
                case 10 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }

        }
    }
}

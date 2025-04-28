package task1;

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

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Подлкючение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }

    // Задаем колонки для SQL
    public static final String[][] columns = {
            {"action", "VARCHAR(50)"},
            {"number_1", "DOUBLE(10, 2)"},
            {"number_2", "DOUBLE(10, 2)"},
            {"result", "DOUBLE(10, 2)"}
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

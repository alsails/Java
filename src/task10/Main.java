package task10;

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
            System.out.println("Подключение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }

    public static final String[][] columns = {
            {"student_id", "INT"},
            {"direction", "VARCHAR(100)"},
            {"name", "VARCHAR(100)"},
            {"group_name", "VARCHAR(50)"}
    };


    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";

        while (!"7".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести данные о студентах и сохранить в MySQL\n" +
                    "4. Вывести данные о студенте по ID\n" +
                    "5. Удалить данные о студенте по ID\n" +
                    "6. Сохранить данные в Excel и вывести в консоль\n" +
                    "7. Выход");

            s = sc.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода");
            }

            switch (x) {
                case 1 -> ViewsTables.viewsTables(conn);
                case 2 -> CreateTable.createTable(conn, tableName, columns);
                case 3 -> StudentAction.inputAndSaveStudents();
                case 4 -> StudentByID.findStudentByID();
                case 5 -> StudentDelete.deleteByID();
                case 6 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}


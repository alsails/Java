package task8;

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

    public static String tableName;
    public static String fileName;

    public static final String[][] columns = {
            {"name", "VARCHAR(50)"},
            {"age", "INT"},
            {"salary", "DOUBLE(10, 2)"}
    };

    static {
        try {
            conn = DriverManager.getConnection(mysqlurl, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Подключение успешно!");
        } catch(SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }

        tableName = Utils.validateTableName();
        fileName  = Utils.validateFileName();
    }

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";

        while (!"6".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести данные студента и сохранить в MySQL с выводом в консоль\n" +
                    "4. Вывести все записи студентов из MySQL\n" +
                    "5. Сохранить записи в Excel и вывести их в консоль\n" +
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
                case 3 -> new InputStudent().execute();
                case 4 -> new ShowStudents().execute();
                case 5 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}



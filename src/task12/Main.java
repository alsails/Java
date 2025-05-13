package task12;

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
    public static int ID;

    public static String tableName;
    public static String fileName;

    public static final String[][] columns = {
            {"fio", "text"},
            {"direction", "text"},
            {"studentGroup", "text"}
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

        System.out.println("tableName: " + tableName);
    }

    public static void createStudents() {
        int count;

        while (true) {
            System.out.print("Введите число студентов (минимум 7): ");
            count = sc.nextInt();

            if (count < 7) {
                System.out.println("Число должно быть НЕ меньше 7");
                continue;
            }

            break;
        }

        Students[] students = new Students[count];

        SortedStudents sorted = new SortedStudents();
        sorted.inputStudents(students);
    }

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";



        while (!"7".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести данные о всех студентах и сохранить список в MySQL с последующим табличным\n" +
                    "(форматированным) выводом в консоль\n" +
                    "4. Вывести данные о студенте по ID из MySQL\n" +
                    "5. Удалить данные о студенте из MySQL по ID\n" +
                    "6. Сохранить записи в Excel и вывести их в консоль\n" +
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
                case 3 -> createStudents();
                case 4 -> SelectStudentById.selectById(conn, tableName);
                case 5 -> DeleteStudentById.deleteById(conn, tableName);
                case 6 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}



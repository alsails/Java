package task9;

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
            {"matrix1", "text"},
            {"matrix2", "text"},
            {"multiply", "text"},
            {"sum", "text"},
            {"minus", "text"},
            {"degree1", "text"},
            {"degree2", "text"}
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

    public static void main(String[] args) throws SQLException {
        int x = 0;
        String s = "";

        ArrayPI matrix = new ArrayPI();
        Multiply multi = new Multiply(matrix);
        Sum sum = new Sum(matrix);
        Minus minus = new Minus(matrix);
        Degree degree = new Degree(matrix);


        while (!"6".equals(s)) {
            System.out.println("---------------------------\n" +
                    "1. Вывести все таблицы из MySQL\n" +
                    "2. Создать таблицу в MySQL\n" +
                    "3. Ввести две матрицы с клавиатуры и каждую из них сохранить в MySQL с последующим\n" +
                    "форматированным выводом в консоль\n" +
                    "4. Перемножить, сложить, вычесть, возвести в степень матрицы, а также сохранить результаты в MySQL\n" +
                    "и вывести в консоль\n" +
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
                case 3 -> matrix.inputMatrices();
                case 4 -> {
                    multi.multiplyMatrices();
                    multi.printResult();
                    sum.sumMatrices();
                    sum.printResult();
                    minus.minusMatrices();
                    minus.printResult();
                    degree.degreeMatrices();
                    degree.printResult();
                }
                case 5 -> ExportToExcel.exportToExcel(fileName, columns, tableName, conn);
            }
        }
    }
}



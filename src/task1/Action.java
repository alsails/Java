package task1;

import sql.InsertIntoTable;

import java.sql.SQLException;
import java.util.Scanner;

import task1.Main;

public class Action {
    public static double number1;
    public static double number2;

    static void getParam(Boolean isTwo) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Введите первое число: ");
            String input = sc.nextLine();

            try {
                double number = Double.parseDouble(input);

                if (Double.isInfinite(number)) {
                    System.out.println("Число слишком большое. Попробуйте снова.");
                    continue;
                }

                number1 = number;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Необходимо ввести число.");
            }
        }


        if (isTwo) {
            while (true) {
                System.out.println("Введите второе число: ");
                String input = sc.nextLine();

                try {
                    double number = Double.parseDouble(input);

                    if (Double.isInfinite(number)) {
                        System.out.println("Число слишком большое. Попробуйте снова.");
                        continue;
                    }

                    number2 = number;
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод. Необходимо ввести число.");
                }
            }

        }
    }

    public static void sum() throws SQLException {
        Action.getParam(true);
        double summ = number1 + number2;
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Сложение", number1, number2, summ});
        System.out.println(number1 + " + " + number2 + " = " + summ);
    }

    public static void minus() throws SQLException {
        Action.getParam(true);
        double minuss = number1 - number2;
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Вычитание", number1, number2, minuss});
        System.out.println(number1 + " - " + number2 + " = " + minuss);
    }

    public static void multi() throws SQLException {
        Action.getParam(true);
        double multii = number1 * number2;
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Умножение", number1, number2, multii});
        System.out.println(number1 + " * " + number2 + " = " + multii);
    }

    public static void div() throws SQLException {
        Action.getParam(true);
        double divv = number1 / number2;
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Деление чисел", number1, number2, divv});
        System.out.println(number1 + " / " + number2 + " = " + divv);
    }

    public static void del() throws SQLException {
        Action.getParam(true);
        double dell = number1 % number2;
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Деление по модулю", number1, number2, dell});
        System.out.println(number1 + " % " + number2 + " = " + dell);
    }

    public static void modul() throws SQLException {
        Action.getParam(false);
        double modull = Math.abs(number1);
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Модуль", number1, 0d, modull});
        System.out.println("Возведение числа " + number1 + " в модуль: " + modull);
    }

    public static void stepen() throws SQLException {
        Action.getParam(true);
        double stepenn = Math.pow(number1, number2);
        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {"Возведение в степень", number1, number2, stepenn});
        System.out.println(number1 + " ^ " + number2 + " = " + stepenn);
    }
}

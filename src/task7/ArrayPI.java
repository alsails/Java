package task7;

import com.google.gson.Gson;
import sql.InsertIntoTable;

import java.sql.SQLException;
import java.util.Scanner;

public class ArrayPI {
    int[] arr;

    public ArrayPI() {
        arr = new int[35];
    }

    public void inputMatrices() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean correctInput = false;

        while (!correctInput) {
            System.out.println("Введите 35 элементов для массива (через пробел):");

            String[] numbers = scanner.nextLine().trim().split("\\s+");

            if (numbers.length != 35) {
                System.out.println("Ошибка: нужно ввести ровно 35 чисел. Попробуйте снова.\n");
                continue;
            }

            try {
                for (int i = 0; i < 35; i++) {
                    arr[i] = Integer.parseInt(numbers[i]);
                }

                correctInput = true;

                Gson gson = new Gson();
                String arrJson = gson.toJson(arr);

                Main.ID = InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {arrJson, null, null});
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: вводите только числа. Попробуйте снова.\n");
            }
        }
    }
}

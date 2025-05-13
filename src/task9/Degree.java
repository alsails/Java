package task9;

import com.google.gson.Gson;
import sql.UpdateTable;
import task9.ArrayPI;
import task9.Main;

import java.sql.SQLException;
import java.util.Scanner;

public final class Degree extends ArrayPI {
    int[][] result1;
    int[][] result2;

    public Degree(ArrayPI matrix) {
        this.arr1 = matrix.arr1;
        this.arr2 = matrix.arr2;
        result1 = new int[7][7];
        result2 = new int[7][7];
    }

    public void degreeMatrices() throws SQLException {
        int exponent = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите степень: ");
        
        try {
            exponent = scanner.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода");
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                result1[i][j] = (int) Math.pow(arr1[i][j], exponent);
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                result2[i][j] = (int) Math.pow(arr2[i][j], exponent);
            }
        }

        Gson gson = new Gson();
        String resultJson = gson.toJson(result1);
        String resultJson2 = gson.toJson(result2);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[]{"degree1", "degree2"},
                new Object[]{resultJson, resultJson2}
        );
    }

    public void printResult() {
        System.out.println("Результат возведения в степень матриц:");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(result1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(result2[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package task9;

import com.google.gson.Gson;
import sql.InsertIntoTable;
import sql.UpdateTable;

import java.sql.SQLException;
import java.util.Scanner;

public class ArrayPI {
    int[][] arr1;
    int[][] arr2;

    public ArrayPI() {
        arr1 = new int[7][7];
        arr2 = new int[7][7];
    }

    public void inputMatrices() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите элементы первой матрицы (7 строк по 7 чисел в каждой):");
        for (int i = 0; i < 7; i++) {
            String[] line = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < 7; j++) {
                arr1[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println("Введите элементы второй матрицы (7 строк по 7 чисел в каждой):");
        for (int i = 0; i < 7; i++) {
            String[] line = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < 7; j++) {
                arr2[i][j] = Integer.parseInt(line[j]);
            }
        }

        Gson gson = new Gson();
        String arr1Json = gson.toJson(arr1);
        String arr2Json = gson.toJson(arr2);

        Main.ID = InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns, new Object[] {arr1Json, arr2Json, null, null, null, null, null});
    }
}

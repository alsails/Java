package task9;

import com.google.gson.Gson;
import sql.UpdateTable;
import task9.ArrayPI;
import task9.Main;

import java.sql.SQLException;

public final class Multiply extends ArrayPI {
    int[][] result;

    public Multiply(ArrayPI matrix) {
        this.arr1 = matrix.arr1;
        this.arr2 = matrix.arr2;
        result = new int[7][7];
    }

    public void multiplyMatrices() throws SQLException {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 7; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        Gson gson = new Gson();
        String resultJson = gson.toJson(result);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"multiply"},
                new Object[] {resultJson}
        );
    }

    public void printResult() {
        System.out.println("Результат умножения матриц:");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

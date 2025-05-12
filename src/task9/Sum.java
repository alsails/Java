package task9;

import com.google.gson.Gson;
import sql.UpdateTable;
import task9.ArrayPI;
import task9.Main;

import java.sql.SQLException;

public final class Sum extends ArrayPI {
    int[][] result;

    public Sum(ArrayPI matrix) {
        this.arr1 = matrix.arr1;
        this.arr2 = matrix.arr2;
        result = new int[7][7];
    }

    public void sumMatrices() throws SQLException {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                result[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        Gson gson = new Gson();
        String resultJson = gson.toJson(result);

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"sum"},
                new Object[] {resultJson}
        );
    }

    public void printResult() {
        System.out.println("Результат сложения матриц:");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

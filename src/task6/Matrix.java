package task6;

import com.google.gson.Gson;
import sql.UpdateTable;

import java.sql.SQLException;

public final class Matrix extends ArrayPI {
    int[][] result;

    public Matrix() {
        super();
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
                new String[] {"Result"},
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

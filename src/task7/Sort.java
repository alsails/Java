package task7;

import com.google.gson.Gson;
import sql.UpdateTable;

import java.sql.SQLException;

public class Sort extends ArrayPI {
    public Sort() {
        super();
    }

    public void printArray() {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public void ascendingSort() throws SQLException {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        Gson gson = new Gson();
        String ascendingSort = gson.toJson(arr);

        printArray();

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"Ascending"},
                new Object[] {ascendingSort}
        );
    }

    public void descendingSort() throws SQLException {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        Gson gson = new Gson();
        String descendingSort = gson.toJson(arr);

        printArray();

        UpdateTable.updateColumns(
                Main.tableName, Main.conn, Main.ID,
                new String[] {"Descending"},
                new Object[] {descendingSort}
        );
    }
}

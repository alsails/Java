package task12;

import sql.InsertIntoTable;

import java.util.Arrays;
import java.util.Comparator;

public class SortedStudents extends Students {
    @Override
    public void inputStudents(Students[] students) {
        super.inputStudents(students);

        Arrays.sort(students, Comparator.comparing(s -> s.getName().split(" ")[0]));

        System.out.printf("%-25s %-25s %-15s\n", "ФИО", "Направление", "Группа");
        System.out.println("----------------------------------------------------------------------");
        for (Students s : students) {
            System.out.printf("%-25s %-25s %-15s\n",
                    s.getName(), s.getDirection(), s.getGroup());
        }

        for (Students s : students) {
            try {
                InsertIntoTable.insertIntoTable(
                        Main.tableName,
                        Main.conn,
                        Main.columns,
                        new Object[]{s.getName(), s.getDirection(), s.getGroup()}
                );
            } catch (Exception e) {
                System.out.println("Ошибка при добавлении студента в БД: " + e.getMessage());
            }
        }
    }
}

package task8;

import sql.InsertIntoTable;
import java.util.Scanner;
import java.sql.SQLException;
import task8.Main;

public class InputStudent {
    public void execute() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Worker worker = new Worker();

        System.out.println("Введите имя студента:");
        worker.setName(sc.nextLine());

        System.out.println("Введите возраст студента:");
        worker.setAge(Integer.parseInt(sc.nextLine()));

        System.out.println("Введите предполагаемую зарплату:");
        worker.setSalary(Double.parseDouble(sc.nextLine()));

        InsertIntoTable.insertIntoTable(Main.tableName, Main.conn, Main.columns,
                new Object[]{worker.getName(), worker.getAge(), worker.getSalary()});

        System.out.println("Студент сохранен: " + worker.getName() + ", возраст: " + worker.getAge() + ", зарплата: " + worker.getSalary());
    }
}

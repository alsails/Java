package task12;

import java.util.Scanner;

public class Students {
    private String name;
    private String group;
    private String direction;

    public void inputStudents(Students[] students) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < students.length; i++) {
            students[i] = new Students();
            System.out.println("Введите данные для студента #" + (i + 1));

            System.out.print("Направление подготовки: ");
            students[i].direction = sc.nextLine();

            System.out.print("ФИО: ");
            students[i].name = sc.nextLine();

            System.out.print("Группа: ");
            students[i].group = sc.nextLine();

            System.out.println();
        }
    }

    public String getName() {
        return name;
    }
    

    public String getDirection() {
        return direction;
    }

    public String getGroup() {
        return group;
    }
}

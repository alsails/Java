package task13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Listik {
    protected List<Integer> random() {
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            numbers.add(rand.nextInt(10000)); // диапазон по желанию
        }
        return numbers;
    }

    protected List<String> input() {
        Scanner sc = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        System.out.println("Введите 10 строк:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Строка " + (i + 1) + ": ");
            words.add(sc.nextLine());
        }
        return words;
    }
}

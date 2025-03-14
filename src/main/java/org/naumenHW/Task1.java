package org.naumenHW;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public void getAverageValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n;
        while (true) {
            n = scanner.nextInt();
            if (n > 0) break;
            else System.out.println("Количество элементов должно быть больше 0");
        }

        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        double sum = 0.0;
        for (int num : array) {
            sum += num;
        }

        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Среднее значение элементов массива: " + sum / n);

    }
}

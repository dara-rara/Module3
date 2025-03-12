package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public void getAverageValue() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        var n = scanner.nextInt();

        var array = new int[n];
        var random = new Random();
        for (var i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        var sum = 0.0;
        for (var num : array) {
            sum += num;
        }

        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Среднее значение элементов массива: " + sum / n);

    }
}

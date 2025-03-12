package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task2 {

    public void getSortList() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в списке: ");
        var n = scanner.nextInt();

        ArrayList<Double> list = new ArrayList<>();
        var random = new Random();
        for (var i = 0; i < n; i++) {
            list.add(random.nextDouble() * 100);
        }

        System.out.println("Исходный список: " + list);
        bubbleSort(list);
        System.out.println("Отсортированный список: " + list);
    }

    private void bubbleSort(ArrayList<Double> list) {
        var n = list.size();
        boolean flag;
        for (var i = 0; i < n - 1; i++) {
            flag = false;
            for (var j = 0; j < n - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Меняем элементы местами
                    var temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }
}

package org.naumenHW;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task2 {

    public void getSortList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в списке: ");
        int n;
        while (true) {
            n = scanner.nextInt();
            if (n > 0) break;
            else System.out.println("Количество элементов должно быть больше 0");
        }

        ArrayList<Double> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble() * 100);
        }

        System.out.println("Исходный список: " + list);
        bubbleSort(list);
        System.out.println("Отсортированный список: " + list);
    }

    private void bubbleSort(ArrayList<Double> list) {
        int n = list.size();
        boolean flag;
        for (int i = 0; i < n - 1; i++) {
            flag = false;
            for (var j = 0; j < n - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Меняем элементы местами
                    double temp = list.get(j);
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

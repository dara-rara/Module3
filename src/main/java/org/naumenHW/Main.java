package org.naumenHW;


public class Main {
    public static void main(String[] args) {

        System.out.println("Задача 1");
        var task1 = new Task1();
        task1.getAverageValue();
        System.out.println();

        System.out.println("Задача 2");
        var task2 = new Task2();
        task2.getSortList();
        System.out.println();

        System.out.println("Задача 3");
        var task3 = new Task3();
        task3.getSortAscSalary();
        System.out.println();

        System.out.println("Задача 4");
        var task4 = new Task4();
        task4.getRequestHeaders();
        System.out.println();

        System.out.println("Задача 5");
        var task = new Task5("C:\\WORK\\Programming\\Spring\\test1",
                "C:\\WORK\\Programming\\Spring\\test2");
        task.start();
        // Остановка задачи через 60 секунд (для примера)
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.stop();
        System.out.println();
    }
}
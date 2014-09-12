package com.company;

import java.util.Random;

public class BalanceWeights {

    private static int[] array = new int[30];
    private static int sMax = 100;

    public BalanceWeights() {
        initArray();
    }

    public void run() {

        //вычисляем среднее значение
        int sum = getSum(array);
        int avr = sum / 2;

        //сортируем по убыванию
        quickSort(array, 0, array.length - 1);

        int s1 = 0; //сумма значений в 1-м результирующем массиве
        int s2 = 0; //сумма значений во 2-м результирующем массиве
        int s3 = 0; //сумма значений в массиве, близкая к 100
        SimpleStack res1 = new SimpleStack(array.length); //1-й результирующий массив
        SimpleStack res2 = new SimpleStack(array.length); //2-й результирующий массив

        for (int i : array) {

            if (s3 + i <= sMax)
                s3 += i;

            if (s1 + i <= avr) {
                s1 += i;
                res1.push(i);
            } else {
                s2 += i;
                res2.push(i);
            }
        }

        String sign = "=";
        if (s2 > s1) sign = ">";
        else if (s1 > s2) sign = "<";

        System.out.println(String.format("\n[%s] %s [%s] %s",
                res2.join(" "), sign, res1.join(" "), (s2 - s1 != 0) ? " >> delta = " + (s2 - s1) : ""));
        System.out.println(String.format("%s", (s3 == sMax) ? "yes" : "no"));
    }

    private static void initArray() {
        array = new int[]{2, 4, 3, 6, 5};
        //array = new int[]{1, 1, 2, 3, 4, 7, 10, 20, 30, 40, 50, 60, 120, 127};

        /*array = new int[30];
        for (int j = 0; j < array.length; ++j) {
            int k = randInt(1, array.length);
            array[j] = k;
        }*/

        StringBuilder res = new StringBuilder();
        res.append(array[0]);
        for (int i = 1; i <= array.length - 1; ++i)
            res.append(" ").append(array[i]);

        System.out.println(String.format("[%s]", res));
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private static int getSum(int[] array) {
        int res = 0;
        for (int i : array) res += i;
        return res;
    }

    private static void quickSort(int[] array, int min, int max) {
        int i = min, j = max, temp;
        int half = array[(min + max) / 2];
        do {
            while (array[i] > half) ++i;
            while (array[j] < half) --j;

            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                ++i;
                --j;
            }
        } while (i <= j);

        if (min < j)
            quickSort(array, min, j);
        if (i < max)
            quickSort(array, i, max);
    }

}

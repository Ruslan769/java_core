package com.java_core.lesson2.dz;

public class App {

    public static void main(String[] args) {
        String[][] arStrExample = {{"1", "2", "z", "4"}, {"11", "k", "33"}};
        testArray(arStrExample);
    }

    private static void testArray(String[][] arr) {
        if (arr.length != 2) {
            throw new MyArraySizeException("Неверный размер массива");
        }
        int amountCalc = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива");
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    try {
                        int x = Integer.valueOf(arr[i][j]);
                        amountCalc += x;
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("[" + i + "][" + j + "]");
                    }
                } catch (MyArrayDataException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Сумма расчета = " + amountCalc);
    }
}

package com.java_core.lesson2;

public class App {
    public static void main(String[] args) {
        /*try {
            int x = 5;
            int y = 0;
            int z = x / y;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        System.out.println("success");*/

        // если Exception
        /*try {
            calc();
        } catch (NotHasMoneyException e) {
            e.printStackTrace();
        }*/
        UserManagerImpl userManager = new UserManagerImpl();
        userManager.clear();
    }

    static void calc() throws NotHasMoneyException {
        int price = 90;
        int product = 100;
        if (product > price) {
            throw new NotHasMoneyException();
        }
    }
}

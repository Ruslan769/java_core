package com.java_core.lesson2;

class NotHasMoneyException extends RuntimeException {
    NotHasMoneyException() {
        System.out.println("Не хватает средств");
    }
}

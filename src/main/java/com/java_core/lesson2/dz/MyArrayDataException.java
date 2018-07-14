package com.java_core.lesson2.dz;

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException (String s) {
        super("В ячейке " + s + " неверные данные");
    }
}

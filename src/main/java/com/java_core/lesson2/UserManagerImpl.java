package com.java_core.lesson2;

public class UserManagerImpl implements UserManager {
    @Override
    public void clear() {
        throw new NotHasMoneyException();
    }
}

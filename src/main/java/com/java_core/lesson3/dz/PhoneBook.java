package com.java_core.lesson3.dz;

import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {
    private HashMap<String, HashSet<String>> arPhoneBook = new HashMap<String, HashSet<String>>();

    public String get(String surname) {
        if (surname == null) {
            return "";
        }
        surname = surname.toLowerCase();
        HashSet<String> arHashSet = getPhoneBySurname(surname);

        String strPhone;
        if (!arHashSet.isEmpty()) {
            strPhone = StringUtils.join(arHashSet, ", ");
        } else {
            strPhone = "К сожалению, нет такого контакта.";
        }
        return strPhone;
    }

    public void add(String surname, String number) {
        if (surname == null) {
            return;
        }
        surname = surname.toLowerCase();
        HashSet<String> arHashSet = getPhoneBySurname(surname);
        arHashSet.add(number);
        arPhoneBook.put(surname, arHashSet);
    }

    private HashSet getPhoneBySurname(String surname) {
        HashSet<String> arHashSet = new HashSet<String>();
        if (arPhoneBook.containsKey(surname)) {
            arHashSet = arPhoneBook.get(surname);
        }
        return arHashSet;
    }
}

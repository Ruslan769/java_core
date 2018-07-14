package com.java_core.lesson3.dz;

import java.util.*;

public class App {

    public static void main(String[] args) {
        List<String> artCarsLis = Arrays.asList(new String[]{"BMV", "AUDI", "BMV", "Mercedes-Benz",
                "MAZDA", "Mercedes-Benz", "ВАЗ-2114", "AUDI", "Volkswagen", "NISSAN"});

        final Set<String> arCarsTreeSet = new TreeSet<String>(artCarsLis);

        for (String car : arCarsTreeSet) {
            System.out.printf("%s повторяется %d раз(а)%n", car, Collections.frequency(artCarsLis, car));
        }

        System.out.println("---------------------");

        PhoneBook phonebook = new PhoneBook();
        phonebook.add("Sergeev", "89281234567");
        phonebook.add("Alekseev", "89281234565");
        phonebook.add("Alekseev", "89281234568");
        phonebook.add("Alekseev", "89281234569");
        System.out.println(phonebook.get("Alekseev"));
    }
}

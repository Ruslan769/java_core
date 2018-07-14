package com.java_core.lesson1.zd;

public class App {

    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team(new String[] { "Алексей", "Вадим", "Лера", "Юля" });
        course.doIt(team);
        System.out.println(team.getHavePassedParticipants());
    }
}

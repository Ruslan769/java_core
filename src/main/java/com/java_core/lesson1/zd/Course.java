package com.java_core.lesson1.zd;

import java.util.Random;

public class Course {

    String[] obstacleCourse = new String[] { "LongJump", "ThrowingDisc", "RacingWithBall", "Bridge" };

    void doIt(Team team) {

        Random random = new Random();
        String[] arParticipants = team.getParticipants();

        for (String participant : arParticipants) {
            boolean passed = true;
            for (int i = 0; i < obstacleCourse.length; i++) {
                int num = random.nextInt(1000);
                if (num % 2 == 0) {
                    passed = false;
                }
            }
            if (passed) {
                team.addHavePassedParticipants(participant);
            }
        }
    }
}

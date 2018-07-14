package com.java_core.lesson1.zd;

import java.util.ArrayList;

public class Team {

    private String nameCommand = "Champion";
    private String[] arParticipants;
    private ArrayList<String> arHavePassedParticipants = new ArrayList();

    public Team(String... arParticipants) {
        this.arParticipants = arParticipants;
    }

    public void setNameCommand(String nameCommand) {
        this.nameCommand = nameCommand;
    }

    public void addHavePassedParticipants(String participant) {
        this.arHavePassedParticipants.add(participant);
    }

    public String[] getParticipants() {
        return arParticipants;
    }

    public StringBuffer getHavePassedParticipants() {
        StringBuffer participants = new StringBuffer();
        if (arParticipants.length == 0) {
            participants.append("К сожалению вы не добавили участников");
        } else if (arHavePassedParticipants.isEmpty()) {
            participants.append("К сожалению ни один уастник не прошел испытания");
        } else {
            participants.append("Участники: \n");
            for (String participant : arHavePassedParticipants) {
                participants.append(participant + "\n");
            }
            participants.append("Прошли испытания!");
        }
        return participants;
    }
}

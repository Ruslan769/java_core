package com.java_core.lesson7.server.service;

public interface AuthService {

    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();

}

package com.example.clientapp.manager;

import com.example.clientapp.model.Smember;

public class UserSessionManager {
    private static UserSessionManager instance;
    private Smember smember;

    private UserSessionManager() {}

    public static UserSessionManager getInstance() {
        if(instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public Smember getSmember() {
        return smember;
    }

    public void setSmember(Smember smember) {
        this.smember = smember;
    }
}

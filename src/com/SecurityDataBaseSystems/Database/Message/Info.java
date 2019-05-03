package com.SecurityDataBaseSystems.Database.Message;

import com.SecurityDataBaseSystems.main.Main;

public class Info {

    static Main main;

    public static void setMain(Main main) {
        Info.main = main;
    }

    private String idMessage;
    private String Sender;
    private String MessageSubject;

    public String getIdMessage() {
        return idMessage;
    }

    public String getSender() {
        return Sender;
    }

    public String getMessageSubject() {
        return MessageSubject;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public void setMessageSubject(String messageSubject) {
        MessageSubject = messageSubject;
    }

    @Override
    public String toString() {
        return main.dataBase.employees.IdToLogin(Sender) + " \n" + MessageSubject;
    }
}

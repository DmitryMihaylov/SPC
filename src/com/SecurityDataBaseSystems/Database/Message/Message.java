package com.SecurityDataBaseSystems.Database.Message;


import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Message {

    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }


    //Метод отправки сообщений
    public void SendMessage(String MessageSubject, String Sender, String Recipient, String Message, String CheckSum)
    {

        String Query = "INSERT INTO Info (" +
                "MessageSubject, " +
                "Sender, " +
                "Recipient, " +
                "Info, " +
                "CheckSum)" +

                " VALUES ('" +
                MessageSubject.toString() + "', '" +
                Sender.toString()+"', '"+
                Recipient.toString() + "', '" +
                Message.toString() + "', '" +
                CheckSum.toString()+ "' )";

        System.out.println(Query);

        try {
            statement.executeUpdate(new String(Query.getBytes(), "utf-8"));


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //Метод получения списка сообщений для сотрудника
    public ArrayList<Info> getMessages(int id)
    {
        ArrayList<Info> infos = new ArrayList<Info>(); //Список сообщений

        ResultSet rs = null;

        try {

            rs = statement.executeQuery("SELECT * FROM Message WHERE Recipient="+id); //Список сообщений

            while (rs.next()) {

                Info info = new Info();
                info.setSender(new String(rs.getString("Sender"))); //Отправитель
                info.setIdMessage(new String(rs.getString("idMessage"))); //id Сообщения
                info.setMessageSubject(new String(rs.getString("MessageSubject"))); //Тема сообщения

                infos.add(info);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return infos;
    }

    public String getMessage(String idMessage)
    {
        ResultSet rs = null;
        String Query = new String("SELECT * FROM Message WHERE idMessage="+idMessage);
        try {

            rs = statement.executeQuery(Query); //Таблица авторизации
            while (rs.next())
                return rs.getString("Message");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    //Удаляет сообщение из базы данных
    public void DeleteMessage (String idMessage)
    {
        String Query = "DELETE FROM Message WHERE idMessage="+idMessage;
        try {

            statement.executeUpdate(Query);
            System.out.println(Query);


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public String getRecipient(String idMessage) //id Получателя по id сообщения
    {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM Message WHERE idMessage="+idMessage); //Список сообщений
            rs.next();
            return rs.getString("Recipient");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getSender(String idMessage) //id отправителя по id сообщения
    {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM Message WHERE idMessage="+idMessage); //Список сообщений
            rs.next();
            return rs.getString("Sender");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getCheckSum(String idMessage) //Хеш-сумма по id сообщения
    {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM Message WHERE idMessage="+idMessage); //Список сообщений
            rs.next();
            return rs.getString("CheckSum");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}

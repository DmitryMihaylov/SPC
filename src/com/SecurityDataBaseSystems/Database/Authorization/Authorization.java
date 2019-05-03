package com.SecurityDataBaseSystems.Database.Authorization;

import com.SecurityDataBaseSystems.Database.DataBase;
import com.sun.javaws.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorization {

    private Statement statement;

    public void setStatement(Statement statement) {
       this.statement = statement;
    }

    public String getLogin(int id) //Логин
    {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM Authorization WHERE idEmployee="+id); //Таблица сотрудников
            rs.next();
            return rs.getString("Login");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getPassword(int id) //Пароль
    {
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM Authorization WHERE idEmployee="+id); //Таблица сотрудников
            rs.next();
            return rs.getString("Password");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}

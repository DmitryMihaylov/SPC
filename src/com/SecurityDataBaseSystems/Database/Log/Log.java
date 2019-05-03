package com.SecurityDataBaseSystems.Database.Log;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Statement;

public class Log {

    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setLog (com.SecurityDataBaseSystems.main.Log.Log log)
    {
        String Query = "INSERT INTO Log (" +
                "Category, " +
                "idEmploye, " +
                "CheckSumm, " +
                "Date, " +
                "Comment, " +
                "idSystem, " +
                "Event)" +

                " VALUES ('" +
                log.getCategory() + "', '" +
                log.getIdEmploye()+"', '"+
                log.getCheckSumm() + "', '" +
                log.getDate() + "', '" +
                log.getComment() + "', '" +
                log.getIdSystem() + "', '" +
                log.getEvent()+ "' )";

       // System.out.println(Query);

        try {
            statement.executeUpdate(new String(Query.getBytes(), "utf-8"));


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}

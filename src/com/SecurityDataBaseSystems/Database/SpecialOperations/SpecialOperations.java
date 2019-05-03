package com.SecurityDataBaseSystems.Database.SpecialOperations;



import com.SecurityDataBaseSystems.main.Main;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpecialOperations {
    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ArrayList<Operation> getOperations (Main main)
    {
        ArrayList<Operation> arrayList = new ArrayList<Operation>();

        ResultSet rs = null;

        String Query = new String("SELECT * FROM SpecialOperations");

        System.out.println(Query);

        try {

            rs = statement.executeQuery(Query); //Список операций
            Operation operation;

            int i=0;
            while (rs.next()) {

                i++;
                operation = new Operation(
                        rs.getString("NameSpecialOperation"),
                        rs.getString("Description"),
                        rs.getString("Allowance")
                );


                arrayList.add(operation);
            }


            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return arrayList;
    }

    public SpecialOperations() { }

    public void Add (Operation operation)
    {
        String sql = "INSERT INTO specialoperations (Description, Allowance, NameSpecialOperation) VALUES ('"+ operation.getDescription() +"', '"+ operation.getSpecialOperationName() +"' , '"+ operation.getSpecialOperationName() + "')";
        try {
            System.out.println(sql);
            try {
                statement.executeUpdate(new String(sql.getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public String NameToId(String name)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM SpecialOperations"); //Таблица Специальных операций
            while (rs.next())
                if (rs.getString("NameSpecialOperation").equals(name))
                    return rs.getString("idSpecialOperations");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public void Delete (Operation operation)
    {
        try {
            String Query = "DELETE FROM SpecialOperations WHERE idSpecialOperations = '"+NameToId(operation.getSpecialOperationName()) +"'";
            try {
                statement.executeUpdate(new String(Query.getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public String Search(Operation operation) {
        ResultSet rs = null;
        try {
            String Query;
            Query = "SELECT * FROM specialoperations WHERE idSpecialOperations ="+NameToId(operation.getSpecialOperationName());
            System.out.println(Query);
            rs = statement.executeQuery(Query); //Таблица авторизации
            while (rs.next()){
                String result = "Название операции: "+rs.getString("NameSpecialOperation")+"\n"+
                        "Описание: "+rs.getString("Description")+"\n";
                return result;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    /**********************************************ПРОВЕРИТЬ*********************************************/

    // Получение названия операции по id

    public String GetNameOfOperation(int idSpecialOperations){
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM NameSpecialOperation WHERE id="+idSpecialOperations); //Таблица сотрудников


            rs.next();
            return rs.getString("Name");


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    //Изменение имени операции по id

    public String SetNameOfOperation(int idSpecialOperations){
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("UPDATE * SET NameSpecialOperation WHERE id="+idSpecialOperations); //Таблица сотрудников


            rs.next();
            return rs.getString("Name");


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }
    /************************************************************************************************/
}

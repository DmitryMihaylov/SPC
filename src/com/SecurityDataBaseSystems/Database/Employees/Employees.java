package com.SecurityDataBaseSystems.Database.Employees;


import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Сотрудники

public class Employees {

    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    //Проверка логина на уникальность
    public boolean checkUniqueness(String login) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Authorization"); //Таблица авторизации
            while (rs.next()) {
                if (rs.getString("Login").equals(login)) {
                    System.out.println("Логин уже используется!");
                    return false;
                }
            }
            System.out.println("Логин свободен");
            rs.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Метод возвращает идетнификатор сотрудника или -1, если сотрудник не опознан
    public int Autorization(String login, String password)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Authorization"); //Таблица авторизации


            while (rs.next()) {

                System.out.println("Проверка сотрудника id("+rs.getString("idEmployee")+")");

                if (rs.getString("Login").equals(login) &&
                        rs.getString("Password").equals(password))
                {
                    System.out.println("Авторизация прошла успешно");
                    return Integer.parseInt(rs.getString("idEmployee"));
                }

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return -1;
    }


    public String getName(int id) //Имя
    {
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees="+id); //Таблица сотрудников


            rs.next();
            return rs.getString("Name");


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public String getSurname(int id) //Фамилия
    {

        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees="+id); //Таблица сотрудников

            rs.next();

            return rs.getString("Surname");

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public String getPatronymic(int id) //Отчество
    {
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees="+id); //Таблица сотрудников


            while (rs.next()) {

                return rs.getString("Patronymic");

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public String getPosition(int id) //Должность
    {
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees="+id); //Таблица сотрудников


            while (rs.next()) {

                return rs.getString("Position");

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    //Возвращает ID Сотрудника по логину
    public String LoginToId(String login)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Authorization"); //Таблица авторизации
            while (rs.next())
                if (rs.getString("Login").equals(login))
                    return rs.getString("idEmployee");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String IdToLogin(String id) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Authorization WHERE idEmployee="+id); //Таблица авторизации
            while (rs.next())
                return rs.getString("Login");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    //Изменение данных сотрудника
    public void Change (Employee employee )
    {
        String Query = "UPDATE Employees SET `Name`='"+employee.getName()+"', `Surname`='"+employee.getSurname()+"', `Patronymic`='"+employee.getPatronymic()+"', `Telephone`='"+employee.getTelephone()+"', `Position`='"+employee.getPosition()+"' WHERE `idEmployees`='"+LoginToId(employee.getLogin())+"';";
        System.out.println(Query);
        try {
            statement.executeUpdate(new String(Query.getBytes(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Добавить сотрудника
    public void Add (Employee employee )
    {
        try {
            //Добавление логина и пароля в таблицу Authorization
            statement.executeUpdate("INSERT INTO Authorization (Login, Password) VALUES ('"+ employee.getLogin() +"', '"+ employee.getPassword()+"' ) ");


            //Добавление данных в таблицу Employees

            String Query = "INSERT INTO Employees (" +
                    "Name, " +
                    "Patronymic, " +
                    "Position, " +
                    "Surname, " +
                    "Telephone)" +

                    " VALUES ('" +
                    employee.getName() + "', '" +
                    employee.getPatronymic() + "', '" +
                    employee.getPosition() + "', '" +
                    employee.getSurname() + "', '" +
                    employee.getTelephone() + "' )";


            System.out.println(Query);

            try {
                statement.executeUpdate(new String(Query.getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //Удаление сотрудника
    public void Delete (Employee employee )
    {
        //Нужно разобраться с багом метода LoginTOid при получении логина NullPointer
        try {

            //Удалеение из Employees
            Employees employees = new Employees();
            String Query = "DELETE FROM Employees WHERE idEmployees ="+ LoginToId(employee.getLogin());
            System.out.println(Query);
            try {
                statement.executeUpdate(new String(Query.getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            //Удаление из Authorization
            String Query2 = new String("DELETE FROM Authorization WHERE idEmployee ="+ LoginToId(employee.getLogin()));
            System.out.println(Query2);
            statement.executeUpdate(new String(Query2.getBytes(), "utf-8"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    public void Search(Employee employee) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees ="+LoginToId(employee.getLogin())); //Таблица авторизации
            while (rs.next()){
                employee.setName(rs.getString("Name"));
                employee.setSurname(rs.getString("Surname"));
                employee.setPatronymic(rs.getString("Patronymic"));
                employee.setPosition(rs.getString("Position"));
                employee.setTelephone(rs.getString("Telephone"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //Обновить данные сотрудника
}
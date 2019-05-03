package com.SecurityDataBaseSystems.Database;

import com.SecurityDataBaseSystems.Database.Access.Access;
import com.SecurityDataBaseSystems.Database.AccessMatrixEmployee.AccessMatrixEmployee;
import com.SecurityDataBaseSystems.Database.Authorization.Authorization;
import com.SecurityDataBaseSystems.Database.Documentation.Documentation;
import com.SecurityDataBaseSystems.Database.Employees.Employees;
import com.SecurityDataBaseSystems.Database.Equipment.Equipment;
import com.SecurityDataBaseSystems.Database.Log.Log;
import com.SecurityDataBaseSystems.Database.Message.Message;
import com.SecurityDataBaseSystems.Database.RSAkeys.RSAkeys;
import com.SecurityDataBaseSystems.Database.SpecialOperations.SpecialOperations;
import com.SecurityDataBaseSystems.Database.System.System;
import com.SecurityDataBaseSystems.main.Main;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.*;
import java.util.Properties;

public class DataBase  {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    //Данные для входа на MySQL сервер
    private String port="3306";
    private String Ip="localhost";
    private String login = "root";
    private String password = "root";

    public void setIp(String ip)
    {
        Ip = new String(ip);
    }

    public void setLogin(String Login)
    {
        login = new String(Login);
    }

    public void setPassword(String Password)
    {
        password = new String(Password);
    }

    public void setPort(String Port)
    {
        port = new String(Port);
    }

    //Флаг установленного с базой данных соединения
    public boolean ConnectionDataBaseFlag=false;

    private Connection connection; //Соединение с базой данных

    private Statement statement;


    //Таблицы:

    public Access access = new Access();

    public AccessMatrixEmployee accessMatrixEmployee = new AccessMatrixEmployee();

    public Authorization authorization= new Authorization();

    public Documentation documentation = new Documentation();

    public Employees employees = new Employees();

    public Equipment equipment = new Equipment() ;

    public Log log = new Log();

    public Message message = new Message();

    public RSAkeys rsaKeys = new RSAkeys();

    public SpecialOperations specialOperations = new SpecialOperations();

    public System system = new System();



    public void Start() {

    if (ConnectionDataBaseFlag==false)
    {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            Properties properties=new Properties();
            properties.setProperty("user",login);
            properties.setProperty("password",password);
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","cp1251");

            String url = new String("jdbc:mysql://"+Ip+":"+port+"/special_purpose_center");

            connection = DriverManager.getConnection(url, properties);

            if (!connection.isClosed())
                java.lang.System.out.println("Соединение с базой данных установлено");

            statement = connection.createStatement();

            //Соединение установлено
            ConnectionDataBaseFlag=true;

            SetStatements();  //"Сцепление" дочерних классов с основным (управляющим) DataBase
            SetDataBase();
            SetMains();

        } catch (SQLException e) {
            java.lang.System.out.println("Не удалось подключиться к базе данных");
            e.printStackTrace();
        }
    }
    else {
        java.lang.System.out.println("");
    }




    }

    public void Stop () {

        if (ConnectionDataBaseFlag==true)
        {
            try {
                connection.close();
                statement.close();

                if (connection.isClosed() && statement.isClosed())
                    java.lang.System.out.println("Соединение с базой данных закрыто");

                //Соединение закрыто
                ConnectionDataBaseFlag=false;


            } catch (SQLException e) {

                java.lang.System.out.println("Не удалось закрыть соединение с базой данных");
                return;
            }
        }

        else {
            java.lang.System.out.println("Соединение с базой данных не установлено");
        }


    }



    //"Сцепление" дочерних классов с основным (управляющим) DataBase
    public void SetStatements ()
    {
        access.setStatement(statement);
        accessMatrixEmployee.setStatement(statement);
        authorization.setStatement(statement);
        documentation.setStatement(statement);
        employees.setStatement(statement);
        equipment.setStatement(statement);
        log.setStatement(statement);
        message.setStatement(statement);
        rsaKeys.setStatement(statement);
        specialOperations.setStatement(statement);
        system.setStatement(statement);

    }

    public void SetMains ()
    {
        documentation.setMain(main);
    }

    public void SetDataBase()
    {
        rsaKeys.setDataBase(this);
    }


}

package com.SecurityDataBaseSystems.Database.Access;


import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Access {

    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    //Преобразует идентификатор в вид допуска
    /*
        Идентификатор вида дпуска
        1 Особой важности
        2 Совершенно секретно
        3 Секретно
        4 Конфиденциально
     */

    public String getAllowance(String idEmployee)
    {
        switch (getIDAllowance (idEmployee)) {
        case "1":
            return "Особая важность";

        case "2":
            return "Совершенно-секретно";

        case "3":
            return "Секретно";

        case "4":
            return "Конфиденциально";

        default:
            return null;

    }
    }

    //Вернет идентификатор вида допуска
    public String getIDAllowance (String idEmployee)
    {
        ResultSet rs = null;


        try {
            rs = statement.executeQuery("SELECT * FROM Access WHERE idEmployee="+idEmployee); //Таблица допусков (Access)
             rs.next();
             return rs.getString("idAccess");
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    /*Обновляет запись в таблице допуска к информации в соответствии с выставленной категорией секретности
      Добавляет запись, если идентификатор сотрудника (idEmployee) не найден.
     */
    public void setIDAllowance (String idEmployee, String idAccess)
    {
        try {
            String Query = "SELECT * FROM Access WHERE idEmployee = " + idEmployee;

            //Вывод запроса для контроля
            System.out.println(Query);

            ResultSet rs = statement.executeQuery(new String(Query.getBytes(), "utf-8"));

            //Если такой записи нет, то создать новую запись для сотрудника
            boolean temp = rs.next();
            rs.close();

            if (!temp) {

                //Создать новую запись в таблице допусков для сотрудника
                String SetAccessQuery =
                        "INSERT INTO Access (" + "idEmployee, " + "idAccess " + ")"+ " VALUES ('" + idEmployee + "', '" +idAccess+"' )";

                statement.executeUpdate(new String(SetAccessQuery.getBytes(), "utf-8"));

                //Вывод запроса для контроля
                System.out.println(SetAccessQuery);

            } else {

                //Обновить запись в таблице допусков для сотрудника
                String UpdeteAccessQuery = "UPDATE Access SET " +
                        "idAccess ='" + idAccess + "' " + "WHERE idEmployee =" + idEmployee;
                statement.executeUpdate(new String(UpdeteAccessQuery.getBytes(), "utf-8"));

                //Вывод запроса для контроля
                System.out.println(UpdeteAccessQuery);

            }

        } catch (SQLException e) {
            e.printStackTrace();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

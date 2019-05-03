package com.SecurityDataBaseSystems.Database.AccessMatrixEmployee;


import com.SecurityDataBaseSystems.Crypto.SHA;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessMatrixEmployee {
    private Statement statement;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    //Метод проверяет, явлется ли сотрудник администратором
    //true - да
    //false - нет
    public boolean isEmployeeAdmin(int id)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Employees WHERE idEmployees="+id); //Таблица сотрудников


            while (rs.next()) {

                if (rs.getBoolean("FlagAdministrator"))
                {
                    System.out.println("Сотрудник является администратором");
                    return true;
                }
                else {
                    System.out.println("Сотрудник не является администратором");
                    return false;
                }

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }


    /*
    *   Метод добавляет или изменяет матрицу доступа, если для этого сторудника нет записи матрицы доступа
    * */
    public void SetEmployeMatrix (EmployeMatrix employeMatrix, String idEmploye) {
	    try {
		    String Query = "SELECT * FROM AccessMatrixEmployee WHERE idEmployee =" + idEmploye;
		    ResultSet rs = statement.executeQuery(new String(Query.getBytes(), "utf-8"));

		    //Если матрицы нет, то создать новую запись для матрицы доступа
		    boolean temp = rs.next();
		    rs.close();
			SHA sha = new SHA();
			String hash = new String(

					sha.StringToSHA1(idEmploye+"QkI2N0Y4QzA2QzhF"+ 	//Соль хеширования
							employeMatrix.CheckAccess(EmployeMatrix.BlockEmploye)+
							employeMatrix.CheckAccess(EmployeMatrix.ButtonAdministration) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonControlSPC) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonEmployees) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonJob) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonPrivateOffice) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonSettings) +
							employeMatrix.CheckAccess(EmployeMatrix.ButtonSpecialOperation) +
							employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Equipment) +
							employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Object) +
							employeMatrix.CheckAccess(EmployeMatrix.Job_Documents) +
							employeMatrix.CheckAccess(EmployeMatrix.Job_Equipment) +
							employeMatrix.CheckAccess(EmployeMatrix.Job_Operations) +
							employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Keys) +
							employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Messages))
			);




		    if (!temp) {

			    //Создать новую запись для матрицы доступа
			    String SetAccessMatrixQuery = "INSERT INTO AccessMatrixEmployee (" +
					    "idEmployee, " +
					    "BlockEmploye, " +
					    "ButtonAdministration, " +
					    "ButtonControlSPC, " +
					    "ButtonEmployees, " +
					    "ButtonJob, " +
					    "ButtonPrivateOffice, " +
					    "ButtonSettings, " +
					    "ButtonSpecialOperation, " +
					    "ControlSPC_Equipment, " +
					    "ControlSPC_Object, " +
					    "Job_Documents, " +
					    "Job_Equipment, " +
					    "Job_Operations, " +
					    "PrivateOffice_Keys, " +
					    "PrivateOffice_Messages, " +
					    "CheckSum"+")"+

					    " VALUES ('" +
					    idEmploye + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.BlockEmploye) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonAdministration) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonControlSPC) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonEmployees) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonJob) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonPrivateOffice) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonSettings) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ButtonSpecialOperation) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Equipment) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Object) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.Job_Documents) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.Job_Equipment) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.Job_Operations) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Keys) + "', '" +
					    employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Messages) + "', '" +
						hash + "' )";

			    statement.executeUpdate(new String(SetAccessMatrixQuery.getBytes(), "utf-8"));

			    //Вывод запроса для контроля
			    System.out.println(SetAccessMatrixQuery);

		    } else {

			    //Обновить запись матрицы доступа
			    String UpdeteAccessMatrixQuery = "UPDATE AccessMatrixEmployee SET " +
					    "BlockEmploye ='" + employeMatrix.CheckAccess(EmployeMatrix.BlockEmploye) + "' " +
					    ", ButtonAdministration ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonAdministration) + "' " +
					    ", ButtonControlSPC ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonControlSPC) + "' " +
					    ", ButtonEmployees ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonEmployees) + "' " +
					    ", ButtonJob ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonJob) + "' " +
					    ", ButtonPrivateOffice ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonPrivateOffice) + "' " +
					    ", ButtonSettings ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonSettings) + "' " +
					    ", ButtonSpecialOperation ='" + employeMatrix.CheckAccess(EmployeMatrix.ButtonSpecialOperation) + "' " +
					    ", ControlSPC_Equipment ='" + employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Equipment) + "' " +
					    ", ControlSPC_Object ='" + employeMatrix.CheckAccess(EmployeMatrix.ControlSPC_Object) + "' " +
					    ", Job_Documents ='" + employeMatrix.CheckAccess(EmployeMatrix.Job_Documents) + "' " +
					    ", Job_Equipment ='" + employeMatrix.CheckAccess(EmployeMatrix.Job_Equipment) + "' " +
					    ", Job_Operations ='" + employeMatrix.CheckAccess(EmployeMatrix.Job_Operations) + "' " +
					    ", PrivateOffice_Keys ='" + employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Keys) + "' " +
					    ", PrivateOffice_Messages ='" + employeMatrix.CheckAccess(EmployeMatrix.PrivateOffice_Messages) + "' " +
					    ", CheckSum ='" + hash + "' " +
					    "WHERE idEmployee =" + idEmploye;
			    statement.executeUpdate(new String(UpdeteAccessMatrixQuery.getBytes(), "utf-8"));

			    //Вывод запроса для контроля
			    System.out.println(UpdeteAccessMatrixQuery);

		    }

	    } catch (SQLException e) {
		    e.printStackTrace();


	    } catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
	    }
    }
    /*
        Метод возвращает текущую матрицу доступа к информации для сотрудника
     */
    public EmployeMatrix GetGurrenEmployeMatrix (String idEmploye)
    {
        ResultSet rs = null;

        String Query = new String("SELECT * FROM AccessMatrixEmployee WHERE idEmployee="+idEmploye);

        try {
            rs = statement.executeQuery(Query); //Таблица матрицы доступа (AccessMatrixEmployee)

          rs.next();

          //Текущая матрица доступа к информации для сотрудника
          EmployeMatrix EM = new EmployeMatrix();

        //Заполнение матрицы доступа
         EM.SetElement(EmployeMatrix.BlockEmploye, Boolean.getBoolean(rs.getString("BlockEmploye"))); //Блокировка сотрудника
         EM.SetElement(EmployeMatrix.ButtonAdministration, Boolean.getBoolean(rs.getString("ButtonAdministration"))); //Кнопка администратор
         EM.SetElement(EmployeMatrix.ButtonControlSPC, Boolean.getBoolean(rs.getString("ButtonControlSPC"))); //Кнопка Управление ЦСН
         EM.SetElement(EmployeMatrix.ButtonEmployees, Boolean.getBoolean(rs.getString("ButtonEmployees"))); //Кнопка сотрудники
         EM.SetElement(EmployeMatrix.ButtonJob, Boolean.getBoolean(rs.getString("ButtonJob"))); //Кнопка Работа
         EM.SetElement(EmployeMatrix.ButtonPrivateOffice, Boolean.getBoolean(rs.getString("ButtonPrivateOffice"))); //Кнопка личный кабинет
         EM.SetElement(EmployeMatrix.ButtonSettings, Boolean.getBoolean(rs.getString("ButtonSettings"))); //Кнопка настройки
         EM.SetElement(EmployeMatrix.ButtonSpecialOperation, Boolean.getBoolean(rs.getString("ButtonSpecialOperation"))); //Кнопка специальные операции
         EM.SetElement(EmployeMatrix.ControlSPC_Equipment, Boolean.getBoolean(rs.getString("ControlSPC_Equipment"))); //Управление ЦСН - Оборудование
         EM.SetElement(EmployeMatrix.ControlSPC_Object, Boolean.getBoolean(rs.getString("ControlSPC_Object"))); //Управление ЦСН - Объект
         EM.SetElement(EmployeMatrix.Job_Documents, Boolean.getBoolean(rs.getString("Job_Documents"))); //Работа-Документы
         EM.SetElement(EmployeMatrix.Job_Equipment, Boolean.getBoolean(rs.getString("Job_Equipment"))); //Работа - оборудование
         EM.SetElement(EmployeMatrix.Job_Operations, Boolean.getBoolean(rs.getString("Job_Operations"))); //Работа - Операции
         EM.SetElement(EmployeMatrix.PrivateOffice_Keys, Boolean.getBoolean(rs.getString("PrivateOffice_Keys"))); //Личный кабинет - ключи
         EM.SetElement(EmployeMatrix.PrivateOffice_Messages, Boolean.getBoolean(rs.getString("PrivateOffice_Messages"))); //Личный кабинет - сообщения

         rs.close();


         return EM;

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Ошибка при выполнении следующего запроса: " + Query);

        }

        return null;
    }
}

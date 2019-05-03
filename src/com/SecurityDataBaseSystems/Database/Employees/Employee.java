package com.SecurityDataBaseSystems.Database.Employees;

import com.SecurityDataBaseSystems.Database.AccessMatrixEmployee.EmployeMatrix;
import com.SecurityDataBaseSystems.main.Main;

//Сотрудник
public class Employee {

    public Employee ()
    {

    }

    private String name;        //Имя
    private String surname;     //Фамилия
    private String patronymic;  //Отчество
    private String position;    //Должность
    private String telephone;   //Телефон
    private String login;       //Логин
    private String password;    //Пароль
    private String id;          //Идентификатор сотрудника


    //Матрица-доступа
    private EmployeMatrix employeMatrix = new EmployeMatrix();

    //Обновляет текущую матрицу в программе
    public void UpdateMatrixInProgram (Main main)
    {
        this.employeMatrix = main.dataBase.accessMatrixEmployee.GetGurrenEmployeMatrix(Integer.toString(main.getIdEmployee()));
    }

    public EmployeMatrix getEmployeMatrix () {
        return employeMatrix;
    }

    //Конструктор
    public Employee(String name, String surname, String patronymic, String position, String telephone, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.telephone = telephone;
        this.login = login;
        this.password = password;


    }



    public Employee (String name, String surname, String patronymic,String telephone, String position) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    int booleanToInt(Boolean b) {
        return b.compareTo(false);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Имя: "+getName()+"\n"+
                        "Фамилия: "+getSurname()+"\n"+
                        "Отчество"+getPatronymic()+"\n"+
                        "Телефон"+getTelephone()+"\n"+
                        "Должность"+getPosition()+"\n"+
                        "Логин"+getLogin()+"\n";

    }

    //Возвращает ФИО
    public String getSurnameNamePatronymic ()
    {
        return getSurname()+" "+getName()+" "+getPatronymic();
    }


}

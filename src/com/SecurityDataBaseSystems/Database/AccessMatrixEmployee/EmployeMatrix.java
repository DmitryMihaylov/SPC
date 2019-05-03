package com.SecurityDataBaseSystems.Database.AccessMatrixEmployee;

/*

Вспомогательный класс для работы с матрицей доступа
Создано: 01.05.18 Гудин Юрий
 */

public class EmployeMatrix {

    //Идентификаторы полей в базе данных
    public static final int BlockEmploye=0;             //Блокировка сотрудника
    public static final int ButtonAdministration=1;     //Кнопка администратор
    public static final int	ButtonControlSPC=2;         //Кнопка Управление ЦСН
    public static final int ButtonEmployees=3;          //Кнопка сотрудники
    public static final int ButtonJob=4;                //Кнопка Работа
    public static final int ButtonPrivateOffice=5;      //Кнопка личный кабинет
    public static final int ButtonSettings=6;           //Кнопка настройки
    public static final int ButtonSpecialOperation=7;   //Кнопка специальные операции
    public static final int ControlSPC_Equipment=8;     //Управление ЦСН - Оборудование
    public static final int ControlSPC_Object=9;        //Управление ЦСН - Объект
    public static final int Job_Documents=10;           //Работа-Документы
    public static final int Job_Equipment=11;           //Работа - оборудование
    public static final int Job_Operations=12;          //Работа - Операции
    public static final int PrivateOffice_Keys=13;      //Личный кабинет - ключи
    public static final int PrivateOffice_Messages=14;  //Личный кабинет - сообщения

    //Матрица
    private boolean Matrix[] = new boolean[15];

    //Конструктор
    public EmployeMatrix() {

        //Обнуление всей матрицы доступа
        for (int i=0; i<Matrix.length; i++)
            Matrix[i] = false;
    }


    /*
       Метод проверки доступа к информации

    */
    public int CheckAccess(int element)
    {
        if (element>Matrix.length) return 1;
        return Matrix[element]?1:0;
    }


    /*
           Метод изменения значений в матрице доступа
     */
    public void SetElement(int element, boolean value)
    {
        Matrix[element] = value;
    }

    @Override
    public String toString()
    {
        return "\n\n    -----Матрица доступа-----"+ " \n"+
                "Блокировка сотрудника: "           + CheckAccess(EmployeMatrix.BlockEmploye) + " \n"+
                "Кнопка Администратор: "             + CheckAccess(EmployeMatrix.ButtonAdministration)+ " \n"+
                "Кнопка Управление ЦСН: "            + CheckAccess(EmployeMatrix.ButtonControlSPC)+ " \n"+
                "Кнопка Сотрудники: "                + CheckAccess(EmployeMatrix.ButtonEmployees)+ " \n"+
                "Кнопка Работа: "                    + CheckAccess(EmployeMatrix.ButtonJob)+ " \n"+
                "Кнопка Личный кабинет: "            + CheckAccess(EmployeMatrix.ButtonPrivateOffice)+ " \n"+
                "Кнопка Настройки: "                 + CheckAccess(EmployeMatrix.ButtonSettings)+ " \n"+
                "Кнопка Специальные операции: "      + CheckAccess(EmployeMatrix.ButtonSpecialOperation)+ " \n"+
                "Управление ЦСН - оборудование: "    + CheckAccess(EmployeMatrix.ControlSPC_Equipment)+ " \n"+
                "Управление ЦСН - объект: "          + CheckAccess(EmployeMatrix.ControlSPC_Object)+ " \n"+
                "Работа - документы: "               + CheckAccess(EmployeMatrix.Job_Documents)+ " \n"+
                "Работа - оборудование: "            + CheckAccess(EmployeMatrix.Job_Equipment)+ " \n"+
                "Работа - операции: "                + CheckAccess(EmployeMatrix.Job_Operations)+ " \n"+
                "Личный кабинет - ключи: "           + CheckAccess(EmployeMatrix.PrivateOffice_Keys)+ " \n"+
                "Личный кабинет - сообщения: "       + CheckAccess(EmployeMatrix.PrivateOffice_Messages)+ " \n"

                ;
    }

}

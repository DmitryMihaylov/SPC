package com.SecurityDataBaseSystems.Database.SpecialOperations;



import com.google.gson.Gson;

import java.util.ArrayList;

public class Operation {
    private String SpecialOperationName;
    private String Description;
    private String Allowance;
    public ArrayList<String> Employes = new ArrayList<String>();//Сотрудники
    public ArrayList<String> Materials = new ArrayList<String>();


    public Operation(String specialOperationName, String description, String allowance) {

        this.SpecialOperationName = new String(specialOperationName);
        this.Description = new String(description);
        setAllowance(allowance);
    }

    public Operation()
    {

    }

    public Operation(int idOperation, String JSON) {

    }

    public String getSpecialOperationName() {
        return SpecialOperationName;
    }

    public void setSpecialOperationName(String specialOperationName) {
        this.SpecialOperationName = specialOperationName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAllowance() {
        return Allowance;
    }



    public void setAllowance(String allowance) {

        /*
        Free
            Степень конфиденциальности/секретности

            Формы допуска:

                1-я (самая высокая) форма – для лиц, которые работают со сведениями особой важности
                2-я форма - для работников, соприкасающихся с совершенно секретными сведениями
                3-я (низшая) форма - для лиц, работающих с (просто) секретными сведениями22
            4 - конфиденциально
            5 - открыто
            0- закрыто
         */

        switch (allowance) {
            case "0": {
                this.Allowance = new String("Закрыто");
            }
            break;

            case "1": {
                this.Allowance = new String("Особая важность");
            }
            break;

            case "2": {
                this.Allowance = new String("Совершенно-секретно");
            }
            break;

            case "3": {
                this.Allowance = new String("Cекретно");
            }
            break;

            case "4": {
                this.Allowance = new String("Конфиденциально");
            }
            break;

            case "5": {
                this.Allowance = new String("Открыто");
            }
            break;

        }
    }

    @Override
    public String toString() {
        return
                "Название операции: "+getSpecialOperationName()+"\n"+
                "Описание операции: "+getDescription()+"\n"+
                "Степень конфиденциальности: "+getAllowance()+"\n";
    }

    //Работа с GSON
    public void TestJSON()
    {

    //Заполнение тестовыми идентификаторами
        //Участники специальной операции
        Employes.add("101");
        Employes.add("102");
        Employes.add("103");
        Employes.add("104");

        //Материалы специальной операции (Документы)
        Materials.add("8");
        Materials.add("9");
        Materials.add("10");



        Gson gson = new Gson();
        String jsonString = gson.toJson(this);

        System.out.println("json Operation: " + jsonString);


        Operation TestOperation = gson.fromJson(jsonString, Operation.class);

       System.out.println(TestOperation.toString());
    }
}

package com.SecurityDataBaseSystems.Database.RSAkeys;

import com.SecurityDataBaseSystems.Database.DataBase;

public class RSApublicKeyEmoloye {

    private String RSAkey;
    private Integer idEmploye;
    private String Employe; //ФИО
    private DataBase dataBase;


    public RSApublicKeyEmoloye(String RSAkey, int idEmploye, DataBase dataBase) {
        this.RSAkey = RSAkey;
        this.idEmploye = idEmploye;
        this.dataBase = dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    public String getRSAkey() {
        return RSAkey;
    }

    public String getEmploye() {

        this.Employe=new String(
                dataBase.employees.getSurname(idEmploye)+" "+
                dataBase.employees.getName(idEmploye)+" "+
                dataBase.employees.getPatronymic(idEmploye));

        return Employe;
    }
}

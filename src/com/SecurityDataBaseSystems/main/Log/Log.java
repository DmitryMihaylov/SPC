package com.SecurityDataBaseSystems.main.Log;

/*
Класс, предназначенный для логирования операций

 */

import com.SecurityDataBaseSystems.Crypto.SHA;
import com.SecurityDataBaseSystems.main.Main;


import java.util.Date;

public class Log {

   //Категории событий
   public static final int NORMAL_EVENT     = 0; //Обычные события (min)
   public static final int WARNING_EVENT    = 1; //События - предупреждения  !
   public static final int SERIOUS_EVENT    = 2; //Важные события            !!
   public static final int CRITICAL_EVENT   = 3; //Критические события (max) !!!

   private Main main;

    private String Category;    //Категория
    private String Comment;     //Комменарий
    private String Date;        //Дата
    private String Event;       //Событие
    private String idEmploye;   //id Сотрудника
    private String idSystem;    //id Системы или имя компьютера
    private String CheckSumm;   //Контроль  ная сумма

    //Конструктор
    public Log (String Event, int Category, String Comment, Main main)
    {
        setMain(main);

        setCategory(Integer.toString(Category));
        setComment(Comment);
        setDate(new Date().toString());
        setEvent(Event);
        setIdEmploye(Integer.toString(main.getIdEmployee()));
        setIdSystem(System.getProperty("user.name"));

        //Рассчет контрольной суммы
        SHA sha = new SHA();

        setCheckSumm(sha.StringToSHA1(
                   this.Category+
                        this.Comment+
                        this.Date+
                        this.Event+
                        this.idEmploye+
                        this.idSystem
        ));

        main.dataBase.log.setLog(this);
    }


    private void setCategory(String category) {
        Category = category;
    }

    private void setComment(String comment) {
        Comment = comment;
    }

    private void setDate(String date) {
        Date = date;
    }

    private void setEvent(String event) {
        Event = event;
    }

    private void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    private void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    private void setMain(Main main) {
        this.main = main;
    }

    public void setCheckSumm(String checkSumm) {
        CheckSumm = checkSumm;
    }

    public String getCategory() {
        return Category;
    }

    public String getComment() {
        return Comment;
    }

    public String getDate() {
        return Date;
    }

    public String getEvent() {
        return Event;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public String getCheckSumm() {
        return CheckSumm;
    }
}

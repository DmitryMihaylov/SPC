package com.SecurityDataBaseSystems.main.Block;

import com.SecurityDataBaseSystems.Database.DataBase;
import com.SecurityDataBaseSystems.main.Log.Log;
import com.SecurityDataBaseSystems.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerLockScreen{


    @FXML
    private Pane pane;

    @FXML
    private Button buttonBack;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField textLogin;

    @FXML
    private Button buttonEnter;

    private Main main;

    private DataBase dataBase; //Ссылка на класс для работы с базами данных



    public void Init()  {


        buttonEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //Взятие id из базы данных
                int idEmployee = dataBase.employees.Autorization(textLogin.getText(), passwordField.getText());

                //Проверка результата
                if (idEmployee == -1)
                {
                    System.out.println("Неверные данные");

                    System.out.println("Завершение программы...");

                    //Отключение базы данных
                    dataBase.Stop();

                    try {

                        //Торможение 3 секунды
                        Thread.sleep(3000);


                        //Завершение работы программы
                        System.exit(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("При завершении программы возникли ошибки");
                    }
                }
                else {

                    //В случае корректного ввода данных отправить ID в главный класс
                    main.setIdEmployee(idEmployee);
                    new Log("Кнопка ввод", Log.NORMAL_EVENT, "", main); //Логирование операции

                    main.setPassword(passwordField.getText());
                    //Открыть главную рабочую форму
                    main.ShowWorkForm();

                }
            }

        });


        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new Log("Кнопка назад", Log.NORMAL_EVENT, "", main); //Логирование операции

                main.dataBase.Stop();

                main.initRootLayout();

            }
        });

    }
    public void setMain(Main main)
    {
        this.main = main;
    }

    public void setDataBase(DataBase dataBase)
    {
        this.dataBase = dataBase;
    }
}
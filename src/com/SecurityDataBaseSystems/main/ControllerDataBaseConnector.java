package com.SecurityDataBaseSystems.main;

import com.SecurityDataBaseSystems.Database.DataBase;
import com.SecurityDataBaseSystems.main.Block.ControllerLockScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerDataBaseConnector {


   private Main main; //Точка входа

   private DataBase dataBase;//Класс для работы с базами данных


    @FXML
    private Button Enter;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private TextField login;

    @FXML
    private Button buttonExit;

    @FXML
    private PasswordField password;


    public void Init () //Стартер-инициализатор
    {
        //Кнопка ВХОД
        Enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Вход");



                main.dataBase = new DataBase();


                dataBase = main.dataBase;
                dataBase.setMain(main);
                dataBase.setLogin(login.getText());
                dataBase.setPassword(password.getText());
                dataBase.setPort(port.getText());
                dataBase.setIp(ip.getText());

                //Подключение к базе данных
                dataBase.Start();


                //Запуск блокировкщика системы с проверкой по базе данных
                ControllerLockScreen controllerLockScreen = (ControllerLockScreen) main.OpenForm("Block/FormLockScreen.fxml");
                controllerLockScreen.setMain(main); //Поместить главный класс
                controllerLockScreen.setDataBase(main.dataBase); //Поместить главный класс для работы с базой данных
                controllerLockScreen.Init(); //Инициализировать интерфейс

                //Отключение базы данных
                //dataBase.Stop();
            }

        });

        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            try {
                main.dataBase.Stop();

            }
            catch (NullPointerException e) {
                System.out.println("Соединение сбазой данных не было установлено");
            }


            System.exit(0);
             }
        });
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void CloseThisForm()
    {
        main.primaryStage.close();
    }
}



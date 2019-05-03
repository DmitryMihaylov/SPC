package com.SecurityDataBaseSystems.main.Pane.PrivateOffice.MessageWindow;

/*
 * Класс окна для работы с сообщениями
 *
 * */

import com.SecurityDataBaseSystems.Database.Message.Info;
import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Main;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MessageWindow {

    private Pane pane;
    private ControllerMessageWindow controllerMessageWindow;
    private Stage stage = new Stage();
    private Main main;

    private String transformPath = new String(PathWindowsLinux.transformPath("Pane/PrivateOffice/MessageWindow/PaneMessageWindow.fxml"));

    //Конструктор
   public MessageWindow(Info info, Main main)
    {

        this.main = main;

        stage.setTitle("Сообщение");

        stage.setResizable(false); //Невозможно развернуть на весь экран

        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Main.class.getResource(transformPath));

        try {
            pane = (Pane) loader.load();
            //stage.setScene(new Scene(pane));

            //Кроссплатформенный путь к файлу css
            transformPath = new String(PathWindowsLinux.transformPath("Style.css"));


            Scene scene = new Scene(pane);
            scene.getStylesheets().add(transformPath); //Утановка таблицы стилей

            //primaryStage.setScene(new Scene(rootLayout)); //Предыдущая версия

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

        controllerMessageWindow = loader.getController();
        controllerMessageWindow.Init();
        controllerMessageWindow.setMessageWindow(this);
        controllerMessageWindow.setMain(main);
        controllerMessageWindow.UnpackMessage(info);
    }


    //Открыть окно с сообщением
    public void ShowMessageWindow()
    {
    stage.show();
    }

    public  void  CloseMessageWindow()
    {
        stage.close();
    }

}

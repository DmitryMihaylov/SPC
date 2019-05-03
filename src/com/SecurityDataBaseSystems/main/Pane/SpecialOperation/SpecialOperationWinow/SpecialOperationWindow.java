package com.SecurityDataBaseSystems.main.Pane.SpecialOperation.SpecialOperationWinow;

import com.SecurityDataBaseSystems.Database.SpecialOperations.Operation;
import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/*
    Класс окна для работы со специальными операциями

 */

public class SpecialOperationWindow {

    private Pane pane;
    private ControllerSpecialOperationWindow controllerSpecialOperationWindow; //Котроллер окна специальных операций
    private Stage stage = new Stage();
    private Main main;

    private String transformPath = new String(PathWindowsLinux.transformPath("Pane/SpecialOperation/SpecialOperationWinow/SpecialOperationWindow.fxml"));


    //Конструктор
    public SpecialOperationWindow(Operation operation, Main main)
    {

        this.main = main;

        stage.setTitle("ОПЕРАЦИЯ: "+operation.getSpecialOperationName());

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

        controllerSpecialOperationWindow = loader.getController();
        controllerSpecialOperationWindow.setMain(main);
        controllerSpecialOperationWindow.setOperation(operation);

        controllerSpecialOperationWindow.Init();


    }


    //Открыть окно
    public void ShowWindow()
    {
        stage.show();
    }

    //Закрыть окно
    public  void  CloseWindow()
    {
        stage.close();
    }

}

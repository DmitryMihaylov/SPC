package com.SecurityDataBaseSystems.main.GeneratePassword;

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Message.ControllerMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GeneratePassword {
    Object parent;

    private Pane pane;
    private ControllerGeneratePassword controllerGeneratePassword;
    private Stage stage = new Stage(StageStyle.UNDECORATED);

    private String transformPath = new String(PathWindowsLinux.transformPath("GeneratePassword/FormGeneratePassword.fxml"));

    //Конструктор
    public GeneratePassword ()
    {

        stage.initModality(Modality.APPLICATION_MODAL); //Модальное окно

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


            stage.setResizable(false);


        } catch (IOException e) {
            e.printStackTrace();
        }

        controllerGeneratePassword = loader.getController();

        controllerGeneratePassword.setGeneratePasswordParent(this);
        controllerGeneratePassword.Init();

    }

    public void setParent(Object parent) {
        this.parent = parent;
    }


    //Закрыть сообщение
    public void Close ()
    {
        stage.close();
    }

    //Открыть сообщение
    public void Show ()
    {
        stage.show();
    }

}

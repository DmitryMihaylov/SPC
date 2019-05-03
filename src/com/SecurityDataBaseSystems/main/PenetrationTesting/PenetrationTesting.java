package com.SecurityDataBaseSystems.main.PenetrationTesting;

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PenetrationTesting {
    Object parent;

    private Pane pane;
    private ControllerPenetrationTesting controllerPenetrationTesting;
    private Stage stage = new Stage();

    private String transformPath = new String(PathWindowsLinux.transformPath("PenetrationTesting/FormPenetrationTesting.fxml"));

    //Конструктор
    public PenetrationTesting ()
    {

       // stage.initModality(Modality.APPLICATION_MODAL); //Модальное окно

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
            stage.setTitle("Тестирование на проникновение");


        } catch (IOException e) {
            e.printStackTrace();
        }

        controllerPenetrationTesting = loader.getController();
        controllerPenetrationTesting.setPenetrationTesting(this);
        controllerPenetrationTesting.Init();

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

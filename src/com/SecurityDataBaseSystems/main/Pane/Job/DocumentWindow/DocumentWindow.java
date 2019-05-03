package com.SecurityDataBaseSystems.main.Pane.Job.DocumentWindow;

/*
 * Класс окна для работы с документами
 *
 * */

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.Database.Documentation.Document;
import com.SecurityDataBaseSystems.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class DocumentWindow {

    private Pane pane;
    private ControllerDocumentWindow controllerDocumentWindow;
    private Stage stage = new Stage();
    private Main main;

    private String transformPath = new String(PathWindowsLinux.transformPath("Pane/Job/DocumentWindow/PaneDocumentWindow.fxml"));

    //Конструктор
   public DocumentWindow(Document document, Main main)
    {

        this.main = main;

        stage.setTitle("Документ");

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

        controllerDocumentWindow = loader.getController();
        controllerDocumentWindow.Init();

        controllerDocumentWindow.setMessageWindow(this);
        controllerDocumentWindow.setMain(main);
        controllerDocumentWindow.UnpackDocument(document);
    }


    //Открыть окно с сообщением
    public void ShowDocumentWindow()
    {
    stage.show();
    }

    public  void  CloseDocumentiWndow()
    {
        stage.close();
    }

}

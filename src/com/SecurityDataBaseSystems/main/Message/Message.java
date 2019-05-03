package com.SecurityDataBaseSystems.main.Message;


/*
 * Класс вывода информационных сообщений
 *
 * */

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Message {

   private Pane pane;
   private ControllerMessage controllerMessage;
   private Stage stage = new Stage(StageStyle.UNDECORATED);

   public static final int WARNING=1; //Код предупреждения
   public static final int DANGER=2;  //Код опасности
   public static final int INFO=3;   //Код штатного информационного сообщения
   public static final int GOOD=5;   //Код положительного информационного сообщения
   public static final int QESTION=4;   //Код режима вопроса

   public boolean ButtonOk=false;   //Нажата кнопка Ок
   public boolean ButtonCancel=false; //Нажата кнопка Отмена

   private String transformPath = new String(PathWindowsLinux.transformPath("Message/FormMessage.fxml"));

    //Конструктор
  public Message (String message, int code)
    {

       // stage.setTitle(message);
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

        controllerMessage = loader.getController();
        controllerMessage.setMessageParent(this);

        controllerMessage.Init();
        controllerMessage.SetMessage(message);

        controllerMessage.setCode(code);

    }

    //Закрыть сообщение
    public void CloseMessage ()
    {
        stage.close();
    }

    //Открыть сообщение
    public void ShowMessage ()
    {
        stage.show();
    }


}

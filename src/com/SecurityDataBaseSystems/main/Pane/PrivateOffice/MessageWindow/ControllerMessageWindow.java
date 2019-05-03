package com.SecurityDataBaseSystems.main.Pane.PrivateOffice.MessageWindow;

import com.SecurityDataBaseSystems.Database.Message.Info;
import com.SecurityDataBaseSystems.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerMessageWindow {

    private MessageWindow messageWindow;

    private Main main;

    @FXML
    private TextField textFieldRecipient;

    @FXML
    private TextArea textAreaMessage;

    @FXML
    private TextField textFieldMessageSubject;

    @FXML
    private Button buttonSendMessage;

    @FXML
    private Button buttonSaveToDocuments;

    @FXML
    private Button buttoClear;

    @FXML
    private Button buttonDelete;



    private String idMessage;



    public void Init ()
    {

        buttonSendMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Отправить сообщение");



                main.dataBase.message.SendMessage(
                        textFieldMessageSubject.getText(),  //Тема сообщения
                        Integer.toString(main.getIdEmployee()),  //Преобразование в строковый ID (отправитель)
                        main.dataBase.employees.LoginToId(textFieldRecipient.getText()), //Преобразование логина в строковый ID (получатель)
                        textAreaMessage.getText(), //Текстовое сообщение
                        "Заглушка (контрольная сумма)" //Контрольная сумма сообщения
                );

                messageWindow.CloseMessageWindow();
                com.SecurityDataBaseSystems.main.Message.Message message = new com.SecurityDataBaseSystems.main.Message.Message("Сообщение отправлено", com.SecurityDataBaseSystems.main.Message.Message.GOOD);
                message.ShowMessage();
            }

        });

        buttonSaveToDocuments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Сохранить в документах");

            }

        });

        buttoClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Очистить");
                textFieldMessageSubject.clear();
                textFieldRecipient.clear();
                textAreaMessage.clear();
            }

        });


        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Удалить");
                main.dataBase.message.DeleteMessage(idMessage);
                messageWindow.CloseMessageWindow();
                com.SecurityDataBaseSystems.main.Message.Message message =
                        new com.SecurityDataBaseSystems.main.Message.Message("Сообщение удалено", com.SecurityDataBaseSystems.main.Message.Message.GOOD);
                message.ShowMessage();
            }

        });

    }

    public void setMessageWindow(MessageWindow messageWindow) {
        this.messageWindow = messageWindow;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void UnpackMessage(Info info)
    {
         textFieldMessageSubject.setText(info.getMessageSubject());
         textFieldRecipient.setText(main.dataBase.employees.IdToLogin(info.getSender()));
         textAreaMessage.setText(main.dataBase.message.getMessage(info.getIdMessage()));
         idMessage = new String(info.getIdMessage());
    }
}

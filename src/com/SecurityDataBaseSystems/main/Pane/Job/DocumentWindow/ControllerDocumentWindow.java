package com.SecurityDataBaseSystems.main.Pane.Job.DocumentWindow;

import com.SecurityDataBaseSystems.Database.Documentation.Document;
import com.SecurityDataBaseSystems.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerDocumentWindow {

    private DocumentWindow documentWindow;

    private Main main;

    @FXML
    private Text textInformation;

    @FXML
    private TextArea textAreaDocument;

    @FXML
    private TextField textFieldDocumentHeader;

    @FXML
    private Button buttonSendMessage;

    @FXML
    private Button buttonSaveDocument;

    @FXML
    private Button buttoClear;

    @FXML
    private Button buttonDelete;

    @FXML
    private Text textIDdocument;



    public void Init ()
    {

        buttonSendMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Отправить сообщением");

            }

        });

        buttonSaveDocument.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Сохранить документ");

            }

        });

        buttoClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Очистить");

            }

        });

        //Закрыть окно
        textInformation.setOnMouseClicked(event -> {
            System.out.println("Нажата кнопкка Информация");
        });

        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Удалить");

            }

        });

    }

    public void setMessageWindow(DocumentWindow documentWindow) {
        this.documentWindow = documentWindow;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void UnpackDocument(Document document)
    {
        textIDdocument.setText(document.getIdDocument());
        textFieldDocumentHeader.setText(document.getHeader());
        textAreaDocument.setText(main.dataBase.documentation.getDocument(document.getIdDocument()));
    }
}

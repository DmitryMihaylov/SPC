package com.SecurityDataBaseSystems.main.Message;


import com.SecurityDataBaseSystems.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControllerMessage {

    private Main main;

    private Message message;

    @FXML
    private ImageView imageViewDanger;

    @FXML
    private ImageView imegeViewGood;

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea textAreaMessage;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttoCancel;

    @FXML
    private ImageView imageViewQestion;

    @FXML
    private ImageView imageViewWarning;

    @FXML
    private ImageView imageViewInfo;

    public void SetMain (Main main)
    {
        this.main=main;
    }

    public void SetMessage(String message)
    {
        textAreaMessage.setText(message);
    }

    public void setMessageParent(Message message) {
        this.message = message;
    }


    public void setCode (int code)
    {
        switch (code)
        {
            case Message.GOOD: {
                System.out.println("GOOD");

                imageView.setImage(imegeViewGood.getImage());

            } break;

            case Message.DANGER: {
                System.out.println("DANGER");

                imageView.setImage(imageViewDanger.getImage());
            } break;

            case Message.QESTION: {
                System.out.println("QESTION");

                imageView.setImage(imageViewQestion.getImage());
            } break;

            case Message.WARNING: {
                System.out.println("WARNING");

                imageView.setImage(imageViewWarning.getImage());

            } break;

            case Message.INFO: {
                System.out.println("INFO");

                imageView.setImage(imageViewInfo.getImage());

            } break;

            default: {

            }
        }
    }

    public  void Init()
    {
        textAreaMessage.setWrapText(true);
        textAreaMessage.setEditable(false);

        buttoCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Отмена");
            }
            });

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка ОК");
                message.CloseMessage();
            }
        });


    }


}

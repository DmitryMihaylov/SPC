package com.SecurityDataBaseSystems.main.Pane.PrivateOffice;


import com.SecurityDataBaseSystems.Crypto.RSA;
import com.SecurityDataBaseSystems.Database.Message.Info;
import com.SecurityDataBaseSystems.main.Log.Log;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Message.Message;
import com.SecurityDataBaseSystems.main.Pane.PrivateOffice.MessageWindow.MessageWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


public class ControllerPanePrivateOffice {


    private Main main;

    @FXML
    private Pane pane;

    @FXML
    private Button buttonSetupKey;

    @FXML
    private Text labelFIO;

    @FXML
    private Text labelPosition;

    @FXML
    private Text labelAllowance;

    @FXML
    private Text labelID;

    @FXML
    private Tab tabMessage;

    @FXML
    private Button buttonGenerateNewKeyPair;

    @FXML
    private TextArea textAreaPublicKey;

    @FXML
    private TextArea textAreaPrivateKey;

    @FXML
    private Button buttonSaveToDocuments;

    @FXML
    private TreeView  <Info> treeView = new TreeView<Info>();

    TreeItem<Info> root;

    public void Init ()
    {

        labelFIO.setText("ФИО: "+main.dataBase.employees.getSurname(main.getIdEmployee())+
        " "+main.dataBase.employees.getName(main.getIdEmployee())+
        " "+main.dataBase.employees.getPatronymic(main.getIdEmployee())
        );
        labelPosition.setText("Должность: "+main.dataBase.employees.getPosition(main.getIdEmployee()));


        labelAllowance.setText("Допуск: "+main.dataBase.access.getAllowance(Integer.toString(main.getIdEmployee())));



        buttonGenerateNewKeyPair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка "+buttonGenerateNewKeyPair.getText());
                RSA rsa = new RSA();

                rsa.GenerateKeyPairRSA();
                new Log("Кнопка "+ buttonGenerateNewKeyPair.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции




                textAreaPublicKey.setText(Base64.getEncoder().encodeToString(rsa.PublicKey.getEncoded()));

                textAreaPrivateKey.setText(Base64.getEncoder().encodeToString(rsa.PrivateKey.getEncoded()));
            }

        });

        buttonSetupKey.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка "+buttonSetupKey.getText());
                new Log("Кнопка "+  buttonSetupKey.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции

                if (textAreaPublicKey.getText().equals("")){
                    new com.SecurityDataBaseSystems.main.Message.Message("Поле данных с открытым ключем пустое", com.SecurityDataBaseSystems.main.Message.Message.DANGER).ShowMessage();

                }
                else {
                    main.dataBase.rsaKeys.setRSA(textAreaPublicKey.getText(), main.getIdEmployee());
                    new com.SecurityDataBaseSystems.main.Message.Message("Открытый ключ успешно установлен", com.SecurityDataBaseSystems.main.Message.Message.GOOD).ShowMessage();
                    new Log("Открытый ключ успешно установлен", Log.NORMAL_EVENT, "", main);

                }
           }

        });

        buttonSaveToDocuments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата Сохранить в документах");

                 String document = new String(
                        "ФИО: "+main.dataBase.employees.getSurname(main.getIdEmployee())+
                                " "+main.dataBase.employees.getName(main.getIdEmployee())+
                                " "+main.dataBase.employees.getPatronymic(main.getIdEmployee())+
                                "\nДолжность: "+main.dataBase.employees.getPosition(main.getIdEmployee())+
                        "\n\nГенерация RSA ключей : \n\n\n"+
                                "\nОткрытый ключ:  \n\n"+textAreaPublicKey.getText()+
                                "\n\nЗакрытый ключ: \n\n"+textAreaPrivateKey.getText()
                );

                Date date = new Date();

                String Header = "Генерация RSA ключей "+date.toString();
                main.dataBase.documentation.SetDocument(Header, Integer.toString(main.getIdEmployee()),document);

                new Log("Нажата кнопка "+buttonSetupKey.getText(), Log.NORMAL_EVENT, Header, main);
                new com.SecurityDataBaseSystems.main.Message.Message("Сохранено в документах", Message.GOOD).ShowMessage();

            }

        });

        tabMessage.setOnSelectionChanged(event -> {
            if (tabMessage.isSelected())
            {
                ArrayList<Info> infos = main.dataBase.message.getMessages(main.getIdEmployee()); //Полученный список сообщений
                Info.setMain(main); //Поместить статический адрес

                root = new TreeItem("Мои сообщения");
                root.setExpanded(true);
                TreeItem <Info> temp; //Временный адрес на item

                for (int i = 0; i< infos.size(); i++)
                {
                    temp = new TreeItem<Info>(infos.get(i)); //Создание item
                    root.getChildren().add(temp); //Добавление item на дерево
                }
                treeView.setCursor(Cursor.CLOSED_HAND); //Указатель мышки в виде руки
                treeView.setRoot(root); //Поместить родительский item и все дочерние на treeView


            }

        });

        //Реакция сообщение в списке сообщений
        treeView.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue ) -> {


                    if (newValue!=root) {
                        try {
                            MessageWindow messageWindow = new MessageWindow(newValue.getValue(), main);
                            messageWindow.ShowMessageWindow(/*newValue.getValue().getIdMessage()*/);
                        }
                        catch (NullPointerException e)
                        {
                            //Зашлушка
                        }

                    }

                    else
                       newValue.setExpanded(!newValue.isExpanded());



                }
        );


    }

    public void setMain(Main main)
    {
        this.main = main;
    }

}

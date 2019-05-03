package com.SecurityDataBaseSystems.main.Pane.Employees;


import com.SecurityDataBaseSystems.Database.RSAkeys.RSApublicKeyEmoloye;
import com.SecurityDataBaseSystems.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ControllerPaneEmployees {


   private Main main;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RSApublicKeyEmoloye> tableWiew;

    @FXML
    private TableColumn<RSApublicKeyEmoloye, String> tableCoulummFIO;

    @FXML
    private TableColumn<RSApublicKeyEmoloye, String> tableCoulummPublicKey;

    //Список ключей RSA
    private ObservableList<RSApublicKeyEmoloye> rsaPublicKeyEmoloye = FXCollections.observableArrayList();

    public void Init ()
    {
        ArrayList<RSApublicKeyEmoloye> arrayList = main.dataBase.rsaKeys.getRSAkeysOfEmployees();


        for (int i=0; i<arrayList.size(); i++)
                 rsaPublicKeyEmoloye.add(arrayList.get(i));

        tableCoulummFIO.setCellValueFactory(new PropertyValueFactory<RSApublicKeyEmoloye, String>("Employe"));

        tableCoulummPublicKey.setCellValueFactory(new PropertyValueFactory<RSApublicKeyEmoloye, String>("RSAkey"));

        //Обработка нажатий на строку таблицы
        tableWiew.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(newValue.getEmploye());
                });

        tableWiew.setItems(rsaPublicKeyEmoloye);


    }

    public void setMain(Main main)
    {
        this.main = main;
    }

}

package com.SecurityDataBaseSystems.main.Pane.SpecialOperation;


import com.SecurityDataBaseSystems.Database.SpecialOperations.Operation;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Pane.SpecialOperation.SpecialOperationWinow.SpecialOperationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ControllerPaneSpecialOperation {

    private  Main main;

    @FXML
    private Pane pane;

    @FXML
    private TableView<Operation> tableView;

    @FXML
    private TableColumn<Operation, String> CoulummName;

    @FXML
    private TableColumn<Operation, String> CoulumAllowance;


    //Список доступных операций
    private ObservableList<Operation> operationObservableList = FXCollections.observableArrayList();

    public void Init ()
    {
        ArrayList<Operation> arrayList = main.dataBase.specialOperations.getOperations(main);


        for (int i=0; i<arrayList.size(); i++)
            operationObservableList.add(arrayList.get(i));

        CoulummName.setCellValueFactory(new PropertyValueFactory<Operation, String>("SpecialOperationName"));
        CoulumAllowance.setCellValueFactory(new PropertyValueFactory<Operation, String>("Allowance"));

        //Обработка нажатий на строку таблицы
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                   // System.out.println(newValue.toString());

                    SpecialOperationWindow specialOperationWindow = new SpecialOperationWindow(newValue, main);
                    specialOperationWindow.ShowWindow();

                });

        tableView.setItems(operationObservableList);

    }

    public void setMain(Main main)
    {
        this.main = main;
    }

}

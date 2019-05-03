package com.SecurityDataBaseSystems.main.Pane.Job;


import com.SecurityDataBaseSystems.Database.Documentation.Document;
import com.SecurityDataBaseSystems.Database.Message.Info;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Pane.Job.DocumentWindow.DocumentWindow;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class ControllerPaneJob {


   private Main main;


    @FXML
    private TreeView  <Document> treeViewDocuments = new TreeView<Document>();

    @FXML
    private Tab tabDocument;

    public void Init ()
    {
        //Реакция сообщение в списке документов
        treeViewDocuments.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue ) -> {

                    DocumentWindow documentWindow = new DocumentWindow(newValue.getValue(), main);
                    documentWindow.ShowDocumentWindow();
                }
        );

        tabDocument.setOnSelectionChanged(event -> {
            if (tabDocument.isSelected())
            {
                ArrayList<Document> documents = main.dataBase.documentation.getDociments(main.getIdEmployee()); //Полученный список сообщений

                Info.setMain(main); //Поместить статический адрес


                TreeItem<Document> root = new TreeItem("Мои документы");
                root.setExpanded(true);

                TreeItem <Document> temp; //Временный адрес на item

                for (int i=0; i<documents.size(); i++)
                {
                    temp = new TreeItem<Document>(documents.get(i)); //Создание item
                    root.getChildren().add(temp); //Добавление item на дерево
                }
                treeViewDocuments.setCursor(Cursor.CLOSED_HAND); //Указатель мышки в виде руки
                treeViewDocuments.setRoot(root); //Поместить родительский item и все дочерние на treeView
            }

        });

    }

    public void setMain(Main main)
    {
        this.main = main;
    }


}

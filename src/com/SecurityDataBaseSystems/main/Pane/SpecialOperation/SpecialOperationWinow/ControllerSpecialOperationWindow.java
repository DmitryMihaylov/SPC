package com.SecurityDataBaseSystems.main.Pane.SpecialOperation.SpecialOperationWinow;


import com.SecurityDataBaseSystems.Database.Documentation.Document;
import com.SecurityDataBaseSystems.Database.Employees.Employee;
import com.SecurityDataBaseSystems.Database.SpecialOperations.Operation;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Pane.Job.DocumentWindow.DocumentWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ControllerSpecialOperationWindow {

    @FXML
    private Pane pane;

    @FXML
    private Text textOperation;

    @FXML
    private Text Allowance;


    @FXML
    private TextArea textAreaDescription;


    @FXML
    private TableView<Employee> tableViewFIO;   //Таблица ФИО Сотрудников

    @FXML
    private TableColumn<Employee, String> tableCoulummFIO; //Столбец ФИО


    //Обрабатываемый список сотрудников
    private ObservableList<Employee> EmoloyesObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Document> tableViewMaterials; //Таблица материалов специальной операции

    @FXML
    private TableColumn<Document, String> tableCoulummMaterials; //Столбец материалов специальной операции

    //Обрабатываемый список материалов
    private ObservableList<Document> DocumentsObservableList = FXCollections.observableArrayList();

    private Main main;

    private Operation operation;


    public void Init ()
    {

        textOperation.setText(operation.getSpecialOperationName()); //Название операции
        Allowance.setText("Категория секретности: "+operation.getAllowance()); //Категория секретности
        textAreaDescription.setText(operation.getDescription()); //Описание

/*ТЕСТ*/ operation.TestJSON();   //Тест JSON

        //Временный ArrayList для сотрудников
        ArrayList<Employee> arrayList = new ArrayList<Employee>();

        Employee employee;
        for (int i=0; i<operation.Employes.size(); i++) {
             employee = new Employee();                      //Создаем экземпляр сотрудника
             employee.setId(operation.Employes.get(i)); //Присваиваем ему идентификатор
             employee.setSurname(main.dataBase.employees.getSurname(Integer.parseInt(employee.getId()))); //Фамилия
             employee.setName(main.dataBase.employees.getName(Integer.parseInt(employee.getId())));       //Имя
             employee.setPatronymic(main.dataBase.employees.getPatronymic(Integer.parseInt(employee.getId()))); //Отчество
             arrayList.add(employee);                        //Добавляем в ArrayList
        }

        //Переписывание из временного ArrayList в основной ObservableList
        for (int i=0; i<arrayList.size(); i++)
            EmoloyesObservableList.add(arrayList.get(i));


        //ФИО
        tableCoulummFIO.setCellValueFactory(new PropertyValueFactory<Employee, String>("SurnameNamePatronymic"));

        //Обработка нажатий на строку таблицы
        tableViewFIO.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(newValue.getSurnameNamePatronymic());
                });

        tableViewFIO.setItems(EmoloyesObservableList);


        //Временный ArrayList для материалов специальной операции
        ArrayList<Document> arrayDocuments = new ArrayList<Document>();

        Document document;
        for (int i=0; i<operation.Materials.size(); i++)
        {
            document = new Document();  //Создаем экземпляр документа
            document.setIdDocument(operation.Materials.get(i)); //присваиваем идентификатор документу

            //Присваиваем заголовок к документу
            document.setHeader(main.dataBase.documentation.getHeaderDocument(document.getIdDocument()));

            //Присваивание контрольной суммы документа
            document.setCheckSum(main.dataBase.documentation.getCheckSumDocument(document.getIdDocument()));


            arrayDocuments.add(document); //Добавляем документ к временному ArrayList для материалов специальной операции
        }

        //Переписывание из временного ArrayList в основной ObservableList
        for (int i=0; i<arrayDocuments.size(); i++)
            DocumentsObservableList.add(arrayDocuments.get(i));


        //Материалы специальной операции
        tableCoulummMaterials.setCellValueFactory(new PropertyValueFactory<Document, String>("IdDocumentHeader"));

        //Обработка нажатий на строку таблицы
        tableViewMaterials.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(newValue.getIdDocumentHeader());

                    //Окно работы с документом
                    DocumentWindow documentWindow = new DocumentWindow(newValue, main);
                    documentWindow.ShowDocumentWindow();

                });

        tableViewMaterials.setItems(DocumentsObservableList);
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public void setOperation(Operation operation)
    {
        this.operation = operation;
    }
}

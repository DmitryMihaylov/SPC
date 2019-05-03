package com.SecurityDataBaseSystems.main.Pane.Admin;


import com.SecurityDataBaseSystems.Database.AccessMatrixEmployee.EmployeMatrix;
import com.SecurityDataBaseSystems.Database.Employees.Employee;

import com.SecurityDataBaseSystems.Database.SpecialOperations.Operation;
import com.SecurityDataBaseSystems.main.GeneratePassword.GeneratePassword;
import com.SecurityDataBaseSystems.main.Log.Log;
import com.SecurityDataBaseSystems.main.Main;
import com.SecurityDataBaseSystems.main.Message.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;



public class ControllerPaneAdmin {

    @FXML
    private Pane pane;

    //ВКЛАДКА СОТРУДНИКИ
    @FXML
    private Label labelClearEmployee;
    @FXML
    private TextField textFieldSearchEmployee;
    @FXML
    private Button buttonSearchEmployee;
    @FXML
    private TextField textFieldTelephone;

    @FXML
    private Button buttonChangeEmployee;
    @FXML
    private Button buttonDelEmployee;
    @FXML
    private CheckBox checkBoxAdmin;
    @FXML
    private Button buttonGeneratePassword;
    @FXML
    private Button buttonGeneratePassword2;
    @FXML
    private Button buttonClearEmployee;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private TextField textFieldPosition;
    @FXML
    private TextField textFieldSurname;
    @FXML
    public TextField textFieldName;
    @FXML
    private TextField textFieldPatronymic;
    @FXML
    private Button buttonAddEmployee;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private CheckBox checkBoxJob;
    @FXML
    private CheckBox checkBoxKeys;
    @FXML
    private CheckBox checkBoxMessages;
    @FXML
    private CheckBox checkBoxDocuments;
    @FXML
    private CheckBox checkBoxEmployees;
    @FXML
    private CheckBox checkBoxControl;
    @FXML
    private CheckBox checkBoxLock;
    @FXML
    private CheckBox checkBoxSettings;
    @FXML
    private CheckBox checkBoxSpecialOperations;
    //ВКЛАДКА СПЕЦИАЛЬНЫЕ ОПЕРАЦИИ
    @FXML
    private Label labelClearSO;
    @FXML
    private Button buttonDelSO;
    @FXML
    private ListView<?> participantList;
    @FXML
    private Button buttonAddSO;
    @FXML
    private TextField textFieldSearchSO;
    @FXML
    private ChoiceBox choiceOfSecrecySO;
    @FXML
    private Button buttonSearchSO;
    @FXML
    private Button buttonClearSO;
    @FXML
    private Button buttonChangeSO;
    @FXML
    private TextField textFieldOperationName;
    @FXML
    private TextArea textFieldDescription;

    //ВКЛАДКА УПРАВЛЕНИЕ И АНАЛИЗ

    @FXML
    private Text textSystemStatus;
    @FXML
    private Button buttonSaveAnalysisManagement;
    @FXML
    private Button buttonSendMassMessage;
    @FXML
    private Button buttonLockSystem;
    @FXML
    private TextArea textDataAnalysis;
    @FXML
    private Button buttonDataAnalysis;
    @FXML
    private PasswordField PasswordFieldLockSystem;
    @FXML
    private TextArea textFieldMassMessage;

    //ВКЛАДКА АУДИТ

    @FXML
    private TableView<?> tableLog;
    @FXML
    private TableColumn<?, ?> columnEmployee;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnEvent;
    @FXML
    private DatePicker firstDate;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonCheckSum;
    @FXML
    private Button buttonSaveAnalysisAudit;
    @FXML
    private DatePicker secondDate;

    //БЛОК ПЕРЕМЕННЫХ

    private  Main main;
    private ControllerPaneAdmin controllerPaneAdmin=this;

    public void Init () {
        //ПАНЕЛЬ СОТРУДНКИ
        //Инициализация выбора Допуска
        choiceBox.setValue("Конфиденциально");
        choiceBox.getItems().add("Конфиденциально");
        choiceBox.getItems().add("Секретно");
        choiceBox.getItems().add("Совершенно-секретно");
        choiceBox.getItems().add("Особая важность");
        //Кнопка Сгенерировать пароль
        buttonGeneratePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Сгенерировать пароль");

                new Log("Кнопка "+ buttonGeneratePassword.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции

                GeneratePassword generatePassword = new GeneratePassword();
                generatePassword.Show();

            }

        });

        //Кнопка изменения сотрудника
        buttonChangeEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Изменения");

                new Log("Кнопка "+ buttonChangeEmployee.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции


            ///Замечание: Можно вынести блок работы с сотрудником в отдельный метод
                Employee employee = new Employee();


                employee.setName(textFieldName.getText());
                employee.setSurname(textFieldSurname.getText());
                employee.setPatronymic(textFieldPatronymic.getText());
                employee.setPosition(textFieldPosition.getText());
                employee.setTelephone(textFieldTelephone.getText());
                employee.setLogin(textFieldSearchEmployee.getText());

                //Заполнение матрицы доступа для сотрудника в соответствии с выбранными чекбоксами:
                employee.getEmployeMatrix().SetElement(EmployeMatrix.PrivateOffice_Messages, checkBoxMessages.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonSettings, checkBoxSettings.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonSpecialOperation, checkBoxSpecialOperations.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.PrivateOffice_Keys, checkBoxKeys.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonJob, checkBoxJob.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonEmployees, checkBoxEmployees.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonControlSPC, checkBoxControl.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.Job_Documents, checkBoxDocuments.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonAdministration, checkBoxAdmin.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.BlockEmploye, checkBoxLock.isSelected());


                FieldChecker check = new FieldChecker();
                boolean chk = false; //Переменная для контроля
                check.setMain(main);
                //Проверка сотрудника
                chk = (
                                check.checkRussianField(employee.getName())             &&
                                check.checkRussianField(employee.getSurname())          &&
                                check.checkRussianField(employee.getPatronymic())       &&
                                check.checkRussianField(employee.getPosition())         &&
                                check.checkTelephoneField(employee.getTelephone())

                );
                if (chk) //Если все проверки успешно пройдены, то добавить сотрудника в базу данных
                {
                    new Log("Сотрудник успешно прошел проверку", Log.NORMAL_EVENT, ""+employee.toString(), main); //Логирование операции
                    main.dataBase.employees.Change(employee);


                    //Изменение записи в таблице "матрица доступа" (AccessMatrixEmployee)
                    main.dataBase.accessMatrixEmployee.SetEmployeMatrix(employee.getEmployeMatrix(),  main.dataBase.employees.LoginToId(employee.getLogin()));



                    String tempAllowance= null;

                    if (choiceBox.getValue()=="Конфиденциально")        tempAllowance = new String("4");
                    if (choiceBox.getValue()=="Совершенно-секретно")    tempAllowance = new String("3");
                    if (choiceBox.getValue()=="Секретно")               tempAllowance = new String("2");
                    if (choiceBox.getValue()=="Особая важность")        tempAllowance = new String("1");

                    //Изменение записи в таблице "Допуск" (Access)
                    main.dataBase.access.setIDAllowance(main.dataBase.employees.LoginToId(employee.getLogin()), tempAllowance);



                    //#Дмитрий Михайлов (Каково назначение следующей строки?)
                    main.dataBase.employees.LoginToId(employee.getLogin());
                    System.out.println(checkBoxAdmin.isSelected());
                    new Log("Сотрудник изменен", Log.SERIOUS_EVENT, ""+employee.toString(), main); //Логирование операции
                    Message message = new Message("Сотрудник "+employee.getName()+" "+employee.getSurname()+ " успешно изменен", Message.GOOD);
                    message.ShowMessage();
                }


            }

        });
        //По нажатию на Enter выполняется поиск
        textFieldSearchEmployee.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    System.out.println("Нажата кнопкка поиска сотрудника");
                    new Log("Кнопка "+  buttonSearchEmployee.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                    Employee employee = new Employee();
                    FieldChecker checker = new FieldChecker();
                    checker.setMain(main);
                    employee.setLogin(textFieldSearchEmployee.getText());
                    if (checker.checkLoginField(employee.getLogin())){
                        Message message = new Message("Сотрудника с логином "+employee.getLogin()+ " нет в базе данных", Message.GOOD);
                        message.ShowMessage();
                    }
                    else
                    {
                        if (checker.isEmpty(employee.getLogin())){
                            Message message = new Message("Поле поиска пустое", Message.GOOD);
                            message.ShowMessage();
                        }
                        else {
                            main.dataBase.employees.Search(employee);
                            textFieldName.setText(employee.getName());
                            textFieldSurname.setText(employee.getSurname());
                            textFieldPatronymic.setText(employee.getPatronymic());
                            textFieldTelephone.setText(employee.getTelephone());
                            textFieldPosition.setText(employee.getPosition());
                            textFieldLogin.setText(employee.getLogin());

                        }
                    }
                }
            }
        });
        //Кнопка поиска сотрудника
        buttonSearchEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка поиска сотрудника");
                new Log("Кнопка "+  buttonSearchEmployee.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                Employee employee = new Employee();
                FieldChecker checker = new FieldChecker();
                checker.setMain(main);
                employee.setLogin(textFieldSearchEmployee.getText());
                if (checker.checkLoginField(employee.getLogin())){
                    Message message = new Message("Сотрудника с логином "+employee.getLogin()+ " нет в базе данных", Message.GOOD);
                    message.ShowMessage();
                }
                else
                {
                    if (checker.isEmpty(employee.getLogin())){
                        Message message = new Message("Поле поиска пустое", Message.GOOD);
                        message.ShowMessage();
                    }
                    else {
                        main.dataBase.employees.Search(employee);
                        textFieldName.setText(employee.getName());
                        textFieldSurname.setText(employee.getSurname());
                        textFieldPatronymic.setText(employee.getPatronymic());
                        textFieldTelephone.setText(employee.getTelephone());
                        textFieldPosition.setText(employee.getPosition());
                        textFieldLogin.setText(employee.getLogin());

                    }
                }
            }

        });
        //Кнопка добавить (Добавить сотрудника в базу данных)
        buttonAddEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Добавить");
                new Log("Кнопка "+ buttonAddEmployee.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции

                //Сотрудник
                Employee employee = new Employee();

                employee.setName(textFieldName.getText());
                employee.setSurname(textFieldSurname.getText());
                employee.setPatronymic(textFieldPatronymic.getText());
                employee.setPosition(textFieldPosition.getText());
                employee.setTelephone(textFieldTelephone.getText());
                employee.setLogin(textFieldLogin.getText());
                employee.setPassword(textFieldPassword.getText());


                //Заполнение матрицы доступа для сотрудника в соответствии с выбранными чекбоксами:
                employee.getEmployeMatrix().SetElement(EmployeMatrix.PrivateOffice_Messages, checkBoxMessages.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonSettings, checkBoxSettings.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonSpecialOperation, checkBoxSpecialOperations.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.PrivateOffice_Keys, checkBoxKeys.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonJob, checkBoxJob.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonEmployees, checkBoxEmployees.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonControlSPC, checkBoxControl.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.Job_Documents, checkBoxDocuments.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.ButtonAdministration, checkBoxAdmin.isSelected());
                employee.getEmployeMatrix().SetElement(EmployeMatrix.BlockEmploye, checkBoxLock.isSelected());

                //Тест матрицы доступа:
                //System.out.println(employee.getEmployeMatrix().toString());


                //Чекер
                FieldChecker check = new FieldChecker();
                boolean chk = false; //Переменная для контроля
                check.setMain(main);
                //Проверка сотрудника
                chk = (
                        check.checkRussianField(employee.getName())                     &&
                                check.checkRussianField(employee.getSurname())          &&
                                check.checkRussianField(employee.getPatronymic())       &&
                                check.checkRussianField(employee.getPosition())         &&
                                check.checkTelephoneField(employee.getTelephone())      &&
                                check.checkLoginField(employee.getLogin())              &&
                                check.checkPasswordField(employee.getPassword())
                );

                if (chk) //Если все проверки успешно пройдены, то добавить сотрудника в базу данных
                {
                    new Log("Сотрудник успешно прошел проверку", Log.NORMAL_EVENT, ""+employee.toString(), main); //Логирование операции

                    //Добавление записи в таблицу "сотрудники" (Employees)
                    main.dataBase.employees.Add(employee);

                    //Добавление записи в таблицу "матрица доступа" (AccessMatrixEmployee)
                    main.dataBase.accessMatrixEmployee.SetEmployeMatrix(employee.getEmployeMatrix(),  main.dataBase.employees.LoginToId(employee.getLogin()));

                    //Добавление записи в табоицу "Допуск" (Access)

                    String tempAllowance= null;

                        if (choiceBox.getValue()=="Конфиденциально")        tempAllowance = new String("4");
                        if (choiceBox.getValue()=="Совершенно-секретно")    tempAllowance = new String("3");
                        if (choiceBox.getValue()=="Секретно")               tempAllowance = new String("2");
                        if (choiceBox.getValue()=="Особая важность")        tempAllowance = new String("1");

                    main.dataBase.access.setIDAllowance(main.dataBase.employees.LoginToId(employee.getLogin()), tempAllowance);

                    //#Дмитрий Михайлов (Для чего нужна эта строка, которая никуда не возвращает результат и не заносит данные в БД?)
                    main.dataBase.employees.LoginToId(employee.getLogin());

                    System.out.println(checkBoxAdmin.isSelected());
                    new Log("Сотрудник добавлен в базу данных", Log.SERIOUS_EVENT, ""+employee.toString(), main); //Логирование операции
                    Message message = new Message("Сотрудник "+employee.getName()+" "+employee.getSurname()+ " успешно добавлен", Message.GOOD);
                    message.ShowMessage();
                }
                else {
                    //#Дмитрий Михайлов
                    //!!! Обработать ситуацию, если сотрудник НЕ прошел проверку в базе данных
                    //Вывести соответсвующие сообщения  Message со стеком несоответсвий проверок класса FieldChecker через метод GetLogChecker

                }


            }

        });
        //Кнопка Очистить
        buttonClearEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Очистить");
                new Log("Кнопка "+  buttonClearEmployee.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции

                textFieldPassword.clear();
                textFieldName.clear();
                textFieldLogin.clear();
                textFieldSurname.clear();
                textFieldTelephone.clear();
                textFieldPosition.clear();
                textFieldPatronymic.clear();
                choiceBox.setValue("Конфиденциально");
                //Убираем галочки с чекбоксов
                checkBoxMessages.setSelected(false);
                checkBoxKeys.setSelected(false);
                checkBoxJob.setSelected(false);
                checkBoxControl.setSelected(false);
                checkBoxSettings.setSelected(false);
                checkBoxSpecialOperations.setSelected(false);
                checkBoxEmployees.setSelected(false);
                checkBoxDocuments.setSelected(false);
                checkBoxLock.setSelected(false);
                checkBoxAdmin.setSelected(false);
            }

        });
        //Удаляет сотрудника с логином в поиске
        buttonDelEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Удалить Сотрудника");
                new Log("Кнопка "+  buttonDelEmployee.getText(), Log.CRITICAL_EVENT, "", main); //Логирование операции
                FieldChecker check = new FieldChecker();
                boolean chk = false; //Переменная для контроля
                check.setMain(main);
                //Проверка сотрудника
                chk = (
                        !check.checkLoginField(textFieldLogin.getText())
                );
                Employee employee = new Employee();
                employee.setLogin(textFieldLogin.getText());
                if (chk) //Если все проверки успешно пройдены, то добавить сотрудника в базу данных
                {
                    new Log("Сотрудник успешно прошел проверку", Log.NORMAL_EVENT, ""+employee.toString(), main); //Логирование операции
                    main.dataBase.employees.Delete(employee);
                    new Log("Сотрудник удален из базы данных", Log.SERIOUS_EVENT, ""+employee.toString(), main); //Логирование операции
                    Message message = new Message("Сотрудник c логином" +employee.getLogin()+ " успешно удален", Message.GOOD);
                    message.ShowMessage();
                }

            }

        });

        //Очистка поля поиска
        labelClearEmployee.setOnMouseClicked(event -> {
            textFieldSearchEmployee.clear();
            new Log("Очищено поле поиска сотрудников", Log.NORMAL_EVENT, "", main); //Логирование операции
        });

        //ПАНЕЛЬ СПЕЦИАЛЬНЫЕ ОПЕРАЦИИ
        //Очистка поля поиска
        labelClearSO.setOnMouseClicked(event -> {
            textFieldSearchSO.clear();
            new Log("Очищено поле поиска cпециальных операций", Log.NORMAL_EVENT, "", main); //Логирование операции
        });

        //Кнопка Очистить
        buttonClearSO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Очистить");
                new Log("Кнопка "+  buttonClearSO.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                textFieldOperationName.clear();
                textFieldDescription.clear();
            }

        });

        //ПАНЕЛЬ СПЕЦИАЛЬНЫЕ ОПЕРАЦИИ
        //Инициализация Селектора
        choiceOfSecrecySO.setValue("Конфиденциально");
        choiceOfSecrecySO.getItems().add("Конфиденциально");
        choiceOfSecrecySO.getItems().add("Секретно");
        choiceOfSecrecySO.getItems().add("Совершенно-секретно");
        choiceOfSecrecySO.getItems().add("Особая важность");
        //Очистка поля поиска
        labelClearSO.setOnMouseClicked(event -> {
            textFieldSearchSO.clear();
            new Log("Очищено поле поиска cпециальных операций", Log.NORMAL_EVENT, "", main); //Логирование операции
        });
        //Кнопка Очистить
        buttonClearSO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Очистить");
                new Log("Кнопка "+  buttonClearSO.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                textFieldOperationName.clear();
                textFieldDescription.clear();
                textFieldSearchSO.clear();

            }

        });
        //Кнопка Сгенерировать пароль на вкладке "Специальные операции"
        buttonGeneratePassword2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Log("Кнопка "+ buttonGeneratePassword.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                GeneratePassword generatePassword = new GeneratePassword();
                generatePassword.Show();

            }

        });
        //Кнопка удалить специальную операцию
        buttonDelSO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Удалить");
                new Log("Кнопка "+  buttonDelSO.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                Operation operation = new Operation();
                operation.setSpecialOperationName(textFieldSearchSO.getText());
                new Log("Операция "+ operation.getSpecialOperationName()+" была удалена", Log.NORMAL_EVENT, "", main); //Логирование операции
                main.dataBase.specialOperations.Delete(operation);
            }
        });
        //Кнопка Добавить пециальную операцию
        buttonAddSO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Нажата кнопкка Добавить Специальную Операцию");
                new Log("Кнопка "+ buttonAddSO.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции

                Operation operation = new Operation();
                operation.setSpecialOperationName(textFieldOperationName.getText());
                operation.setDescription(textFieldDescription.getText());
                //Чекер
                FieldChecker check = new FieldChecker();
                //                  System.out.println(choiceOfSecrecySO.getItems());
                boolean chk = false; //Переменная для контроля
                check.setMain(main);
                //Проверка полей
                chk = (
                        check.checkRussianField(operation.getSpecialOperationName())        &&
                                check.checkRussianField(operation.getDescription())
                );



                if (chk) //Если все проверки успешно пройдены, то добавить сотрудника в базу данных
                {
                    new Log("Операция прошла проверку", Log.NORMAL_EVENT, ""+ operation.toString(), main); //Логирование операции
                    main.dataBase.specialOperations.Add(operation);
                    new Log("Операция добавлена в базу данных", Log.SERIOUS_EVENT, ""+ operation.toString(), main); //Логирование операции
                    Message message = new Message("Операция успешно добавлена", Message.GOOD);
                    message.ShowMessage();
                }


            }

        });
        //Кнопка поиска специальной операции
        buttonSearchSO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Очистить");
                new Log("Кнопка "+  buttonSearchSO.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                Operation operation = new Operation();
                operation.setSpecialOperationName(textFieldSearchSO.getText());
                main.dataBase.specialOperations.Search(operation);
                Message message = new Message("Данные по операции с названием  "+ operation.getSpecialOperationName()+ "\n"+main.dataBase.specialOperations.Search(operation), Message.GOOD);
                message.ShowMessage();
                new Log("Был поиск по операции "+ operation.getSpecialOperationName(), Log.NORMAL_EVENT, ""+ operation.toString(), main); //Логирование операции

            }
        });
        //ПАНЕЛЬ АНАЛИЗ И УПРАВЛЕНИЕ

        //ПАНЕЛЬ АУДИТ
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопкка Старт");
                String formatedFirstDate = new String(String.valueOf(firstDate.getValue()));
                System.out.println(formatedFirstDate);
                String formatedSecondDate = new String(String.valueOf(secondDate.getValue()));
                System.out.println(formatedSecondDate);
            }

        });
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

}
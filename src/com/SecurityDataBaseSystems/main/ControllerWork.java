package com.SecurityDataBaseSystems.main;

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.main.Block.ControllerLockScreen;
import com.SecurityDataBaseSystems.main.Log.Log;
import com.SecurityDataBaseSystems.main.Pane.Admin.ControllerPaneAdmin;
import com.SecurityDataBaseSystems.main.Pane.ControlSPC.ControllerPaneControlSPC;
import com.SecurityDataBaseSystems.main.Pane.Employees.ControllerPaneEmployees;
import com.SecurityDataBaseSystems.main.Pane.Job.ControllerPaneJob;
import com.SecurityDataBaseSystems.main.Pane.PrivateOffice.ControllerPanePrivateOffice;
import com.SecurityDataBaseSystems.main.Pane.Settings.ControllerPaneSettings;
import com.SecurityDataBaseSystems.main.Pane.SpecialOperation.ControllerPaneSpecialOperation;
import com.SecurityDataBaseSystems.main.PenetrationTesting.ControllerPenetrationTesting;
import com.SecurityDataBaseSystems.main.PenetrationTesting.PenetrationTesting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControllerWork {


    private Main main;

    @FXML
    private Pane pane;

    @FXML
    private Button buttonAdmin;

    @FXML
    private Button buttonControlSPC;

    @FXML
    private Button buttonEmployees;

    @FXML
    private Button buttonSpecialOperation;

    @FXML
    private Button buttonJob;

    @FXML
    private Button buttonBlock;

    @FXML
    private Button buttonPrivateOffice;

    @FXML
    private Button buttonSettings;

    @FXML
    private Button PenetrationTesting;

    @FXML
    private Button buttonExit;


    /**public Object OpenPane(String path)
     * Метод "открытия панели", заменяет содержимое главной рабочей панели.
     * String path - путь к FXML файлу, пример "FormDataBaseConnector.fxml"
     * Возвращает контроллер fxml файла для этой панели. Object необходимо дополнительно преобразовать
     *
     * Пример использования:
     *
     * ControllerDataBaseConnector controllerDataBaseConnector =
     *                                  (ControllerDataBaseConnector) OpenForm("FormDataBaseConnector.fxml");
     * */

    public Object OpenPane(String path)
    {
        String transformPath = new String(PathWindowsLinux.transformPath(path));

        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(Main.class.getResource(transformPath));
            pane.getChildren().removeAll(); //Очистка содержимого панели

            Pane tempPane = new Pane ((Pane) loader.load()); //Временная панель
            pane.getChildren().setAll(tempPane.getChildren());//Замена содержимого основоной панели содержимым временной

            main.primaryStage.show(); //Перерисовка формы

            return loader.getController(); //Захват контроллера

        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Ошибка при открытии панели");
            return null; //При ошибке
        }
    }

    public void Init () //Стартер-инициализатор
    {


        ControllerPanePrivateOffice controllerPanePrivateOffice = (ControllerPanePrivateOffice) OpenPane("Pane/PrivateOffice/PanePrivateOffice.fxml");
        controllerPanePrivateOffice.setMain(main);
        controllerPanePrivateOffice.Init();

        //Если пользователь не является администратором
        if (!main.dataBase.accessMatrixEmployee.isEmployeeAdmin(main.getIdEmployee()))
            buttonAdmin.setVisible(false);

        //Кнопка заблокировать
        buttonBlock.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Заблокировать");
                new Log("Кнопка заблокировать", Log.NORMAL_EVENT, "", main); //Логирование операции

                //Запуск блокировкщика системы с проверкой по базе данных
                ControllerLockScreen controllerLockScreen = (ControllerLockScreen) main.OpenForm("Block/FormLockScreen.fxml");
                controllerLockScreen.setMain(main); //Поместить главный класс
                controllerLockScreen.setDataBase(main.dataBase); //Поместить главный класс для работы с базой данных
                controllerLockScreen.Init(); //Инициализировать интерфейс
            }
        });

        //Кнопка заблокировать
        PenetrationTesting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка "+PenetrationTesting.getText());

                /**
                 * Отвечает за выполнение задач по тестированию на проникновение в БД ЦСН
                 *
                 */


                com.SecurityDataBaseSystems.main.PenetrationTesting.PenetrationTesting penetrationTesting = new PenetrationTesting();
                penetrationTesting.Show();
            }
        });


        //Кнопка личный кабинет
        buttonPrivateOffice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Личный кабинет");
                new Log("Кнопка личный кабинет", Log.NORMAL_EVENT, "", main); //Логирование операции

                ControllerPanePrivateOffice controllerPanePrivateOffice = (ControllerPanePrivateOffice) OpenPane("Pane/PrivateOffice/PanePrivateOffice.fxml");
                controllerPanePrivateOffice.setMain(main);
                controllerPanePrivateOffice.Init();

                System.out.println("Пароль для шифрования: "+main.getPassword());
            }
        });


        //Кнопка администратор
        buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Администратор");
                new Log("Кнопка администратор", Log.NORMAL_EVENT, "", main); //Логирование операции
                ControllerPaneAdmin controllerPaneAdmin = (ControllerPaneAdmin) OpenPane("Pane/Admin/PaneAdmin.fxml");
                controllerPaneAdmin.setMain(main);
                controllerPaneAdmin.Init();


            }
        });


        //Кнопка управление ЦСН
        buttonControlSPC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Управление ЦСН");
                new Log("Кнопка управление ЦСН", Log.NORMAL_EVENT, "", main); //Логирование операции
                ControllerPaneControlSPC controllerPaneControlSPC = (ControllerPaneControlSPC) OpenPane("Pane/ControlSPC/PaneControlSPC.fxml");

            }
        });

        //Кнопка сотрудники
        buttonEmployees.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Сотрудники");
                new Log("Кнопка сотрудники", Log.NORMAL_EVENT, "", main); //Логирование операции
                ControllerPaneEmployees controllerPaneEmployees = (ControllerPaneEmployees) OpenPane("Pane/Employees/PaneEmployees.fxml");
                controllerPaneEmployees.setMain(main);
                controllerPaneEmployees.Init();


            }
        });

        //Кнопка специальные операции
        buttonSpecialOperation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Специальные операции");
                new Log("Кнопка специальные операции", Log.NORMAL_EVENT, "", main); //Логирование операции

                ControllerPaneSpecialOperation controllerPaneSpecialOperation =
                        (ControllerPaneSpecialOperation) OpenPane("Pane/SpecialOperation/PaneSpecialOperation.fxml");

                controllerPaneSpecialOperation.setMain(main);
                controllerPaneSpecialOperation.Init();

            }
        });



        //Кнопка работа
        buttonJob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Работа");
                new Log("Кнопка работа", Log.NORMAL_EVENT, "", main); //Логирование операции
                ControllerPaneJob controllerPaneJob = (ControllerPaneJob) OpenPane("Pane/Job/PaneJob.fxml");
                controllerPaneJob.setMain(main);
                controllerPaneJob.Init();



            }
        });

        //Кнопка настройки
        buttonSettings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Настройки");
                new Log("Кнопка настройки", Log.NORMAL_EVENT, "", main); //Логирование операции
                ControllerPaneSettings controllerPaneSettings = (ControllerPaneSettings) OpenPane("Pane/Settings/PaneSettings.fxml");
                controllerPaneSettings.setMain(main);
                controllerPaneSettings.Init();



            }
        });

        //Кнопка выход
        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Нажата кнопка Выход");
                new Log("Кнопка "+buttonExit.getText(), Log.NORMAL_EVENT, "", main); //Логирование операции
                main.dataBase.Stop();
                main.initRootLayout();

            }

        });



    }


    //Ссылка на главный класс
    public void setMain(Main main)
    {
        this.main = main;
    }



    //Закрыть форму
    public void CloseThisForm()
    {
        main.primaryStage.close();
    }

}
package com.SecurityDataBaseSystems.main;

import java.io.IOException;

import com.SecurityDataBaseSystems.PathWindowsLinux.PathWindowsLinux;
import com.SecurityDataBaseSystems.Database.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public  class  Main extends Application{

    //Главный контейнер содержимого программы
    public Stage primaryStage;

    //ID сотрудника для работы со всей системой
    private int IdEmployee;

    //Корневая панель содержимого программы
    public  Pane rootLayout;

    //Класс для работы с базой данных
    public DataBase dataBase;

    //пароль пользователя для шифрования данных
    private String password;

//test

    //Точка входа в приложение
    public static void main(String[] args) {

        //Тестируем классы


        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setTitle("Центр специального назначения");

        //Загрузка окна подключения к базе данных (запускается при старте программы)
        initRootLayout();

    }


    /**public Object OpenForm(String path)
     * Метод "открытия формы", заменяет содержимое главной рабочей формы.
     * String path - путь к FXML файлу, пример "FormDataBaseConnector.fxml"
     * Возвращает контроллер fxml файла для этой формы. Object необходимо дополнительно преобразовать
     *
     * Пример использования:
     *
     * ControllerDataBaseConnector controllerDataBaseConnector =
     *                                  (ControllerDataBaseConnector) OpenForm("FormDataBaseConnector.fxml");
     * */

    public Object OpenForm(String path)
    {


        String transformPath = new String(PathWindowsLinux.transformPath(path));

        try {

            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(Main.class.getResource(transformPath));
            rootLayout = (Pane) loader.load();

            //Кроссплатформенный путь к файлу css
            transformPath = new String(PathWindowsLinux.transformPath("Style.css"));


            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(transformPath); //Утановка таблицы стилей

            //primaryStage.setScene(new Scene(rootLayout)); //Предыдущая версия

	        primaryStage.setScene(scene);
            primaryStage.show();
            return loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при открытии формы");
            return null;
        }


    }


    //Загрузка окна подключения к базе данных (запускается при старте программы)
    public void initRootLayout() {

        //Открыть форму
        ControllerDataBaseConnector controllerDataBaseConnector = (ControllerDataBaseConnector) OpenForm("FormDataBaseConnector.fxml");

        controllerDataBaseConnector.setMain(this); //Поместить родителя
        controllerDataBaseConnector.Init(); //Инициализировать функции элементов
    }


    //Загрузка окна основной рабочей формы программы (основаня рабочая среда программы)
    public void ShowWorkForm()
    {
        //Открыть форму
        ControllerWork controllerWork = (ControllerWork) OpenForm("FormWork.fxml");

        controllerWork.setMain(this); //Поместить родителя
        controllerWork.Init();  //Инициализировать функции элементов

    }


    public void setIdEmployee(int idEmployee)
    {
        this.IdEmployee = idEmployee;
    }



    public int  getIdEmployee()
    {
        return this.IdEmployee;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
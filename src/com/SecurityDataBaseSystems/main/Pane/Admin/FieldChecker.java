package com.SecurityDataBaseSystems.main.Pane.Admin;


import com.SecurityDataBaseSystems.main.Main;

//Класс проверки корректности введенных данных
public class FieldChecker {
    private Main main;
    ////////////////////////////////////////////Виды Полей//////////////////////////////////////////////////////////////
    public boolean checkRussianField(String text){ //Русское поле у которого 1-я буква обязательно заглавная
        System.out.println("Проверка русского поля");
        if(isRussian(text)&&!isEmpty(text)&&isUpperCase(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
    public boolean checkTelephoneField(String text) {//Поле с цифрами, у которого ограниченая длинна - 11 символов
        System.out.println("Проверка телефонного поля");
        if(!isEmpty(text)&&isNumber(text)&&correctTelephoneLength(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
    public boolean checkNumberField(String text) {//Поле с цифрами неограниченной длинны
        System.out.println("Проверка численного поля");
        if(!isEmpty(text)&&isNumber(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
    public boolean checkLoginField(String text){//Английское поле
        System.out.println("Проверка поля логина");
        System.out.println(text);
        if(isEnglish(text)&&!isEmpty(text)&& main.dataBase.employees.checkUniqueness(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
    public boolean checkEnglishField(String text){//Английское поле
        System.out.println("Проверка английского поля");
        if(isEnglish(text)&&!isEmpty(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
    public boolean checkPasswordField(String text) {//Поле пароля,с любыми символами,длина которого как минимум 8 символов
        System.out.println("Проверка пароля");
        if(!isEmpty(text) && correctPasswordLength(text)){
            System.out.println("Корректный ввод");
            return true;
        }
        return false;
    }
////////////////////////////////Вспомогательные методы//////////////////////////////////////////////////////////////

    public boolean isEmpty(String object){
        if (object.toCharArray().length == 0) {
            System.out.println("Пустое поле");
            return true;
        }
        return false;
    }

    public boolean isRussian(String object) {
        for (int i = 0; i < object.length(); i++) {
            if ((int) object.toCharArray()[i] != 32 && (int) object.toCharArray()[i] != 45 && (int) object.toCharArray()[i] < 1040 || (int) object.toCharArray()[i] > 1103) {
                System.out.print("Поддерживаются только русские буквы" + "\n");
                return false;
            }
        }
        return true;
    }
    public boolean isNumber(String object) {
        for (int i = 0; i < object.length(); i++) {
            if ((int) object.toCharArray()[i] < 48 || (int) object.toCharArray()[i] > 57) {
                System.out.println("Поддерживаются только цифры");
                return false;
            }
        }
        return true;
    }
    public boolean isEnglish(String object) {
        for (int i = 0; i < object.length(); i++) {
            if (((int) object.toCharArray()[i] < 65 || (int) object.toCharArray()[i] > 122) ) {
                System.out.print("Поддерживаются только английские буквы" + "\n");
                return false;
            }
        }
        return true;
    }
    public boolean isUpperCase(String object) {
        if ((int) object.toCharArray()[0] < 1040 || (int) object.toCharArray()[0] > 1071) {
            System.out.println("Первая буква должна быть заглавной");
            return false;
        }
        return true;
    }
    public boolean correctTelephoneLength(String object) {
        if (object.length()!=11) {
            System.out.println("Длинна телефона - 11 символов");
            return false;
        }
        return true;
    }
    public boolean correctPasswordLength(String object) {
        if (object.length()<8) {
            System.out.println("Длинна пароля - минимум 8 символов");
            return false;
        }
        return true;
    }

    //Метод возвращает строку найденных несоответсвий текущей проверки  //#Дмитрий Михайлов
    public String GetLogChecker ()
    {


        return "Всей найденные несоответствия";
    }
    public void setMain(Main main)
    {
        this.main = main;
    }
}
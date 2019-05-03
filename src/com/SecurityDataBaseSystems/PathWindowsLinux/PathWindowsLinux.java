package com.SecurityDataBaseSystems.PathWindowsLinux;

public class PathWindowsLinux {


    //Преобразует путь в формате "pac1/pac2/file.fxml" к нужному формату пути для конкретной ОС
    public static String transformPath (String path)
    {

        //Получаем массив частей одного пути
        String [] splitPath = path.split("/");


        //Строковый построитель
        StringBuilder SB = new StringBuilder();

        //Разделитель строки
        String separator = new String(PathWindowsLinux.getSeparator());

        //Цикл формирует новый путь к файлу с использованием новго разделителя
        int i;
        for (i=0; i<splitPath.length-1; i++)
            SB.append(splitPath[i]+separator);

        //Добавляем имя fxml файла
        SB.append(splitPath[i]);

      //  System.out.println("Преобразованный путь: "+SB.toString());

        //Возвращаем путь
        return SB.toString();
    }

    //Возвращает разделитель
    public static String getSeparator() {

        //Название операционной системы
        final String currentOS = System.getProperty("os.name").toLowerCase();

        if (currentOS.contains("win")) {
            // Текущая операционная система - Windows
            return "\\";
        } else if (currentOS.contains("nix") || currentOS.contains("nux")
                || currentOS.contains("mac")) {
            // Текущая операционная система - Linux, MacOS или "другой Unix"
            return "/";
        }
        // Мы не знаем, какая операционная система используется - возвращаем
        // значение по умолчанию
        return "/";
    }


}

package com.SecurityDataBaseSystems.main.GeneratePassword;



import com.SecurityDataBaseSystems.Crypto.HEX;
import com.SecurityDataBaseSystems.Crypto.SHA;
import com.SecurityDataBaseSystems.main.Message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Base64;


public class ControllerGeneratePassword {

    @FXML
    private Pane pane;

    @FXML
    private TextField textFieldHash;

    @FXML
    private TextArea textAreaCoordinate;

    @FXML
    private ProgressBar progressBar;

    private Integer iteration = new Integer(0);

    private Double x; //Координаты мыши по X
    private Double y; //Координаты мыши по Y



    private GeneratePassword generatePasswordParent;

    public  void Init()
    {


        //Клик мышки
        pane.setOnMouseClicked(event -> {
            Close();
        });

        //Дивжение мыши по панели
        pane.setOnMouseMoved(event -> {
            iteration++;
            progressBar.setProgress((float)iteration/3000);

            if (iteration>=3000)  { Close(); }



            Point location = MouseInfo.getPointerInfo().getLocation();
            x = location.getX();
            y = location.getY();

            String s = new String(HEX.toHex((x.toString()+y.toString()).getBytes())+"\n");
            textAreaCoordinate.appendText(s);
            SHA sha = new SHA();

            textFieldHash.setText(sha.StringToSHA1(textFieldHash.getText()+s));


        });
    }

    public void Close()
    {
        generatePasswordParent.Close();

        String str = new String(Base64.getMimeEncoder().encodeToString(textFieldHash.getText().getBytes()));

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<16; i++)
            stringBuilder.append(str.charAt(i));

        Message message = new Message(
                "Пароль успешно сгенерирован. \nВаш случайный пароль: \n"+

                stringBuilder.toString(),
                Message.GOOD
        );
        message.ShowMessage();

    }

    public void setGeneratePasswordParent(GeneratePassword generatePasswordParent) {
        this.generatePasswordParent = generatePasswordParent;
    }
}

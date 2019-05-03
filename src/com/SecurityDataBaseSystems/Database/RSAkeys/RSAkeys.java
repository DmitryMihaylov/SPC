package com.SecurityDataBaseSystems.Database.RSAkeys;

import com.SecurityDataBaseSystems.Database.DataBase;
import com.SecurityDataBaseSystems.main.Main;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RSAkeys {

    private Statement statement;

    private DataBase dataBase;

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     *
     * @param publicKey
     * @param idEmploye
     *
     * Метод Проверяет, установлен ли RSA ключ у текущего пользователя, если нет, то метод устанавливает новый ключ
     * путем добавления новой записи в базу данных, иначе - обновляет уже существующий ключ
     */
    public void setRSA(String publicKey, int idEmploye)
    {

        try {
            String Query = "SELECT RSAPublicKey FROM RSAkeys WHERE idEmploye ="+idEmploye;
            ResultSet rs=statement.executeQuery(new String(Query.getBytes(), "utf-8"));

            //Если ключ не установлен, то установить новый, иначе обновить старый
            boolean temp = rs.next();
            rs.close();

            if (!temp) {
                //Установить
                String SetKeysQuery = "INSERT INTO RSAkeys (" +
                        "idEmploye, " +

                        "RSAPublicKey)" +

                        " VALUES ('" +
                        idEmploye+ "', '" +

                        publicKey+ "' )";

                statement.executeUpdate(new String(SetKeysQuery.getBytes(), "utf-8"));
                System.out.println(SetKeysQuery);

            }
            else {

                //Обновить
                String UpdeteKeyQuery = "UPDATE RSAkeys SET RSAPublicKey ='"+publicKey+"' WHERE idEmploye ="+idEmploye;
                statement.executeUpdate(new String(UpdeteKeyQuery.getBytes(), "utf-8"));
                System.out.println(UpdeteKeyQuery);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<RSApublicKeyEmoloye> getRSAkeysOfEmployees ()
    {
        ArrayList<RSApublicKeyEmoloye> arrayList = new ArrayList<RSApublicKeyEmoloye>();

        ResultSet rs = null;

        String Query = new String("SELECT * FROM RSAkeys");

        System.out.println(Query);

        try {

            rs = statement.executeQuery(Query); //Список сообщений

            RSApublicKeyEmoloye rsaPublicKeyEmoloye;

            int i=0;
            while (rs.next()) {

                i++;
               rsaPublicKeyEmoloye = new RSApublicKeyEmoloye(
                        rs.getString("RSAPublicKey"),
                        rs.getInt("idEmploye"), dataBase
                );

               //System.out.println("i = "+i+" "+rs.getString("RSAPublicKey")+" "+rs.getInt("idEmploye"));
                arrayList.add(rsaPublicKeyEmoloye);
            }


            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return arrayList;
    }


    //Взятие одного RSA ключа
    public RSApublicKeyEmoloye getRSApublicKeyEmoloye (String idEmploye)
    {
        ResultSet rs = null;

        String Query = new String("SELECT * FROM RSAkeys");

        System.out.println(Query);
        RSApublicKeyEmoloye rsaPublicKeyEmoloye = null;

        try {
            rs = statement.executeQuery(Query); //Список сообщений
            rs.next();
            rsaPublicKeyEmoloye = new RSApublicKeyEmoloye(
                    rs.getString("RSAPublicKey"),
                    rs.getInt("idEmploye"), dataBase
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }




        return  rsaPublicKeyEmoloye ;
    }
}

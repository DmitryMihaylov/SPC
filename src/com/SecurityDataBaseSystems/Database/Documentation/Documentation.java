package com.SecurityDataBaseSystems.Database.Documentation;


import com.SecurityDataBaseSystems.Crypto.AES;
import com.SecurityDataBaseSystems.Crypto.SHA;
import com.SecurityDataBaseSystems.main.Main;


import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

public class Documentation {

    private Statement statement;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void SetDocument(String Header, String idEmploye, String Document )
    {

        //Хеш
        SHA sha = new SHA();



        AES aes = new AES();

        String Crypt = new String
                     (
                             Base64.getEncoder().encodeToString
                                     (

                                            aes.encrypt(main.getPassword(), Document.getBytes(), main.getPassword())
                                     )
                     );


        String CheckSum = new String(sha.StringToSHA1(Header+idEmploye+Crypt)); //Рассчет контрольной суммы

        String Query = "INSERT INTO Documentation (" +
                "Header, " +
                "idEmploye, " +
                "Document, " +
                "CheckSum)" +

                " VALUES ('" +
                Header + "', '" +
                idEmploye+"', '"+
                Crypt + "', '" +
                CheckSum.toString()+ "' )";

        System.out.println(Query);

        try {
            statement.executeUpdate(new String(Query.getBytes(), "utf-8"));


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getHeaderDocument (String id)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Documentation WHERE idDocument="+id); //Список сообщений
            rs.next();
            return  rs.getString("Header");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Document> getDociments(int id)
    {
        ArrayList<Document> Documents	= new ArrayList<Document>(); //Список сообщений

        ResultSet rs = null;

        try {

            rs = statement.executeQuery("SELECT * FROM Documentation WHERE idEmploye="+id); //Список сообщений

            while (rs.next()) {

                Document document = new Document();

                document.setIdDocument(new String(rs.getString("idDocument")));
                document.setHeader(new String(rs.getString("Header")));
                document.setCheckSum(new String(rs.getString("CheckSum")));

                Documents.add(document);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return Documents;
    }


    public String getCheckSumDocument(String id)
    {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Documentation WHERE idDocument="+id); //Список сообщений
            rs.next();
            return  rs.getString("CheckSum");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDocument(String idDocument)
    {
        ResultSet rs = null;
        String Query = new String("SELECT * FROM Documentation WHERE idDocument="+idDocument);
        AES aes = new AES();
        try {

            rs = statement.executeQuery(Query);

            while (rs.next()) {
                String Decrypt = new String(
                        aes.decrypt(main.getPassword(), (  Base64.getDecoder().decode(
                        rs.getString("Document")
                )
                        ), main.getPassword() )

                );
                return Decrypt;
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }



}

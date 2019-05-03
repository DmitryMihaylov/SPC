package com.SecurityDataBaseSystems.Database.Documentation;

import com.SecurityDataBaseSystems.main.Main;

/*
* Класс для работы с документами
* */


public class Document {

   private Main main;

    public  void setMain(Main main) {

      this.main = main;
    }

    private String Header;
    private String idDocument;
    private String CheckSum;

    public String getHeader() {
        return Header;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public String getCheckSum() {
        return CheckSum;
    }

    public void setHeader(String header) {
        this.Header = header;
    }

    public  Main getMain() {
        return main;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public void setCheckSum(String checkSum) {
        this.CheckSum = checkSum;
    }

    public String getIdDocumentHeader()
    {
        return getIdDocument() +" "+getHeader();
    }

    @Override
    public String toString() {
        return getHeader() + " \n" + getIdDocument()+" \n"+getCheckSum();
    }
}

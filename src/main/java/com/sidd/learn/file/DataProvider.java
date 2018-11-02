package com.sidd.learn.file;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataProvider {

    private String filePath = null;
    private BufferedReader br = null;


    public DataProvider(String filePath){
        this.filePath = filePath;
        try{
            this.br = new BufferedReader(new FileReader(filePath));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String readNext() {
        String line = null;
        try {
            line = br.readLine();
            if(line == null){
                this.br =  new BufferedReader(new FileReader(filePath));
                line = readNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return line;
    }
}

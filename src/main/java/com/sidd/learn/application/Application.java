package com.sidd.learn.application;

import com.sidd.learn.file.DataProvider;
import com.sidd.learn.producer.MessageProducer;

public class Application {

    public static void main(String[] args) {

        String filePath = "C:\\Windows\\WindowsUpdate.log";

        DataProvider dataProvider = new DataProvider(filePath);

        MessageProducer producer = new MessageProducer();
        while(true){
            try{
                producer.sendMessage(dataProvider.readNext());
                Thread.sleep(5000);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }



}

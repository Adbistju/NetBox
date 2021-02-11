package com.gb.java.file.manager;

import com.gb.java.file.manager.Client.ClientApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControlerServer {

    ClientApp clientApp = new ClientApp();
    private int s = 0;
    private int m = 0;
    
    @FXML
    TextField userNameDB;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField serverAddress;

    @FXML
    TextField port;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        if(m==0){
            clientApp.send("-auth "+userNameDB.getText()+" "+passwordField.getText());
            m++;
        } else if (m==1) {
            clientApp.send("-exit");
            m=0;
        }
    }

    public void handleConnectButtonAction(ActionEvent actionEvent) {
        if(s==0){
            clientApp.setAddres(serverAddress.getText());
            clientApp.setPort(port.getText());
            clientApp.clientAppInit();
            s++;
            new Thread(() -> {
                clientApp.connection();
            }).start();
        } else if(s==1){
            System.out.println("disconnect");
            clientApp.send("-exit");
            //try {
                clientApp.unconnection();
                //clientApp.getSocket().close();
           /* } catch (IOException e) {
                e.printStackTrace();
            }*/
            s=0;
        }
    }
}

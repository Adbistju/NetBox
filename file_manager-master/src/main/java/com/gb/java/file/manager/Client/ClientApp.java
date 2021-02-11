package com.gb.java.file.manager.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp implements ClientAppInt {
/*
    String addres = "localhost";
    String port = "8888";
    */
    String addres;
    String port;
    Socket socket = new Socket();
    DataInputStream in;
    DataOutputStream out;

    public ClientApp() {
        this.addres = null;
        this.port = null;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Socket getSocket(){
        return socket;
    }


    public void clientAppInit() {
        try {
            socket = new Socket(addres, Integer.parseInt(port));
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connection(){
        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    System.out.println(message);
                }
            } catch (IOException e) {

                throw new RuntimeException("SWW", e);
            }
        }).start();

        send();
    }

    public void unconnection(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("...");
                out.writeUTF(scanner.nextLine());
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }

    public void send(String str){
        try {
            System.out.println("...");
            out.writeUTF(str);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }
}


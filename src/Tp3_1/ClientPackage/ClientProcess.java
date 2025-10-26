package Tp3_1.ClientPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcess extends Thread{
    Socket socket;
    public ClientProcess(Socket scoket){
        this.socket=scoket;
    }
    public void run(){

        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Connecte au serveur"+socket.getLocalSocketAddress());
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

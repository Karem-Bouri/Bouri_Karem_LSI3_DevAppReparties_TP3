package Tp3_1.ServerPackage;

import Tp3_1.ClientPackage.ClientProcess;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static void main(String[] args) throws IOException {
        new Server().start();

    }
    public void run(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Serveur en attente ...");
            int i=0;
            while(true){

            Socket socket = serverSocket.accept();

            i++;
            System.out.println("Client n°"+i+" connecté ");
            System.out.println(socket.getRemoteSocketAddress());


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Vous etes le client n° "+i);

            new ClientProcess(socket).start();




            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

        /*



        socket.close();
        serverSocket.close();
         */

}

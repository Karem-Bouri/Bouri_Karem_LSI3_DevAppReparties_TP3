package Tp3_2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 5000);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connecté au serveur !");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("a = ");
                double a = sc.nextDouble();

                System.out.print("op (+ - * /) = ");
                String op = sc.next();

                System.out.print("b = ");
                double b = sc.nextDouble();

                // Envoi d'une opération
                out.writeObject(new Operation(a, b, op));
                out.flush();

                // Réception du résultat
                Resultat res = (Resultat) in.readObject();
                System.out.println(">>> " + res);

                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

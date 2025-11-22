package Tp3_2;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private static final int PORT = 5000;

    // Compteur global synchronisé
    private static AtomicInteger compteurOperations = new AtomicInteger(0);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("=== Serveur Calculatrice Multithread ===");
            System.out.println("En attente de clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientProcess(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interne pour gérer chaque client
    static class ClientProcess extends Thread {

        private Socket socket;

        public ClientProcess(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                System.out.println("Client connecté : " + socket.getRemoteSocketAddress());

                Operation op;
                while ((op = (Operation) in.readObject()) != null) {

                    // Traitement de l’opération
                    double res = 0;
                    switch (op.getOp()) {
                        case "+": res = op.getA() + op.getB(); break;
                        case "-": res = op.getA() - op.getB(); break;
                        case "*": res = op.getA() * op.getB(); break;
                        case "/": res = op.getA() / op.getB(); break;
                    }

                    // Mise à jour compteur global
                    int total = compteurOperations.incrementAndGet();
                    System.out.println("[Serveur] Total opérations = " + total);

                    // Envoi du résultat
                    out.writeObject(new Resultat(res));
                    out.flush();
                }

                socket.close();

            } catch (Exception e) {
                System.out.println("Client déconnecté.");
            }
        }
    }
}


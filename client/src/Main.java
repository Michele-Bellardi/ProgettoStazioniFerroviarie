import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;

    public static void main(String[] args) {
        try (Socket socket = new Socket(nomeServer, portaServer);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connesso al server: " + socket.getRemoteSocketAddress());

            while (true) {
                String line;
                // Legge finché non riceve INPUT_REQUEST o la connessione si chiude
                while ((line = in.readLine()) != null) {
                    if (line.equals("INPUT_REQUEST")) {
                        // Server sta aspettando un input da parte nostra
                        System.out.print(">> ");
                        String userInput = scanner.nextLine();
                        out.println(userInput);
                        break; // Torna ad ascoltare risposte del server dopo l'input
                    } else {
                        System.out.println(line);
                        if (line.equalsIgnoreCase("EchoServer: closing...")) {
                            return; // Chiude se il server si sta spegnendo
                        }
                    }
                }

                if (line == null) {
                    System.out.println("Connessione chiusa dal server.");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
        final static String nomeServer = "localhost";
        final static int portaServer = 1050;
        public static void main(String[] args) {
            System.out.println("Connessione al server in corso...");
            try (Socket sck = new Socket(nomeServer, portaServer)) {
                String rem = sck.getRemoteSocketAddress().toString();
                String loc = sck.getLocalSocketAddress().toString();
                System.out.format("Server (remoto): %s%n", rem);
                System.out.format("Client (client): %s%n", loc);
                comunica(sck);
            } catch (UnknownHostException e) {
                System.err.format("Nome di server non valido: %s%n", e.getMessage());  } catch (IOException e) {
                System.err.format("Errore durante la comunicazione con il server: %s%n",  e.getMessage());
            }

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
        }
        private static void comunica(Socket sck) throws IOException {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sck.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(sck.getOutputStream(), "UTF-8"), true);
            Scanner s = new Scanner(System.in, "UTF-8");

            while(true){
                System.out.println("In attesa di risposta dal server...");
                while (true) {
                    String risposta = in.readLine();
                    if (risposta == null) {
                        System.out.println("fine informazioni");
                        break;
                    }
                    System.out.println(risposta);
                }

                System.out.print("inserire il comando: ");
                String frase = s.nextLine();
                System.out.format("Invio al server: %s%n", frase);
                out.println(frase);
            }
        }

    public static void createAndShowGUI() {
        // Creazione della finestra
        JFrame frame = new JFrame("Griglia di Pulsanti");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Creazione del pannello con layout a griglia
        JPanel panel = new JPanel(new GridLayout(2, 3));

        // Creazione dei pulsanti con le etichette indicate
        JButton btnGetRow = new JButton("GET_ROW");
        JButton btnGetMunicipality = new JButton("GET_MUNICIPALITY");
        JButton btnGetName = new JButton("GET_NAME");
        JButton btnGetYear = new JButton("GET_YEAR");
        JButton btnGetCoordinates = new JButton("GET_COORDINATES");
        JButton btnGetIndicator = new JButton("GET_INDICATOR");

        // Aggiunta dei pulsanti al pannello
        panel.add(btnGetRow);
        panel.add(btnGetMunicipality);
        panel.add(btnGetName);
        panel.add(btnGetYear);
        panel.add(btnGetCoordinates);
        panel.add(btnGetIndicator);

        // Aggiunta del pannello alla finestra
        frame.add(panel);

        // Visualizzazione della finestra
        frame.setVisible(true);
    }
}
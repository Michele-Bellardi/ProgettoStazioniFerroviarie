import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientGUI extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea textArea;

    public ClientGUI(String host, int port) {
        setTitle("Client Stazioni Ferroviarie");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Bottoni per ogni comando
        String[] comandi = {
                "GET_ROW", "GET_NAME", "GET_MUNICIPALITY",
                "GET_YEAR", "GET_COORDINATES", "GET_INDICATOR"
        };

        for (String comando : comandi) {
            JButton button = new JButton(comando);
            button.addActionListener(e -> inviaComando(comando));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connessione al server riuscita.");
        } catch (IOException e) {
            System.out.println("Errore di connessione al server: " + e.getMessage());
        }

        setVisible(true);
    }

    private void inviaComando(String comando) {
        if (socket == null || socket.isClosed()) {
            log("Socket chiusa. Riavvia l'applicazione.");
            return;
        }

        try {
            out.println(comando);
            String risposta;

            while ((risposta = in.readLine()) != null) {
                if (risposta.equals("INPUT_REQUEST")) {
                    String input = JOptionPane.showInputDialog(this, "Inserisci valore:");
                    if (input == null) return;
                    out.println(input);
                } else {
                    textArea.append(risposta + "\n");
                }

                // Fine risposta se il server smette di inviare righe
                if (!in.ready()) break;
            }
        } catch (IOException e) {
            log("Errore durante la comunicazione con il server: " + e.getMessage());
        }
    }

    private void log(String msg) {
        textArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI("localhost", 12345)); // Cambia porta se serve
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class Main extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JFrame visualizzaInfoFrame; // Change to JFrame
    private JTextArea textArea;

    public Main(String host, int port) {
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

        visualizzaInfoFrame = new JFrame("Informazioni Server");
        visualizzaInfoFrame.setSize(600, 400);
        visualizzaInfoFrame.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        visualizzaInfoFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        visualizzaInfoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Close but keep the main app running
        visualizzaInfoFrame.setVisible(true);

        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            //log("Connessione al server riuscita.");
        } catch (IOException e) {
            //log("Errore di connessione al server: " + e.getMessage());
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (socket != null && !socket.isClosed()) {
                        out.println("END");
                        socket.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Errore durante la chiusura del socket: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    private void inviaComando(String comando) {
        log("Invio comando: " + comando);
        if (socket == null || socket.isClosed()) {
            System.out.println("Socket chiusa. Riavvia l'applicazione.");
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
                    log(risposta);
                }

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
        int PORT = 1050;
        SwingUtilities.invokeLater(() -> new Main("localhost", PORT)); // Cambia porta se necessario
    }
}
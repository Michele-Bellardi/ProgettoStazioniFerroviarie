import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;

    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        try (Socket socket = new Socket(nomeServer, portaServer)) {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

            SwingUtilities.invokeLater(Main::createAndShowGUI);

            // Thread per ascoltare risposte dal server
            new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.equals("INPUT_REQUEST")) {
                            String input = JOptionPane.showInputDialog(null, "Inserisci il valore richiesto:");
                            if (input != null) {
                                out.println(input);
                            }
                        } else {
                            showServerResponse(line);
                        }
                    }
                } catch (IOException e) {
                    showServerResponse("Errore durante la comunicazione con il server: " + e.getMessage());
                }
            }).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore di connessione al server: " + e.getMessage());
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Stazioni Ferroviarie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 3));

        addButton(panel, "GET_ROW");
        addButton(panel, "GET_MUNICIPALITY");
        addButton(panel, "GET_NAME");
        addButton(panel, "GET_YEAR");
        addButton(panel, "GET_COORDINATES");
        addButton(panel, "GET_INDICATOR");

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void addButton(JPanel panel, String command) {
        JButton button = new JButton(command);
        button.addActionListener(e -> {
            out.println(command);
        });
        panel.add(button);
    }

    private static void showServerResponse(String message) {
        SwingUtilities.invokeLater(() -> {
            JFrame responseFrame = new JFrame("Risposta dal Server");
            responseFrame.setSize(400, 200);
            responseFrame.setLocationRelativeTo(null);

            JTextArea textArea = new JTextArea(message);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            responseFrame.add(new JScrollPane(textArea));

            responseFrame.setVisible(true);
        });
    }
}

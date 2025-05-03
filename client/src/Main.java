import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
/**
 * La classe rappresenta il client grafico di un'applicazione per la consultazione di informazioni su stazioni ferroviarie.
 * Comunica con un server tramite socket e mostra i risultati all'utente.
 *
 * Ogni pulsante rappresenta un comando che il client può inviare al server.
 * In caso il server richieda un input aggiuntivo, viene mostrata una finestra di dialogo per inserire il dato.
 *
 * La risposta del server viene mostrata in una finestra separata.
 */
public class Main extends JFrame {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JFrame visualizzaInfoFrame;
    private JTextArea textArea;

    /**
     * Costruttore principale del client.
     * Crea l'interfaccia grafica, stabilisce la connessione con il server e imposta i listener per la gestione degli eventi.
     *
     * @param host indirizzo del server a cui connettersi
     * @param port porta su cui è in ascolto il server
     */
    public Main(String host, int port) {
        setTitle("Client Stazioni Ferroviarie");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Comandi disponibili
        String[] comandi = {
                "GET_ROW", "GET_NAME", "GET_MUNICIPALITY",
                "GET_YEAR", "GET_COORDINATES", "GET_INDICATOR"
        };

        // Crea un bottone per ogni comando
        for (String comando : comandi) {
            JButton button = new JButton(comando);
            button.addActionListener(e -> inviaComando(comando));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Finestra secondaria per visualizzare la risposta del server
        visualizzaInfoFrame = new JFrame("Informazioni Server");
        visualizzaInfoFrame.setBounds(600, 0, 600, 400);
        visualizzaInfoFrame.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        visualizzaInfoFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        visualizzaInfoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        visualizzaInfoFrame.setVisible(true);

        // Connessione al server
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            log("Errore di connessione al server: " + e.getMessage());
        }

        // Chiude il socket alla chiusura della finestra principale
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

    /**
     * Invia un comando al server e gestisce l'interazione, inclusa l'eventuale richiesta di input.
     *
     * @param comando il comando da inviare.
     */
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
                    String r2 = in.readLine();
                    log(r2);
                } else {
                    log(risposta);
                }

                if (!in.ready()) break;
            }
        } catch (IOException e) {
            log("Errore durante la comunicazione con il server: " + e.getMessage());
        }
    }

    /**
     * Scrive un messaggio nell'area di testo della finestra informativa.
     *
     * @param msg il messaggio da visualizzare
     */
    private void log(String msg) {
        textArea.append(msg + "\n");
    }

    /**
     * Metodo main. Avvia l'applicazione client.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        int PORT = 1050;
        SwingUtilities.invokeLater(() -> new Main("localhost", PORT));
    }
}

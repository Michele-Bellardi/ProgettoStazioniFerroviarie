import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * La classe una singola connessione con un client tramite socket.
 * La classe estende la classe Thread per gestire più di una connessione per volta.
 * La calsse riceve comandi dal client, li interpreta in base all'enum Opzioni ed esegue le relative operazioni tramite la classe Operazioni.
 */
public class Connessione extends Thread {
    /** Socket di comunicazione con il client. */
    private Socket clientSocket;

    /** Stream per la lettura dei messaggi provenienti dal client. */
    private BufferedReader in;

    /** Stream per l'invio di messaggi al client. */
    private PrintWriter out;

    /**
     * Costruttore della classe Connessione.
     * Inizializza i flussi di input e output associati al socket del client.
     *
     * @param clientSocket il socket associato al client connesso.
     * @throws RuntimeException se si verifica un errore durante la creazione degli stream.
     */
    public Connessione(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'inizializzazione degli stream");
        }
    }

    /**
     * Metodo che ascolta e interpreta i comandi inviati dal client, eseguendo le operazioni richieste tramite la classe Operazioni, e rispondendo con i risultati.
     */
    @Override
    public void run() {
        Operazioni operazioni = new Operazioni();
        System.out.println("Connessione avviata con: " + clientSocket);
        String str = null;
        try {
            do {
                str = in.readLine();
                System.out.println("Messaggio ricevuto dal client: " + str);

                if (str.equalsIgnoreCase("STOP") || str.equalsIgnoreCase("END")) {
                    System.out.println("Chiusura richiesta dal client.");
                    break;
                }

                Opzioni scelta = Opzioni.fromString(str);
                if (scelta == null) {
                    out.println("OPZIONE NON PRESENTE");
                    continue;
                }

                switch (scelta) {
                    case GET_ROW:
                        out.println("Inserisci la riga:");
                        out.println("INPUT_REQUEST");
                        String row = in.readLine();
                        int riga = Integer.parseInt(row);
                        out.println(operazioni.getRow(riga));
                        break;

                    case GET_MUNICIPALITY:
                        out.println("Inserisci il comune:");
                        out.println("INPUT_REQUEST");
                        String municipality = in.readLine();
                        ArrayList<String> stazioniComune = operazioni.getMunicipality(municipality);
                        for (String s : stazioniComune) {
                            out.println(s);
                        }
                        break;

                    case GET_NAME:
                        out.println("Inserisci il nome:");
                        out.println("INPUT_REQUEST");
                        String name = in.readLine();
                        out.println(operazioni.getName(name));
                        break;

                    case GET_YEAR:
                        out.println("Inserisci l'anno:");
                        out.println("INPUT_REQUEST");
                        String year = in.readLine();
                        ArrayList<String> stazioniAnno = operazioni.getYear(year);
                        for (String s : stazioniAnno) {
                            out.println(s);
                        }
                        break;

                    case GET_COORDINATES:
                        out.println("Inserisci x:");
                        out.println("INPUT_REQUEST");
                        String x = in.readLine();
                        out.println("Inserisci y:");
                        out.println("INPUT_REQUEST");
                        String y = in.readLine();
                        out.println(operazioni.getCoordinate(x, y));
                        break;

                    case GET_INDICATOR:
                        out.println("Inserisci indicatore:");
                        out.println("INPUT_REQUEST");
                        String indicator = in.readLine();
                        out.println(operazioni.getIndicator(indicator));
                        break;

                    default:
                        out.println("NESSUNA OPZIONE CORRISPONDENTE");
                        break;
                }
            } while (str != null);
        } catch (IOException e) {
            System.err.println("Errore durante la comunicazione con il client: " + e.getMessage());
        } finally {
            try {
                System.out.println("Chiusura della connessione con il client.");
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

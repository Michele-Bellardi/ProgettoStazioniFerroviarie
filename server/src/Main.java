import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * La classe Main rappresenta il punto di ingresso del server per la gestione delle stazioni ferroviarie.
 * Legge i dati da un file CSV, li carica in memoria e avvia un server socket in ascolto su una porta specifica.
 * Per ogni client che si connette, viene avviato un thread separato per gestire la connessione.
 */
public class Main {

    /** Lista contenente le informazioni delle stazioni ferroviarie caricate da file. */
    public static ArrayList<Stazione> info = new ArrayList<>();

    /** Porta su cui il server resta in ascolto. */
    public static int PORT = 1050;

    /**
     * Metodo che carica i dati dal file CSV e avvia il server socket.
     */
    public static void main(String[] args) {
        prelevaDaFile();

        try (ServerSocket serverSocket = new ServerSocket(Main.PORT)) {
            System.out.println("Server in ascolto sulla porta " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Connessione(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }

    /**
     * Legge le informazioni delle stazioni da un file CSV chiamato "Mappa-delle-stazioni-ferroviarie-in-Italia.csv".
     * Ogni riga del file (esclusa l’intestazione) viene trasformata in un oggetto di tipo Stazione e aggiunta alla lista info.
     */
    public static void prelevaDaFile(){
        String filePath = new File("Mappa-delle-stazioni-ferroviarie-in-Italia.csv").getAbsolutePath();
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String comune = columns[0];
                String provincia = columns[1];
                String regione = columns[2];
                String nome = columns[3];
                String annoInserimento = columns[4];
                String dataEOraInserimento = columns[5];
                String identificatore = columns[6];
                String longitudine = columns[7];
                String latitudine = columns[8];

                info.add(new Stazione(comune, provincia, regione, nome, annoInserimento, dataEOraInserimento, identificatore, longitudine, latitudine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

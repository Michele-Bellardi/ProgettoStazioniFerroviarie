import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Connessione extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

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

    @Override
    public void run() {
        Operazioni operazioni = new Operazioni();
        System.out.println("Connessione avviata con: " + clientSocket);
        String str = "a";
        try {

            while ((str != null)) {
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
                        out.println(operazioni.getRow(riga).toString());
                        break;

                    case GET_MUNICIPALITY:
                        out.println("Inserisci il comune:");
                        out.println("INPUT_REQUEST");
                        String municipality = in.readLine();
                        ArrayList<Stazione> stazioniComune = operazioni.getMunicipality(municipality);
                        for (Stazione s : stazioniComune) {
                            out.println(s.toString());
                        }
                        break;

                    case GET_NAME:
                        out.println("Inserisci il nome:");
                        out.println("INPUT_REQUEST");
                        String name = in.readLine();
                        out.println(operazioni.getName(name).toString());
                        break;

                    case GET_YEAR:
                        out.println("Inserisci l'anno:");
                        out.println("INPUT_REQUEST");
                        String year = in.readLine();
                        ArrayList<Stazione> stazioniAnno = operazioni.getYear(year);
                        for (Stazione s : stazioniAnno) {
                            out.println(s.toString());
                        }
                        break;

                    case GET_COORDINATES:
                        out.println("Inserisci x:");
                        out.println("INPUT_REQUEST");
                        String x = in.readLine();
                        out.println("Inserisci y:");
                        out.println("INPUT_REQUEST");
                        String y = in.readLine();
                        out.println(operazioni.getCoordinate(x, y).toString());
                        break;

                    case GET_INDICATOR:
                        out.println("Inserisci indicatore:");
                        out.println("INPUT_REQUEST");
                        String indicator = in.readLine();
                        out.println(operazioni.getIndicator(indicator).toString());
                        break;

                    default:
                        out.println("NESSUNA OPZIONE CORRISPONDENTE");
                        break;
                }
            }
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
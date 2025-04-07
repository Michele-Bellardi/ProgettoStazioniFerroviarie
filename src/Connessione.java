import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Connessione extends Thread {
    private Socket clientSocket;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public Connessione(Socket clientSocket){
        this.clientSocket = clientSocket;
        InputStreamReader isr = null;
        try{
            isr = new InputStreamReader((clientSocket.getInputStream()));
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        in = new BufferedReader(isr);
    }

    @Override
    public void run(){
        Operazioni prove = new Operazioni();
        System.out.println("EchoServer: started ");
        System.out.println("Connection accepted: " + clientSocket);
        while(true){
            out.println(printmenu());
            String str = null;
            try {
                str = in.readLine();
                if(str.equals("STOP")){
                    out.close();
                    try {
                        in.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                }
                Opzioni scelta = Opzioni.fromString(str);
                switch (scelta){
                    case Opzioni.GET_ROW:
                        out.print("inserisci la riga:");
                        int row = in.read();
                        System.out.println(prove.getRow(row).toString());
                        break;
                    case Opzioni.GET_MUNICIPALITY:
                        out.println("inserisci il comune:");
                        String municipality = in.readLine();
                        ArrayList<Stazione> stazioniDelComune = prove.getMunicipality(municipality);
                        for (int i = 0; i < stazioniDelComune.size(); i++){
                            out.println(stazioniDelComune.get(i).toString());
                        }
                        break;
                    case Opzioni.GET_NAME:
                        out.println("inserisci il nome:");
                        String name = in.readLine();
                        out.println(prove.getName(name).toString());
                        break;
                    case Opzioni.GET_YEAR:
                        out.println("inserisci l'anno:");
                        String year = in.readLine();
                        ArrayList<Stazione> stazioniDellAnno = prove.getYear(year);
                        for (int i  = 0; i < stazioniDellAnno.size(); i++){
                            out.println(stazioniDellAnno.get(i).toString());
                        }
                        break;
                    case Opzioni.GET_COORDINATES:
                        out.println("inserisci x:");
                        String x = in.readLine();
                        out.println("inserisci y:");
                        String y = in.readLine();
                        out.println(prove.getCoordinate(x, y).toString());
                        break;
                    case Opzioni.GET_INDICATOR:
                        out.println("inserisci indicatore:");
                        String indicator = in.readLine();
                        out.println(prove.getIndicator(indicator).toString());
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (str.equals("END")) break;
        }
        System.out.println("EchoServer: closing...");
        out.close();
        try {
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printmenu(){
        return "Scegliere un'operazione: " + Opzioni.GET_ROW + ". get row\n" + Opzioni.GET_MUNICIPALITY + ". get municipality\n" + Opzioni.GET_NAME + ". get name\n" + Opzioni.GET_YEAR + ". get year\n" + Opzioni.GET_COORDINATES + ". get coordinates\n" + Opzioni.GET_INDICATOR + ". get coordinates\nEND to close connection\nSTOP to end the program";
    }
}
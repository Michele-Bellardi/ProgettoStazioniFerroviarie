import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Stazione> info = new ArrayList<>();

    public static void main(String[] args) {
        prelevaDaFile();
        for (int i = 0; i < info.size(); i++){
            //System.out.println(info.get(i).toString());
        }
        Operazioni prove = new Operazioni();
        prove.getRow(3);
    }

    //metodo per prelevare le informazioni presenti nel file csv
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
                //creazione array di informazioni prelevate dal file csv
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

                //inserimento nell'arraylist
                info.add(new Stazione(comune, provincia, regione, nome, annoInserimento, dataEOraInserimento, identificatore, longitudine, latitudine));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
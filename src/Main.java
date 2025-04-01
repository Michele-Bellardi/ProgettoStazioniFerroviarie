import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Stazione> info = new ArrayList<>();

    public static void main(String[] args) {
        prelevaDaFile();

        //TESTING
        /*

        //prelevare da file
        for (int i = 0; i < info.size(); i++){
            System.out.println(info.get(i).toString());
        }

        Operazioni prove = new Operazioni();

        //riga
        System.out.println(prove.getRow(3).toString());

        //comune
        ArrayList<Stazione> stazioniDelComune = prove.getMunicipality("BORGARO TORINESE");
        for (int i = 0; i < stazioniDelComune.size(); i++){
            System.out.println(stazioniDelComune.get(i).toString());
        }

        //nome
        System.out.println(prove.getName("Matera Centrale").toString());

        //anno
        ArrayList<Stazione> stazioniDellAnno = prove.getYear("2013");
        for (int i  = 0; i < stazioniDellAnno.size(); i++){
            System.out.println(stazioniDellAnno.get(i).toString());
        }

        //coordinate
        System.out.println(prove.getCoordinate("8.9750146", "46.0085186").toString());

        //indicatore
         System.out.println(prove.getIndicator("984003073").toString());
        */
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
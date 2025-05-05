import java.util.ArrayList;

/**
 * La classe fornisce metodi per filtrare, cercare e ottenere informazioni sulle stazioni ferroviarie caricate nell'ArrayList info nella classe Main.
 * Le ricerche possono essere effettuate tramite riga, nome, comune, anno, coordinate o identificatore.
 */
public class Operazioni {

    /**
     * Costruttore della classe Operazioni.
     * Crea un oggetto che permette l'esecuzione di ricerche sulle stazioni.
     */
    public Operazioni() {
    }

    /**
     * Restituisce la stazione presente alla riga specificata.
     *
     * @param row Numero della riga (a partire da 1).
     * @return La stazione alla riga indicata.
     */
    public String getRow(int row){
        if(row <= 0 || row > Main.info.size()) {
            return "Stazione non esistente";
        }
        return Main.info.get(row - 1).toString();
    }

    /**
     * Restituisce tutte le stazioni appartenenti a un determinato comune.
     *
     * @param municipality Il nome del comune.
     * @return Una lista di stazioni presenti nel comune specificato.
     */
    public ArrayList<String> getMunicipality(String municipality){
        ArrayList<String> stazioni = new ArrayList<>();
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getComune().equalsIgnoreCase(municipality)) stazioni.add(Main.info.get(i).toString());
        }
        if(stazioni.size() <= 0) {
            stazioni.add("Stazione non trovata nel comune indicato");
        }
        return stazioni;
    }

    /**
     * Restituisce una stazione in base al nome.
     *
     * @param name Il nome esatto della stazione.
     * @return La stazione corrispondente al nome.
     */
    public String getName(String name){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getNome().equalsIgnoreCase(name)) return Main.info.get(i).toString();
        }
        return ("Stazione con il nome indicato non esistente");
    }

    /**
     * Restituisce tutte le stazioni inserite in un determinato anno.
     *
     * @param year L'anno di inserimento da cercare.
     * @return Una lista di stazioni inserite nell'anno specificato.
     */
    public ArrayList<String> getYear(String year){
        ArrayList<String> stazioni = new ArrayList<>();
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getAnnoInserimento().equalsIgnoreCase(year)) stazioni.add(Main.info.get(i).toString());
        }
        if(stazioni.size() <= 0) stazioni.add("Stazione non trovata per l'anno indicato");
        return stazioni;
    }

    /**
     * Restituisce la stazione in base alle coordinate geografiche.
     *
     * @param longitudine La longitudine della stazione.
     * @param latitudine La latitudine della stazione.
     * @return La stazione corrispondente alle coordinate specificate.
     */
    public String getCoordinate(String longitudine, String latitudine){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getLatitudine().equalsIgnoreCase(latitudine) && Main.info.get(i).getLongitudine().equalsIgnoreCase(longitudine)) return Main.info.get(i).toString();
        }
        return ("Stazione con le coordinate indicate non esistente");
    }

    /**
     * Restituisce la stazione corrispondente all'identificatore OpenStreetMap.
     *
     * @param indicator L'identificatore OpenStreetMap.
     * @return La stazione corrispondente all'indicatore.
     */
    public String getIndicator(String indicator){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getIdentificatoreInOpenStreetMap().equalsIgnoreCase(indicator)) return Main.info.get(i).toString();
        }
        return ("Stazione con l'indicatore indicato non esistente");
    }
}

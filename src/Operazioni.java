import java.util.ArrayList;

public class Operazioni {

    public Operazioni() {
    }

    public Stazione getRow(int row){
        if(row <= 0 || row > Main.info.size()) throw new RigaNonEsistenteException("Riga non presente nel file csv");
        return Main.info.get(row - 1);
    }

    public ArrayList<Stazione> getMunicipality(String municipality){
        ArrayList<Stazione> stazioni = new ArrayList<>();
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getComune().equalsIgnoreCase(municipality)) stazioni.add(Main.info.get(i));
        }
        if(stazioni.size() <= 0) throw new StazioneNonTrovataException("Stazione non trovata nel comune indicato");
        return stazioni;
    }

    public Stazione getName(String name){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getNome().equalsIgnoreCase(name)) return Main.info.get(i);
        }
        throw new StazioneNonTrovataException("Stazione con il nome indicato non esistente");
    }

    public ArrayList<Stazione> getYear(String year){
        ArrayList<Stazione> stazioni = new ArrayList<>();
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getAnnoInserimento().equalsIgnoreCase(year)) stazioni.add(Main.info.get(i));
        }
        if(stazioni.size() <= 0) throw new StazioneNonTrovataException("Stazione non trovata per l'anno indicato");
        return stazioni;
    }

    public Stazione getCoordinate(String longitudine, String latitudine){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getLatitudine().equalsIgnoreCase(latitudine) && Main.info.get(i).getLongitudine().equalsIgnoreCase(longitudine)) return Main.info.get(i);
        }
        throw new StazioneNonTrovataException("Stazione con il nome indicato non esistente");
    }

    public Stazione getIndicator(String indicator){
        for (int i = 0; i < Main.info.size(); ++i){
            if(Main.info.get(i).getIdentificatoreInOpenStreetMap().equalsIgnoreCase(indicator)) return Main.info.get(i);
        }
        throw new StazioneNonTrovataException("Stazione con il nome indicato non esistente");
    }
}
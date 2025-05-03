/**
 * La classe rappresenta una stazione ferroviaria in Italia.
 * I dati provengono da un file CSV contenente informazioni sulle stazioni.
 */
public class Stazione {
    private String comune;
    private String provincia;
    private String regione;
    private String nome;
    private String annoInserimento;
    private String dataEOraInserimento;
    private String identificatoreInOpenStreetMap;
    private String longitudine;
    private String latitudine;

    /**
     * Costruttore per creare una nuova istanza di Stazione.
     *
     * @param comune Il comune in cui si trova la stazione.
     * @param provincia La provincia in cui si trova la stazione.
     * @param regione La regione in cui si trova la stazione.
     * @param nome Il nome della stazione.
     * @param annoInserimento L'anno in cui la stazione è stata inserita nel dataset.
     * @param dataEOraInserimento La data e ora di inserimento della stazione nel dataset.
     * @param identificatoreInOpenStreetMap L'identificatore univoco della stazione su OpenStreetMap.
     * @param longitudine La longitudine della stazione.
     * @param latitudine La latitudine della stazione.
     */
    public Stazione(String comune, String provincia, String regione, String nome, String annoInserimento,
                    String dataEOraInserimento, String identificatoreInOpenStreetMap, String longitudine, String latitudine) {
        this.comune = comune;
        this.provincia = provincia;
        this.regione = regione;
        this.nome = nome;
        this.annoInserimento = annoInserimento;
        this.dataEOraInserimento = dataEOraInserimento;
        this.identificatoreInOpenStreetMap = identificatoreInOpenStreetMap;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    /** @return Il comune della stazione. */
    public String getComune() {
        return comune;
    }

    /** @param comune Il comune da impostare. */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /** @return La provincia della stazione. */
    public String getProvincia() {
        return provincia;
    }

    /** @param provincia La provincia da impostare. */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /** @return La regione della stazione. */
    public String getRegione() {
        return regione;
    }

    /** @param regione La regione da impostare. */
    public void setRegione(String regione) {
        this.regione = regione;
    }

    /** @return Il nome della stazione. */
    public String getNome() {
        return nome;
    }

    /** @param nome Il nome da impostare. */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return L'anno di inserimento della stazione. */
    public String getAnnoInserimento() {
        return annoInserimento;
    }

    /** @param annoInserimento L'anno di inserimento da impostare. */
    public void setAnnoInserimento(String annoInserimento) {
        this.annoInserimento = annoInserimento;
    }

    /** @return La data e ora di inserimento della stazione. */
    public String getDataEOraInserimento() {
        return dataEOraInserimento;
    }

    /** @param dataEOraInserimento La data e ora da impostare. */
    public void setDataEOraInserimento(String dataEOraInserimento) {
        this.dataEOraInserimento = dataEOraInserimento;
    }

    /** @return L'identificatore su OpenStreetMap. */
    public String getIdentificatoreInOpenStreetMap() {
        return identificatoreInOpenStreetMap;
    }

    /** @param identificatoreInOpenStreetMap L'identificatore da impostare. */
    public void setIdentificatoreInOpenStreetMap(String identificatoreInOpenStreetMap) {
        this.identificatoreInOpenStreetMap = identificatoreInOpenStreetMap;
    }

    /** @return La longitudine della stazione. */
    public String getLongitudine() {
        return longitudine;
    }

    /** @param longitudine La longitudine da impostare. */
    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    /** @return La latitudine della stazione. */
    public String getLatitudine() {
        return latitudine;
    }

    /** @param latitudine La latitudine da impostare. */
    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Stazione.
     *
     * @return una stringa contenente tutte le informazioni della stazione.
     */
    @Override
    public String toString() {
        return "STAZIONE: " +
                "comune='" + comune + "\n" +
                "provincia='" + provincia + "\n" +
                "regione='" + regione + "\n" +
                "nome='" + nome + "\n" +
                "annoInserimento='" + annoInserimento + "\n" +
                "dataEOraInserimento='" + dataEOraInserimento + "\n" +
                "identificatoreInOpenStreetMap='" + identificatoreInOpenStreetMap + "\n" +
                "longitudine='" + longitudine + "\n" +
                "latitudine='" + latitudine + "\n";
    }
}

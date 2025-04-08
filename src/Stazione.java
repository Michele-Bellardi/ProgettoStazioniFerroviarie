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

    public Stazione(String comune, String provincia, String regione, String nome, String annoInserimento, String dataEOraInserimento, String identificatoreInOpenStreetMap, String longitudine, String latitudine) {
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

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnnoInserimento() {
        return annoInserimento;
    }

    public void setAnnoInserimento(String annoInserimento) {
        this.annoInserimento = annoInserimento;
    }

    public String getDataEOraInserimento() {
        return dataEOraInserimento;
    }

    public void setDataEOraInserimento(String dataEOraInserimento) {
        this.dataEOraInserimento = dataEOraInserimento;
    }

    public String getIdentificatoreInOpenStreetMap() {
        return identificatoreInOpenStreetMap;
    }

    public void setIdentificatoreInOpenStreetMap(String identificatoreInOpenStreetMap) {
        this.identificatoreInOpenStreetMap = identificatoreInOpenStreetMap;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    @Override
    public String toString() {
        return "Info{" +
                "comune='" + comune + '\'' +
                ", provincia='" + provincia + '\'' +
                ", regione='" + regione + '\'' +
                ", nome='" + nome + '\'' +
                ", annoInserimento='" + annoInserimento + '\'' +
                ", dataEOraInserimento='" + dataEOraInserimento + '\'' +
                ", identificatoreInOpenStreetMap='" + identificatoreInOpenStreetMap + '\'' +
                ", longitudine='" + longitudine + '\'' +
                ", latitudine='" + latitudine + '\'' +
                '}';
    }
}

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

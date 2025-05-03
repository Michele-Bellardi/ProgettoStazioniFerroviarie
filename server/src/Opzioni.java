/**
 * L'enum definisce le diverse operazioni disponibili che un client può richiedere al server.
 * Ogni opzione rappresenta un'azione specifica da eseguire sulle stazioni ferroviarie.
 */
public enum Opzioni {

    /** Ottieni una stazione in base al numero di riga nel file. */
    GET_ROW ("GET_ROW"),

    /** Ottieni tutte le stazioni appartenenti a un comune specifico. */
    GET_MUNICIPALITY ("GET_MUNICIPALITY"),

    /** Ottieni una stazione tramite il suo nome. */
    GET_NAME ("GET_NAME"),

    /** Ottieni tutte le stazioni inserite in un determinato anno. */
    GET_YEAR ("GET_YEAR"),

    /** Ottieni una stazione tramite le coordinate geografiche (longitudine, latitudine). */
    GET_COORDINATES ("GET_COORDINATES"),

    /** Ottieni una stazione tramite il suo identificatore OpenStreetMap. */
    GET_INDICATOR ("GET_INDICATOR");

    private final String nome;

    /**
     * Costruttore dell'enum Opzioni.
     *
     * @param nome Nome testuale dell'opzione.
     */
    Opzioni(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il nome dell'opzione.
     *
     * @return il nome dell'opzione come stringa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Converte una stringa in un valore dell'enum Opzioni.
     *
     * @param text la stringa da convertire.
     * @return il valore corrispondente dell'enum, oppure null se non esiste corrispondenza.
     */
    public static Opzioni fromString(String text) {
        for (Opzioni op : Opzioni.values()) {
            if (op.getNome().equalsIgnoreCase(text)) {
                return op;
            }
        }
        return null;
    }
}

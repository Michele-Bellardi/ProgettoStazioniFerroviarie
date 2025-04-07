public enum Opzioni {
    GET_ROW ("GET_ROW"),
    GET_MUNICIPALITY ("GET_MUNICIPALITY"),
    GET_NAME ("GET_NAME"),
    GET_YEAR ("GET_YEAR"),
    GET_COORDINATES ("GET_COORDINATES"),
    GET_INDICATOR ("GET_INDICATOR");

    private final String nome;

    Opzioni(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Opzioni fromString(String text) {
        for (Opzioni op : Opzioni.values()) {
            if (op.getNome().equalsIgnoreCase(text)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Nessuna opzione trovata per: " + text);
    }
}
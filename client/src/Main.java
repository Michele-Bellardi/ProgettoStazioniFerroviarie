//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public class ClientMaiuscolo {
        final static String nomeServer = "localhost";
        final static int portaServer = 4000;
        public static void main(String[] args) {
            System.out.println("Connessione al server in corso...");
            try (Socket sck = new Socket(nomeServer, portaServer)) {
                String rem = sck.getRemoteSocketAddress().toString();
                String loc = sck.getLocalSocketAddress().toString();
                System.out.format("Server (remoto): %s%n", rem);
                System.out.format("Client (client): %s%n", loc);
                comunica(sck);
            } catch (UnknownHostException e) {
                System.err.format("Nome di server non valido: %s%n", e.getMessage());  } catch (IOException e) {
                System.err.format("Errore durante la comunicazione con il server: %s%n",  e.getMessage());
            }
        }
        private static void comunica(Socket sck) throws IOException {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sck.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(sck.getOutputStream(), "UTF-8"), true);
            Scanner s = new Scanner(System.in, "UTF-8");
            System.out.print("Frase: ");
            String frase = s.nextLine();
            System.out.format("Invio al server: %s%n", frase);
            out.println(frase);
            System.out.println("In attesa di risposta dal server...");  String risposta = in.readLine();
            System.out.format("Risposta dal server: %s%n", risposta);
        }
    }

}
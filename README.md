# ProgettoStazioniFerroviarie
Progetto di sistemi e reti
OBBIETTIVO: realizzare un'applicazione Client-Server in linguaggio Java, che consenta agli utenti di consultare in remoto le informazioni di interesse relative a stazioni ferroviarie presenti in un file formato CSV.
Nome del file: Mappa-delle-stazioni-ferroviarie-in-Italia.csv

## Requisiti
* Un IDE come **IntelliJ IDEA**
* Una console per eseguire i comandi

## Installazione
1. Clonare la repository:
   ```sh
   git clone https://github.com/Michele-Bellardi/ProgettoStazioniFerroviarie.git
3. Aprire i progetti Server e Client separatamente nell'IDE

## Esecuzione
1. Avviare il Server
2. Avviare il Client

## Funzionamento
Il Server dispone del file CSV contenente le stazioni ferroviarie e le loro informazioni, legge il file e inserisce tutte le informazioni all’interno di un ArrayList.
Client e server comunicano scambiando stringhe.

## Funzionalità
Il Server mette dispone di funzionalità di ricerca di informazioni riguradanti stazioni ferroviarie all'interno di un file CSV. 
Il server riceve comandi dal client sottoforma di stringhe, e restituisce al client le informazioni richieste sottoforma di stringhe.

## Autore
Michele Bellardi

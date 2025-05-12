# Tic-Tac-Toe - Modulo Core

Questo modulo contiene la logica principale del gioco Tic-Tac-Toe, sia per la modalità Giocatore vs Giocatore che Giocatore vs Computer. Qui troverai la gestione dei giocatori, della tabella di gioco, dei colori per la visualizzazione e delle regole di vittoria.

## Indice

1. [Panoramica](#panoramica)
2. [Struttura delle cartelle e dei file](#struttura-delle-cartelle-e-dei-file)
3. [Descrizione dei file principali](#descrizione-dei-file-principali)
    - [ColorUtils.java](#colorutilsjava)
    - [TabellaGioco.java](#tabellagiocojava)
    - [GameStartManagement.java](#gamestartmanagementjava)
    - [PlaterVsPlayer/Players.java](#platervsplayerplayersjava)
    - [PlaterVsPlayer/GameStartPvsP.java](#platervsplayergamestartpvspjava)
    - [PlayersVsComputer/Players_PC.java](#playersvscomputerplayers_pcjava)
    - [PlayersVsComputer/GameStartPvsPc.java](#playersvscomputergamestartpvspcjava)
4. [Motivazioni delle scelte progettuali](#motivazioni-delle-scelte-progettuali)

---

## Panoramica

Il modulo `core` implementa la logica del gioco Tic-Tac-Toe, permettendo di giocare sia contro un altro giocatore sia contro il computer. Gestisce la creazione dei giocatori, la visualizzazione della tabella, la colorazione dei messaggi e il controllo delle condizioni di vittoria o pareggio.

## Struttura delle cartelle e dei file

- `ColorUtils.java`: Gestione dei colori e degli stili per la stampa in console.
- `TabellaGioco.java`: Gestione della tabella di gioco (stato, stampa, inserimento simboli).
- `GameStartManagement.java`: Gestione dell'avvio delle partite e del menu principale.
- `PlaterVsPlayer/`
    - `Players.java`: Gestione dei dati dei giocatori umani.
    - `GameStartPvsP.java`: Logica della partita tra due giocatori umani.
- `PlayersVsComputer/`
    - `Players_PC.java`: Gestione dei dati del giocatore umano e del computer.
    - `GameStartPvsPc.java`: Logica della partita tra giocatore e computer.

## Descrizione dei file principali

### ColorUtils.java

Fornisce costanti e metodi statici per colorare e stilizzare il testo in console. Permette di distinguere facilmente messaggi di errore, titoli, simboli dei giocatori e la tabella di gioco, migliorando l'esperienza utente.

### TabellaGioco.java

Gestisce la tabella 3x3 del gioco:
- Inizializza e resetta la tabella.
- Permette l'inserimento dei simboli (X/O) nelle celle.
- Controlla se la tabella è piena.
- Stampa la tabella colorata in console.

### GameStartManagement.java

Gestisce il menu principale e l'avvio delle partite:
- Permette di scegliere tra modalità Giocatore vs Giocatore e Giocatore vs Computer.
- Crea i giocatori e avvia la partita nella modalità scelta.
- Gestisce la visualizzazione delle informazioni dei giocatori e l'inizio della partita.

### PlaterVsPlayer/Players.java

Gestisce i dati dei giocatori umani:
- Nome e simbolo (X/O).
- Metodi per impostare e ottenere nome e simbolo.
- Gestione dell'inserimento dei dati da console.

### PlaterVsPlayer/GameStartPvsP.java

Contiene la logica della partita tra due giocatori umani:
- Gestione dei turni.
- Richiesta delle mosse ai giocatori.
- Controllo delle condizioni di vittoria o pareggio.
- Stampa del vincitore o del messaggio di pareggio.

### PlayersVsComputer/Players_PC.java

Gestisce i dati sia del giocatore umano che del computer:
- Nome e simbolo per entrambi.
- Gestione dell'inserimento dei dati da console per il giocatore umano.
- Assegnazione automatica del simbolo al computer.

### PlayersVsComputer/GameStartPvsPc.java

Contiene la logica della partita tra giocatore e computer:
- Gestione dei turni tra umano e computer.
- Il computer effettua mosse casuali sulle celle libere.
- Controllo delle condizioni di vittoria o pareggio.
- Stampa del vincitore o del messaggio di pareggio.

## Motivazioni delle scelte progettuali

- **Separazione delle modalità di gioco**: Le classi sono suddivise in base alla modalità (PvP e PvC) per mantenere il codice organizzato e facilmente estendibile.
- **Utilizzo di ColorUtils**: Migliora la leggibilità e l'usabilità dell'applicazione in console.
- **Gestione centralizzata della tabella**: La classe `TabellaGioco` permette di evitare duplicazioni e facilita la gestione dello stato del gioco.
- **Input utente robusto**: Sono previsti controlli per evitare inserimenti errati e per guidare l'utente durante la partita.

---

Per qualsiasi domanda o contributo, sentiti libero di aprire una issue o una pull request!
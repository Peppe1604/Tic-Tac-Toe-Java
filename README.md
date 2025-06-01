# 🎮 Tic-Tac-Toe - Modulo Core

Questo modulo rappresenta il **cuore pulsante** del gioco Tic-Tac-Toe, includendo tutta la logica necessaria per le modalità **Giocatore vs Giocatore** e **Giocatore vs Computer**. Qui trovi tutto: dalla gestione dei giocatori alla visualizzazione della tabella di gioco, con controlli di vittoria e pareggio inclusi.

---

## 📑 Indice

1. [📌 Panoramica](#-panoramica)
2. [📁 Struttura delle cartelle e dei file](#-struttura-delle-cartelle-e-dei-file)
3. [📄 Descrizione dei file principali](#-descrizione-dei-file-principali)
    - [🎨 ColorUtils.java](#-colorutilsjava)
    - [📊 TabellaGioco.java](#-tabellagiocojava)
    - [🚀 GameStartManagement.java](#-gamestartmanagementjava)
    - [👥 PlayerVsPlayer/Players.java](#-playervsplayerplayersjava)
    - [🆚 PlayerVsPlayer/GameStartPvsP.java](#-playervsplayergamestartpvspjava)
    - [🤖 PlayersVsComputer/Players_PC.java](#-playersvscomputerplayers_pcjava)
    - [🧠 PlayersVsComputer/GameStartPvsPc.java](#-playersvscomputergamestartpvspcjava)
4. [💡 Motivazioni delle scelte progettuali](#-motivazioni-delle-scelte-progettuali)

---

## 📌 Panoramica

Il modulo `core` implementa tutta la logica di gioco del Tic-Tac-Toe, permettendo partite:
- 👤 contro un altro giocatore umano
- 🤖 contro il computer

Comprende:
- Creazione e gestione dei giocatori
- Visualizzazione dinamica e colorata della tabella
- Controllo delle regole: vittoria, pareggio, turni

---

## 📁 Struttura delle cartelle e dei file

```
/core
├── ColorUtils.java                  🎨 Gestione dei colori per la console
├── TabellaGioco.java                📊 Tabella e stato del gioco
├── GameStartManagement.java         🚀 Gestione del menu e avvio partita
├── PlayerVsPlayer/
│   ├── Players.java                 👥 Dati e input dei giocatori umani
│   └── GameStartPvsP.java           🆚 Logica PvP (Player vs Player)
└── PlayersVsComputer/
    ├── Players_PC.java              👤🤖 Dati del giocatore e del computer
    └── GameStartPvsPc.java          🧠 Logica PvC (Player vs Computer)
```

---

## 📄 Descrizione dei file principali

### 🎨 ColorUtils.java

Gestisce colori e stili per migliorare l’interfaccia testuale:
- Differenzia messaggi (info, errori, successi)
- Colora simboli X e O
- Migliora l’esperienza visiva in console 🌈

---

### 📊 TabellaGioco.java

Gestisce la tabella 3x3:
- Inizializzazione/reset tabella
- Inserimento dei simboli (X/O)
- Verifica se la tabella è piena
- Stampa colorata e ordinata

---

### 🚀 GameStartManagement.java

Controlla l’avvio del gioco:
- Mostra menu iniziale 📋
- Consente scelta modalità (PvP o PvC)
- Crea i giocatori
- Avvia la modalità selezionata

---

### 👥 PlayerVsPlayer/Players.java

Gestisce i **giocatori umani**:
- Nome e simbolo
- Input da console
- Metodi per lettura/scrittura delle informazioni

---

### 🆚 PlayerVsPlayer/GameStartPvsP.java

Logica per la modalità **Player vs Player**:
- Turni alternati
- Richiesta mossa da console
- Verifica vittoria o pareggio
- Stampa risultato finale

---

### 🤖 PlayersVsComputer/Players_PC.java

Gestisce i dati del giocatore **e del computer**:
- Inserimento del nome del giocatore
- Assegnazione automatica simboli
- Preparazione al gioco

---

### 🧠 PlayersVsComputer/GameStartPvsPc.java

Logica della modalità **Player vs Computer**:
- Turni alternati tra umano e AI
- L’AI effettua **mosse casuali intelligenti**
- Controllo condizioni di vittoria/pareggio
- Output del risultato

---

## 💡 Motivazioni delle scelte progettuali

- 🎯 **Modularità**: separazione delle modalità PvP e PvC per semplificare la manutenzione.
- 🌈 **ColorUtils**: migliora la leggibilità in console.
- 🧩 **Tabella centralizzata**: evita duplicazioni, gestisce lo stato del gioco in modo chiaro.
- 🛡️ **Input robusto**: l’utente viene guidato e protetto da errori di inserimento.
- 🔄 **Facilità di estensione**: l’organizzazione dei file semplifica l’aggiunta di nuove funzionalità future.

---

📬 *Per segnalazioni, suggerimenti o collaborazioni, apri una issue o invia una pull request!*

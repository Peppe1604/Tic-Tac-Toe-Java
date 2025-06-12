# 🎮 TIC-TAC-TOE ULTIMATE - Neon Edition

Un'implementazione moderna e accattivante del classico gioco del Tris con interfaccia grafica futuristica e modalità console tradizionale.

![Version](https://img.shields.io/badge/version-2.0-neon)
![Java](https://img.shields.io/badge/Java-11+-orange)
![GUI](https://img.shields.io/badge/GUI-Swing-cyan)

---

## ✨ Caratteristiche Principali

### 🌟 Interfaccia Grafica Ultra-Moderna
- **Design Neon Futuristico**: Tema dark con effetti luminosi al neon
- **Effetti Glassmorphism**: Trasparenze e sfocature per un look contemporaneo
- **Animazioni Fluide**: Transizioni morbide e effetti particellari
- **Responsive Design**: Si adatta a diverse dimensioni dello schermo

### 🎯 Modalità di Gioco
- **Player vs Player**: Sfida un amico locale
- **Player vs AI**: Gioca contro un'intelligenza artificiale
- **Modalità Console**: Versione classica a linea di comando

### 🎨 Effetti Visivi
- **Particelle Animate**: Sfondo dinamico con particelle fluttuanti
- **Effetti Neon**: Simboli X e O con bagliore luminoso
- **Animazioni di Vittoria**: Celebrazioni colorate quando qualcuno vince
- **Highlight dei Turni**: Indicatore visivo pulsante del giocatore corrente

---

## 📁 Struttura del Progetto

```
/TicTacToe-Ultimate
│
├── Main.java                    🚀 Entry point (GUI/Console)
├── README.md                    📖 Documentazione
│
├── /core                        💼 Logica di gioco
│   ├── ColorUtils.java          🎨 Colori per console
│   ├── TabellaGioco.java        📊 Gestione tabella
│   ├── GameStartManagement.java 🎮 Manager partite
│   ├── /PlayerVsPlayer          👥 Modalità PvP
│   └── /PlayersVsComputer       🤖 Modalità PvC
│
└── /gui                         🖼️ Interfaccia grafica
    └── TrisGUI.java            ✨ GUI Neon moderna
```

---

## 🚀 Come Iniziare

### Prerequisiti
- Java 11 o superiore
- JDK installato nel sistema

### Compilazione
```bash
javac Main.java gui/TrisGUI.java core/*.java core/PlayerVsPlayer/*.java core/PlayersVsComputer/*.java
```

### Esecuzione

**Modalità GUI (default):**
```bash
java Main
```

**Modalità Console:**
```bash
java Main --console
```

---

## 🎮 Come Giocare

### Interfaccia Grafica
1. **Menu Principale**: Scegli tra Player vs Player o Player vs AI
2. **Inserisci i Nomi**: Personalizza i nomi dei giocatori
3. **Fai le Mosse**: Clicca sulle caselle per piazzare X o O
4. **Vinci o Pareggia**: Allinea 3 simboli uguali per vincere!

### Controlli
- **Click**: Piazza il tuo simbolo
- **New Game**: Ricomincia la partita
- **Menu**: Torna al menu principale
- **Exit**: Chiudi il gioco con animazione

---

## 🎨 Caratteristiche Tecniche GUI

### Design Elements
- **Glassmorphism**: Effetti di trasparenza e blur
- **Neon Glow**: Bagliori colorati dinamici
- **Dark Theme**: Sfondo scuro per ridurre l'affaticamento visivo
- **Particle System**: Sistema di particelle per lo sfondo animato

### Animazioni
- **Fade In/Out**: Transizioni morbide tra schermate
- **Pulse Effects**: Effetti pulsanti per evidenziare elementi
- **Move Animations**: Animazioni quando si piazzano i simboli
- **Victory Celebration**: Effetti speciali per il vincitore

### AI Features
- **Smart Moves**: L'AI cerca di vincere o bloccare
- **Strategic Play**: Priorità a centro e angoli
- **Realistic Delay**: Pausa pensiero per simulare riflessione

---

## 🛠️ Architettura

### Core Module
Gestisce tutta la logica di gioco, indipendente dall'interfaccia:
- Validazione mosse
- Controllo vittorie
- Gestione turni
- Stato della partita

### GUI Module
Interfaccia grafica moderna e reattiva:
- Event-driven architecture
- Swing components personalizzati
- Timer-based animations
- Custom painting per effetti

---

## 🔧 Personalizzazione

### Modifica Colori Neon
Nel file `TrisGUI.java`, puoi modificare i colori principali:
```java
private final Color primaryNeon = new Color(0, 255, 255);   // Cyan
private final Color secondaryNeon = new Color(255, 0, 255);  // Magenta
private final Color accentNeon = new Color(255, 255, 0);     // Yellow
```

### Velocità Animazioni
Modifica i timer per animazioni più veloci/lente:
```java
Timer animTimer = new Timer(30, e -> {...});  // 30ms = ~33 FPS
```

---

## 📈 Miglioramenti Futuri

- [ ] **Multiplayer Online**: Gioca con amici remoti
- [ ] **Livelli di Difficoltà AI**: Easy, Medium, Hard, Impossible
- [ ] **Temi Personalizzabili**: Scegli tra diversi stili visivi
- [ ] **Effetti Sonori**: Audio feedback per le azioni
- [ ] **Statistiche**: Traccia vittorie e sconfitte
- [ ] **Tornei**: Modalità torneo per più giocatori
- [ ] **Salvataggio Partite**: Salva e riprendi le partite

---

## 🤝 Contributi

Sentiti libero di contribuire al progetto! 
1. Fork il repository
2. Crea un branch per la tua feature
3. Commit le modifiche
4. Push al branch
5. Apri una Pull Request

---

## 📝 Note Tecniche

- **No dipendenze esterne**: Usa solo Java Swing standard
- **Cross-platform**: Funziona su Windows, Mac, Linux
- **Leggero**: Basso consumo di risorse
- **Scalabile**: Architettura modulare facilmente estendibile

---

## 🎯 Screenshots

### Menu Principale
- Design neon futuristico
- Bottoni con effetto glow
- Particelle animate di sfondo

### Gameplay
- Griglia glassmorphism
- Simboli con effetto neon
- Indicatori di turno animati

### Vittoria
- Animazione celebrativa
- Highlight della linea vincente
- Effetti colorati

---

## 📬 Contatti

Per segnalazioni bug, suggerimenti o collaborazioni, apri una issue su GitHub!

---

*Creato con ❤️ e tanto codice Java*
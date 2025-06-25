# 🎮 TIC-TAC-TOE ULTIMATE - Neon Edition

A modern and captivating implementation of the classic Tic-Tac-Toe game with futuristic graphic interface and traditional console mode.

![Version](https://img.shields.io/badge/version-2.0-neon)
![Java](https://img.shields.io/badge/Java-11+-orange)
![GUI](https://img.shields.io/badge/GUI-Swing-cyan)

---

## ✨ Key Features

### 🌟 Ultra-Modern Graphic Interface
- **Futuristic Neon Design**: Dark theme with neon glowing effects
- **Glassmorphism Effects**: Transparency and blur for a contemporary look
- **Smooth Animations**: Soft transitions and particle effects
- **Responsive Design**: Adapts to different screen sizes

### 🎯 Game Modes
- **Player vs Player**: Challenge a local friend
- **Player vs AI**: Play against artificial intelligence
- **Console Mode**: Classic command-line version

### 🎨 Visual Effects
- **Animated Particles**: Dynamic background with floating particles
- **Neon Effects**: X and O symbols with luminous glow
- **Victory Animations**: Colorful celebrations when someone wins
- **Turn Highlights**: Pulsing visual indicator of current player

---

## 📁 Project Structure

```
/TicTacToe-Ultimate
│
├── Main.java                    🚀 Entry point (GUI/Console)
├── README.md                    📖 Documentation
│
├── /core                        💼 Game logic
│   ├── ColorUtils.java          🎨 Console colors
│   ├── TabellaGioco.java        📊 Board management
│   ├── GameStartManagement.java 🎮 Game manager
│   ├── /PlayerVsPlayer          👥 PvP mode
│   └── /PlayersVsComputer       🤖 PvC mode
│
└── /gui                         🖼️ Graphic interface
    └── TrisGUI.java            ✨ Modern Neon GUI
```

---

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- JDK installed on the system

### Compilation
```bash
javac Main.java gui/TrisGUI.java core/*.java core/PlayerVsPlayer/*.java core/PlayersVsComputer/*.java
```

### Execution

**GUI Mode (default):**
```bash
java Main
```

**Console Mode:**
```bash
java Main --console
```

---

## 🎮 How to Play

### Graphic Interface
1. **Main Menu**: Choose between Player vs Player or Player vs AI
2. **Enter Names**: Customize player names
3. **Make Moves**: Click on squares to place X or O
4. **Win or Draw**: Align 3 identical symbols to win!

### Controls
- **Click**: Place your symbol
- **New Game**: Restart the match
- **Menu**: Return to main menu
- **Exit**: Close the game with animation

---

## 🎨 GUI Technical Features

### Design Elements
- **Glassmorphism**: Transparency and blur effects
- **Neon Glow**: Dynamic colored glows
- **Dark Theme**: Dark background to reduce eye strain
- **Particle System**: Particle system for animated background

### Animations
- **Fade In/Out**: Smooth transitions between screens
- **Pulse Effects**: Pulsing effects to highlight elements
- **Move Animations**: Animations when placing symbols
- **Victory Celebration**: Special effects for the winner

### AI Features
- **Smart Moves**: AI tries to win or block
- **Strategic Play**: Priority to center and corners
- **Realistic Delay**: Thinking pause to simulate reflection

---

## 🛠️ Architecture

### Core Module
Manages all game logic, independent of interface:
- Move validation
- Victory checking
- Turn management
- Game state

### GUI Module
Modern and reactive graphic interface:
- Event-driven architecture
- Custom Swing components
- Timer-based animations
- Custom painting for effects

---

## 🔧 Customization

### Modify Neon Colors
In the `TrisGUI.java` file, you can modify the main colors:
```java
private final Color primaryNeon = new Color(0, 255, 255);   // Cyan
private final Color secondaryNeon = new Color(255, 0, 255);  // Magenta
private final Color accentNeon = new Color(255, 255, 0);     // Yellow
```

### Animation Speed
Modify timers for faster/slower animations:
```java
Timer animTimer = new Timer(30, e -> {...});  // 30ms = ~33 FPS
```

---

## 📈 Future Improvements

- [ ] **Online Multiplayer**: Play with remote friends
- [ ] **AI Difficulty Levels**: Easy, Medium, Hard, Impossible
- [ ] **Customizable Themes**: Choose between different visual styles
- [ ] **Sound Effects**: Audio feedback for actions
- [ ] **Statistics**: Track wins and losses
- [ ] **Tournaments**: Tournament mode for multiple players
- [ ] **Game Saving**: Save and resume games

---

## 🤝 Contributing

Feel free to contribute to the project! 
1. Fork the repository
2. Create a branch for your feature
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

---

## 📝 Technical Notes

- **No external dependencies**: Uses only standard Java Swing
- **Cross-platform**: Works on Windows, Mac, Linux
- **Lightweight**: Low resource consumption
- **Scalable**: Modular architecture easily extensible

---

## 🎯 Screenshots

### Main Menu
- Futuristic neon design
- Buttons with glow effect
- Animated background particles

### Gameplay
- Glassmorphism grid
- Symbols with neon effect
- Animated turn indicators

### Victory
- Celebratory animation
- Winning line highlight
- Colorful effects

---

## 📬 Contact

For bug reports, suggestions or collaborations, open an issue on GitHub!

---

*Created with ❤️ and lots of Java code*

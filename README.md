# Tic-Tac-Toe Java

Java project developed to practice **object-oriented programming, game logic and modular application design**.

The project includes both a console version and a Swing graphical interface, with
Player vs Player and Player vs Computer modes.

## What I practiced

- Object-oriented programming
- Encapsulation
- Classes and interfaces
- Separation between game logic and presentation
- Event-driven programming with Swing
- Modular Java project organization

## AI-assisted development

The core Java programming and game logic were developed as a learning exercise.

AI tools were mainly used later to reorganize and improve the graphical/interface part of the project,
including visual styling and animations.

## Features

- Player vs Player
- Player vs Computer
- Console mode
- Swing GUI
- Move validation
- Victory and draw detection
- Turn management
- Restart / new game flow

## Project structure

```text
TicTacToe-Ultimate/
├── Main.java
├── core/
│   ├── ColorUtils.java
│   ├── TabellaGioco.java
│   ├── GameStartManagement.java
│   ├── PlayerVsPlayer/
│   └── PlayersVsComputer/
└── gui/
    └── TrisGUI.java
```

The `core` package contains the game logic, while `gui` contains the Swing interface.

## Requirements

- Java 11 or newer
- JDK installed

## Compile

```bash
javac Main.java gui/TrisGUI.java core/*.java core/PlayerVsPlayer/*.java core/PlayersVsComputer/*.java
```

## Run

GUI mode:

```bash
java Main
```

Console mode:

```bash
java Main --console
```

## Architecture

### Core

Responsible for:

- board state;
- move validation;
- turn management;
- victory checking;
- computer-player logic.

### GUI

Built with Java Swing and responsible for:

- user interaction;
- visual rendering;
- menu/game screens;
- interface animations.

## Possible improvements

- Multiple AI difficulty levels
- Match statistics
- Save / resume
- Online multiplayer
- Automated tests for the game logic

## Feedback

For bugs or suggestions, open an issue on GitHub.

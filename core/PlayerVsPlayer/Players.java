package core.PlaterVsPlayer;

import static java.lang.System.*;

public class Players {
    private String name;
    private char symbol;
    private static int contGiocatori = 1;  // Imposto la variabile come statica, così è condivisa tra tutti i giocatori

    public Players(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void SetPlayers(Players player1) {
        // Assegno il nome e simbolo al giocatore
        System.out.println("Inserisci il nome del giocatore " + contGiocatori + ": ");
        String name1 = System.console().readLine();
        player1.setName(name1.toUpperCase());

        System.out.println("Inserisci il simbolo del giocatore " + contGiocatori + " (X o O): ");
        String input = System.console().readLine().toUpperCase();
        
        if (input.length() != 1 || (input.charAt(0) != 'X' && input.charAt(0) != 'O')) {
            System.out.println("Simbolo non valido! Riprova.");
            return;
        }

        char symbol1 = input.charAt(0);
        player1.setSymbol(symbol1);

        // Incremento il contatore solo quando sono entrambi i giocatori settati
        if (contGiocatori == 1) {
            contGiocatori++;  // Incrementa per il secondo giocatore
        }

        System.out.println("I giocatori sono stati impostati con successo!");
    }

    public static int getContGiocatori() {
        return contGiocatori;
    }
}

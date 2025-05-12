package core.PlayersVsComputer;
   import java.util.Scanner;

public class Players_PC {
    private String name;
    private char symbol;
    private char simboloComputer;

    public Players_PC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public char getSimboloComputer() {
        return simboloComputer;
    }

    public void setSymbol(char symbol) {    
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

public void setPlayers(Players_PC player1) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Inserisci il nome del giocatore: ");
    String name1 = scanner.nextLine();
    player1.setName(name1.toUpperCase());

    System.out.println("Inserisci il simbolo del giocatore (X o O): ");
    String input = scanner.nextLine().toUpperCase();
    
    if (input.length() != 1 || (input.charAt(0) != 'X' && input.charAt(0) != 'O')) {
        System.out.println("Simbolo non valido! Impostato X di default.");
        player1.setSymbol('X');
    } else {
        player1.setSymbol(input.charAt(0));
    }
    
    // Non chiudere lo scanner qui
}

}

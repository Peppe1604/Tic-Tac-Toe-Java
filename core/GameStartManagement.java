package core;
import core.PlayerVsPlayer.GameStartPvsP;
import core.PlayerVsPlayer.Players;
import core.PlayersVsComputer.GameStartPvsPc;
import core.PlayersVsComputer.Players_PC;

public class GameStartManagement {
    public void GiocatoreVsGiocatore(String[] args) {
        System.out.println(ColorUtils.coloraTitolo("Giocatore vs Giocatore"));

        Players player1 = new Players("Giocatore 1");
        Players player2 = new Players("Giocatore 2"); 
        player1.SetPlayers(player1);
        player2.SetPlayers(player2);
        do {
            System.out.println(ColorUtils.coloraMenu("1: Stampa il nome del primo giocatore e il suo simbolo"));
            System.out.println(ColorUtils.coloraMenu("2: Stampa il nome del secondo giocatore e il suo simbolo"));
            System.out.println(ColorUtils.coloraMenu("3: Inizia la partita"));
            System.out.println(ColorUtils.coloraMenu("4: Esci dal gioco"));
            System.out.print(ColorUtils.coloraMenu("Scegli un'opzione: "));

            int scelta = Integer.parseInt(System.console().readLine());
            switch (scelta) {
                case 1:
                    System.out.println("Nome del primo giocatore: " + player1.getName() + ", Simbolo: " + 
                        (player1.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 2:
                    System.out.println("Nome del secondo giocatore: " + player2.getName() + ", Simbolo: " + 
                        (player2.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 3:
                    System.out.println(ColorUtils.coloraTitolo("Inizia la partita!"));
                    GameStartPvsP game = new GameStartPvsP();
                    game.InizioDelGioco(player1, player2);
                    break;
                case 4:
                    System.out.println("Uscita dal gioco...");
                    return;
                default:
                    System.out.println(ColorUtils.coloraErrore("Scelta non valida. Riprova."));
            }
        } while (true);
    }

    public void GiocatoreVsComputer(String[] args) {
        System.out.println(ColorUtils.coloraTitolo("Giocatore vs Computer"));

        Players_PC player1 = new Players_PC("Giocatore 1");
        Players_PC player2 = new Players_PC("Computer");
        player1.setPlayers(player1);

        // Assegna simbolo automatico al computer
        char simboloGiocatore = player1.getSymbol();
        char simboloComputer = (simboloGiocatore == 'X') ? 'O' : 'X';
        player2.setSymbol(simboloComputer);

        do {
            System.out.println(ColorUtils.coloraMenu("1: Stampa il nome del primo giocatore e il suo simbolo"));
            System.out.println(ColorUtils.coloraMenu("2: Stampa il nome del secondo giocatore (Computer) e il suo simbolo"));
            System.out.println(ColorUtils.coloraMenu("3: Inizia la partita"));
            System.out.println(ColorUtils.coloraMenu("4: Esci dal gioco"));
            System.out.print(ColorUtils.coloraMenu("Scegli un'opzione: "));

            int scelta = Integer.parseInt(System.console().readLine());
            switch (scelta) {
                case 1:
                    System.out.println("Nome del primo giocatore: " + player1.getName() + ", Simbolo: " + 
                        (player1.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 2:
                    System.out.println("Nome del secondo giocatore: " + player2.getName() + ", Simbolo: " + 
                        (player2.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 3:
                    System.out.println(ColorUtils.coloraTitolo("Inizia la partita!"));
                    GameStartPvsPc game = new GameStartPvsPc();
                    game.InizioDelGioco(player1, player2);
                    break;
                case 4:
                    System.out.println("Uscita dal gioco...");
                    return;
                default:
                    System.out.println(ColorUtils.coloraErrore("Scelta non valida. Riprova."));
            }
        } while (true);
    }
}

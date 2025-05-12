package core.PlayersVsComputer;

import java.util.Scanner;

import core.ColorUtils;
import core.TabellaGioco;

public class GameStartPvsPc {
    public void InizioDelGioco(Players_PC player1, Players_PC player2) {
        System.out.println(ColorUtils.coloraTitolo("Inizio del gioco tra " + player1.getName() + " e " + player2.getName()));
        System.out.println("Simbolo di " + player1.getName() + ": " + 
            (player1.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
        System.out.println("Simbolo di " + player2.getName() + ": " + 
            (player2.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
        System.out.println(ColorUtils.coloraTitolo("Inizia la partita!"));

        TabellaGioco tabella = new TabellaGioco();
        tabella.stampaTabella();
        boolean turno = true; // true per player1, false per player2
        while (true) {
            if (turno) {
                System.out.println(ColorUtils.VERDE + player1.getName() + ", è il tuo turno!" + ColorUtils.RESET);
                MossaGiocatore(player1, tabella);
                if (ControlloVittoria(tabella, player1.getSymbol())) {
                    StampaVincitore(player1);
                    break;
                } 
            } else {
                MossaGiocatoreBOT(player2, tabella);
                if (ControlloVittoria(tabella, player2.getSymbol())) {
                    StampaVincitore(player2);
                    break;
                }
            }

            if (tabella.tabellaPiena()) {
                System.out.println(ColorUtils.GIALLO + "La partita è finita in pareggio!" + ColorUtils.RESET);
                break;
            }
            
            turno = !turno;
        }
    }
    
    public static void MossaGiocatore(Players_PC player, TabellaGioco tabella) {
        Scanner scanner = new Scanner(System.in);
        boolean mossaEseguita = false;
    
        while (!mossaEseguita) {
            System.out.println(ColorUtils.coloraMenu("1: Stampa la tabella di gioco"));
            System.out.println(ColorUtils.coloraMenu("2: Mostra info giocatore"));
            System.out.println(ColorUtils.coloraMenu("3: Fai la tua mossa"));
            System.out.print(ColorUtils.coloraMenu("Scegli un'opzione: "));
            int scelta = scanner.nextInt();
    
            switch (scelta) {
                case 1:
                    tabella.stampaTabella();
                    break;
                case 2:
                    System.out.println("Nome: " + player.getName() + ", Simbolo: " + 
                        (player.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 3:
                    System.out.print(ColorUtils.VERDE + "Inserisci il numero di riga (0-2): " + ColorUtils.RESET);
                    int riga = scanner.nextInt();
                    System.out.print(ColorUtils.VERDE + "Inserisci il numero di colonna (0-2): " + ColorUtils.RESET);
                    int colonna = scanner.nextInt();
    
                    if (tabella.inserisciSimbolo(riga, colonna, player.getSymbol())) {
                        tabella.stampaTabella();
                        mossaEseguita = true;
                    } else {
                        System.out.println(ColorUtils.coloraErrore("Mossa non valida. Casella già occupata o coordinate errate."));
                    }
                    break;
                default:
                    System.out.println(ColorUtils.coloraErrore("Scelta non valida."));
            }
        }
        // Rimuovi la chiusura dello scanner qui
    }

    public static void MossaGiocatoreBOT(Players_PC player, TabellaGioco tabella) {
        System.out.println(ColorUtils.BLU + player.getName() + ", è il turno del computer!" + ColorUtils.RESET);
        int riga, colonna;
        do {
            riga = (int) (Math.random() * 3);
            colonna = (int) (Math.random() * 3);
        } while (!tabella.inserisciSimbolo(riga, colonna, player.getSymbol()));
        tabella.stampaTabella();
    }

    public static boolean ControlloVittoria(TabellaGioco tabella, char simbolo) {
        String simboloStr = String.valueOf(simbolo);
        
        // Controlla righe
        for (int i = 0; i < 3; i++) {
            if (tabella.getTabella()[i][0].equals(simboloStr) && 
                tabella.getTabella()[i][1].equals(simboloStr) && 
                tabella.getTabella()[i][2].equals(simboloStr)) {
                return true;
            }
        }
        
        // Controlla colonne
        for (int i = 0; i < 3; i++) {
            if (tabella.getTabella()[0][i].equals(simboloStr) && 
                tabella.getTabella()[1][i].equals(simboloStr) && 
                tabella.getTabella()[2][i].equals(simboloStr)) {
                return true;
            }
        }
        
        // Controlla diagonali
        if (tabella.getTabella()[0][0].equals(simboloStr) && 
            tabella.getTabella()[1][1].equals(simboloStr) && 
            tabella.getTabella()[2][2].equals(simboloStr)) {
            return true;
        }
        
        if (tabella.getTabella()[0][2].equals(simboloStr) && 
            tabella.getTabella()[1][1].equals(simboloStr) && 
            tabella.getTabella()[2][0].equals(simboloStr)) {
            return true;
        }
        
        return false;
    }

    public void StampaVincitore(Players_PC player) {
        System.out.println(ColorUtils.coloraVincitore("Il vincitore è: " + player.getName() + " con simbolo " + 
            (player.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O"))));
    }
}



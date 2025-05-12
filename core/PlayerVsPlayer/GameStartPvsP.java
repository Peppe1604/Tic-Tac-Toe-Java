package core.PlaterVsPlayer;

import core.ColorUtils;
import core.TabellaGioco;

public class GameStartPvsP {

    public void InizioDelGioco(Players player1, Players player2) {
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
                System.out.println(ColorUtils.VERDE + player2.getName() + ", è il tuo turno!" + ColorUtils.RESET);
                MossaGiocatore(player2, tabella);
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

    public static void MossaGiocatore(Players player, TabellaGioco tabella) {
        boolean mossaValida = false;
        do{
            System.out.println(ColorUtils.coloraMenu("1: stampa la tabella di gioco"));
            System.out.println(ColorUtils.coloraMenu("2: stampa il nome del giocatore e il suo simbolo"));
            System.out.println(ColorUtils.coloraMenu("3: Fai la tua mossa"));
            System.out.print(ColorUtils.coloraMenu("Scegli un opzione: "));
            int scelta = Integer.parseInt(System.console().readLine());

            switch (scelta) {
                case 1:
                    tabella.stampaTabella();
                    break;
                case 2:
                    System.out.println("Nome del giocatore: " + player.getName() + ", Simbolo: " + 
                        (player.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O")));
                    break;
                case 3:       
                    System.out.println(ColorUtils.VERDE + player.getName() + ", inserisci la tua mossa (riga e colonna): " + ColorUtils.RESET);
                    mossaValida = true; // La mossa è valida, esci dal ciclo
                    break;// Esci dal ciclo per fare la mossa
                default:
                    System.out.println(ColorUtils.coloraErrore("Scelta non valida. Riprova."));
            }
        }while(!mossaValida);

        int riga = Integer.parseInt(System.console().readLine());
        int colonna = Integer.parseInt(System.console().readLine());
        if (tabella.inserisciSimbolo(riga, colonna, player.getSymbol())) {
            tabella.stampaTabella();
        } else {
            System.out.println(ColorUtils.coloraErrore("Mossa non valida! Riprova."));
            MossaGiocatore(player, tabella);
        }
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

    public void StampaVincitore(Players player) {
        System.out.println(ColorUtils.coloraVincitore("Il vincitore è: " + player.getName() + " con simbolo " + 
            (player.getSymbol() == 'X' ? ColorUtils.coloraX("X") : ColorUtils.coloraO("O"))));
    }
}

package core;

public class TabellaGioco {
    private static final int RIGHE = 3;
    private static final int COLONNE = 3;
    private String[][] tabella = new String[RIGHE][COLONNE];

    public TabellaGioco() {
        resetTabella();
    }

    public String[][] getTabella() {
        return tabella;
    }

    public void stampaTabella() {
        System.out.println(ColorUtils.coloraTabella("   0   1   2 "));
        System.out.println(ColorUtils.coloraTabella("  ┌───┬───┬───┐"));
        for (int i = 0; i < RIGHE; i++) {
            System.out.print(ColorUtils.coloraTabella(i + " │"));
            for (int j = 0; j < COLONNE; j++) {
                String cella = tabella[i][j];
                if (cella.equals("X")) {
                    System.out.print(" " + ColorUtils.coloraX("X") + ColorUtils.coloraTabella(" │"));
                } else if (cella.equals("O")) {
                    System.out.print(" " + ColorUtils.coloraO("O") + ColorUtils.coloraTabella(" │"));
                } else {
                    System.out.print(ColorUtils.coloraTabella(" " + cella + " │"));
                }
            }
            System.out.println();
            if (i < RIGHE - 1) {
                System.out.println(ColorUtils.coloraTabella("  ├───┼───┼───┤"));
            }
        }
        System.out.println(ColorUtils.coloraTabella("  └───┴───┴───┘"));
    }

    public boolean inserisciSimbolo(int riga, int colonna, char simbolo) {
        if (riga >= 0 && riga < RIGHE && colonna >= 0 && colonna < COLONNE && tabella[riga][colonna].equals(" ")) {
            tabella[riga][colonna] = String.valueOf(simbolo);
            return true;
        }
        return false;
    }

    public boolean tabellaPiena() {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                if (tabella[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetTabella() {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                tabella[i][j] = " ";
            }
        }
    }

    public boolean isCellaLibera(int riga, int colonna) {
        return tabella[riga][colonna].equals(" ");
    }
}

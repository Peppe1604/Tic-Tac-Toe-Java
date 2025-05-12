package core;

public class ColorUtils {
    // Colori di testo
    public static final String RESET = "\u001B[0m";
    public static final String NERO = "\u001B[30m";
    public static final String ROSSO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String GIALLO = "\u001B[33m";
    public static final String BLU = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";
    public static final String BIANCO = "\u001B[37m";
    
    // Colori di sfondo
    public static final String SFONDO_NERO = "\u001B[40m";
    public static final String SFONDO_ROSSO = "\u001B[41m";
    public static final String SFONDO_VERDE = "\u001B[42m";
    public static final String SFONDO_GIALLO = "\u001B[43m";
    public static final String SFONDO_BLU = "\u001B[44m";
    public static final String SFONDO_MAGENTA = "\u001B[45m";
    public static final String SFONDO_CIANO = "\u001B[46m";
    public static final String SFONDO_BIANCO = "\u001B[47m";
    
    // Stili di testo
    public static final String GRASSETTO = "\u001B[1m";
    public static final String SOTTOLINEATO = "\u001B[4m";
    
    // Metodi di utilità
    public static String coloraX(String testo) {
        return ROSSO + testo + RESET;
    }
    
    public static String coloraO(String testo) {
        return BLU + testo + RESET;
    }
    
    public static String coloraTitolo(String testo) {
        return GIALLO + GRASSETTO + testo + RESET;
    }
    
    public static String coloraMenu(String testo) {
        return VERDE + testo + RESET;
    }
    
    public static String coloraErrore(String testo) {
        return ROSSO + GRASSETTO + testo + RESET;
    }
    
    public static String coloraVincitore(String testo) {
        return MAGENTA + GRASSETTO + testo + RESET;
    }
    
    public static String coloraTabella(String testo) {
        return CIANO + testo + RESET;
    }
}

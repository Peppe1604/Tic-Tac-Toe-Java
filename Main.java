import core.GameStartManagement;
import gui.TrisGUI;
import javax.swing.*;

class Main {
    public static void main(String[] args) {
        // Se viene passato l'argomento "--console", avvia la versione console
        if (args.length > 0 && args[0].equals("--console")) {
            avviaVersioneConsole();
        } else {
            // Altrimenti avvia la GUI
            avviaGUI();
        }
    }
    
    private static void avviaGUI() {
        try {
            // Imposta il look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Avvia la GUI nel thread EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            new TrisGUI();
        });
    }
    
    private static void avviaVersioneConsole() {
        System.out.println("CIAO BENVENUTO NEL GIOCO TRIS");
        System.out.println("COME VUOI IMPOSTARE LA PARTITA?");
        System.out.println("1. Giocatore vs Giocatore");
        System.out.println("2. Giocatore vs Computer");
        System.out.println("3. Esci dal gioco");
        System.out.println("Scegli un opzione: ");
        String[] args = new String[0]; // Definisci args locale per la console
        int scelta = Integer.parseInt(System.console().readLine());
        switch (scelta) {
            case 1:
                GameStartManagement GiocatoreVsGiocatore = new GameStartManagement();
                GiocatoreVsGiocatore.GiocatoreVsGiocatore(args);
                break;
            case 2:
                GameStartManagement GiocatoreVsComputer = new GameStartManagement();
                GiocatoreVsComputer.GiocatoreVsComputer(args);
                break;
            case 3:
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }
}
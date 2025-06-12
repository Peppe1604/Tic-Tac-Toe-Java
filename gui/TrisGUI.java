package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import core.TabellaGioco;
import core.PlayerVsPlayer.Players;
import core.PlayersVsComputer.Players_PC;

public class TrisGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private Color primaryColor = new Color(41, 128, 185);
    private Color secondaryColor = new Color(52, 152, 219);
    private Color accentColor = new Color(231, 76, 60);
    private Color successColor = new Color(46, 204, 113);
    
    public TrisGUI() {
        setTitle("Tic-Tac-Toe Ultimate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        
        // Imposta look and feel moderno
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(236, 240, 241));
        
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        
        // Animazione iniziale
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            animateStartup();
        });
    }
    
    private void animateStartup() {
        // Fade in effect
        Timer timer = new Timer(20, new ActionListener() {
            float currentAlpha = 0f;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAlpha += 0.05f;
                if (currentAlpha >= 1f) {
                    currentAlpha = 1f;
                    ((Timer)e.getSource()).stop();
                }
                mainPanel.setBackground(new Color(236, 240, 241, (int)(255 * currentAlpha)));
                mainPanel.repaint();
            }
        });
        timer.start();
    }
    
    // Pannello Menu principale
    class MenuPanel extends JPanel {
        private JLabel titleLabel;
        private ModernButton pvpButton, pvcButton, exitButton;
        
        public MenuPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(236, 240, 241));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 50, 10, 50);
            
            // Titolo con effetto gradiente
            titleLabel = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    GradientPaint gradient = new GradientPaint(
                        0, 0, primaryColor,
                        getWidth(), getHeight(), secondaryColor
                    );
                    g2d.setPaint(gradient);
                    g2d.setFont(new Font("Arial", Font.BOLD, 48));
                    
                    FontMetrics fm = g2d.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(getText())) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    
                    // Ombra
                    g2d.setColor(new Color(0, 0, 0, 50));
                    g2d.drawString(getText(), x + 3, y + 3);
                    
                    // Testo principale
                    g2d.setPaint(gradient);
                    g2d.drawString(getText(), x, y);
                }
            };
            titleLabel.setPreferredSize(new Dimension(400, 100));
            add(titleLabel, gbc);
            
            // Spazio
            gbc.insets = new Insets(30, 50, 10, 50);
            add(Box.createVerticalStrut(20), gbc);
            
            // Bottoni
            pvpButton = new ModernButton("Giocatore vs Giocatore", new Color(52, 152, 219));
            pvpButton.addActionListener(e -> startPvPGame());
            gbc.insets = new Insets(10, 100, 10, 100);
            add(pvpButton, gbc);
            
            pvcButton = new ModernButton("Giocatore vs Computer", new Color(46, 204, 113));
            pvcButton.addActionListener(e -> startPvCGame());
            add(pvcButton, gbc);
            
            exitButton = new ModernButton("Esci", new Color(231, 76, 60));
            exitButton.addActionListener(e -> System.exit(0));
            add(exitButton, gbc);
        }
        
        private void startPvPGame() {
            gamePanel.initPvPGame();
            cardLayout.show(mainPanel, "game");
        }
        
        private void startPvCGame() {
            gamePanel.initPvCGame();
            cardLayout.show(mainPanel, "game");
        }
    }
    
    // Pannello di gioco
    class GamePanel extends JPanel {
        private JButton[][] buttons;
        private TabellaGioco tabella;
        private JLabel statusLabel;
        private JLabel player1Label, player2Label;
        private boolean isPvP;
        private boolean isPlayerTurn = true;
        private Players player1, player2;
        private Players_PC playerPC1, playerPC2;
        private Timer aiTimer;
        
        public GamePanel() {
            setLayout(new BorderLayout(20, 20));
            setBackground(new Color(236, 240, 241));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Pannello superiore con info giocatori
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setOpaque(false);
            
            player1Label = createPlayerLabel("Giocatore 1", Color.RED);
            player2Label = createPlayerLabel("Giocatore 2", Color.BLUE);
            
            topPanel.add(player1Label, BorderLayout.WEST);
            topPanel.add(player2Label, BorderLayout.EAST);
            
            // Status centrale
            statusLabel = new JLabel("", SwingConstants.CENTER);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
            statusLabel.setForeground(primaryColor);
            topPanel.add(statusLabel, BorderLayout.CENTER);
            
            add(topPanel, BorderLayout.NORTH);
            
            // Griglia di gioco
            JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
            gridPanel.setBackground(primaryColor);
            gridPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primaryColor, 5),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            
            buttons = new JButton[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = createGameButton(i, j);
                    gridPanel.add(buttons[i][j]);
                }
            }
            
            add(gridPanel, BorderLayout.CENTER);
            
            // Pannello inferiore con bottoni
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            bottomPanel.setOpaque(false);
            
            ModernButton resetButton = new ModernButton("Nuova Partita", successColor);
            resetButton.addActionListener(e -> resetGame());
            
            ModernButton menuButton = new ModernButton("Menu", accentColor);
            menuButton.addActionListener(e -> {
                if (aiTimer != null) aiTimer.stop();
                cardLayout.show(mainPanel, "menu");
            });
            
            bottomPanel.add(resetButton);
            bottomPanel.add(menuButton);
            
            add(bottomPanel, BorderLayout.SOUTH);
        }
        
        private JLabel createPlayerLabel(String name, Color color) {
            JLabel label = new JLabel(name, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(color);
            label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            return label;
        }
        
        private JButton createGameButton(int row, int col) {
            JButton button = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    if (getText().equals("X")) {
                        g2d.setColor(Color.RED);
                        g2d.setStroke(new BasicStroke(8));
                        int padding = 20;
                        g2d.drawLine(padding, padding, getWidth() - padding, getHeight() - padding);
                        g2d.drawLine(getWidth() - padding, padding, padding, getHeight() - padding);
                    } else if (getText().equals("O")) {
                        g2d.setColor(Color.BLUE);
                        g2d.setStroke(new BasicStroke(8));
                        int padding = 20;
                        g2d.drawOval(padding, padding, getWidth() - 2*padding, getHeight() - 2*padding);
                    }
                }
            };
            
            button.setPreferredSize(new Dimension(120, 120));
            button.setFont(new Font("Arial", Font.BOLD, 0)); // Font size 0 perché disegniamo custom
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            button.setText("");
            
            // Effetto hover
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.getText().isEmpty()) {
                        button.setBackground(new Color(245, 245, 245));
                        button.setBorder(BorderFactory.createLineBorder(primaryColor, 2));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (button.getText().isEmpty()) {
                        button.setBackground(Color.WHITE);
                        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    }
                }
            });
            
            button.addActionListener(e -> handleButtonClick(row, col));
            
            return button;
        }
        
        public void initPvPGame() {
            isPvP = true;
            tabella = new TabellaGioco();
            
            // Dialog per inserire i nomi
            String name1 = JOptionPane.showInputDialog(this, "Nome Giocatore 1:", "Giocatore 1");
            String name2 = JOptionPane.showInputDialog(this, "Nome Giocatore 2:", "Giocatore 2");
            
            player1 = new Players(name1 != null ? name1 : "Giocatore 1");
            player2 = new Players(name2 != null ? name2 : "Giocatore 2");
            
            player1.setSymbol('X');
            player2.setSymbol('O');
            
            player1Label.setText(player1.getName() + " (X)");
            player2Label.setText(player2.getName() + " (O)");
            
            resetGame();
            updateStatus(player1.getName() + " inizia!");
        }
        
        public void initPvCGame() {
            isPvP = false;
            tabella = new TabellaGioco();
            
            String name = JOptionPane.showInputDialog(this, "Il tuo nome:", "Giocatore");
            
            playerPC1 = new Players_PC(name != null ? name : "Giocatore");
            playerPC2 = new Players_PC("Computer");
            
            playerPC1.setSymbol('X');
            playerPC2.setSymbol('O');
            
            player1Label.setText(playerPC1.getName() + " (X)");
            player2Label.setText(playerPC2.getName() + " (O)");
            
            resetGame();
            updateStatus(playerPC1.getName() + " inizia!");
        }
        
        private void handleButtonClick(int row, int col) {
            if (!buttons[row][col].getText().isEmpty()) return;
            
            if (isPvP) {
                handlePvPMove(row, col);
            } else {
                handlePvCMove(row, col);
            }
        }
        
        private void handlePvPMove(int row, int col) {
            char currentSymbol = isPlayerTurn ? player1.getSymbol() : player2.getSymbol();
            
            if (tabella.inserisciSimbolo(row, col, currentSymbol)) {
                animateMove(buttons[row][col], String.valueOf(currentSymbol));
                
                if (checkWin(currentSymbol)) {
                    String winner = isPlayerTurn ? player1.getName() : player2.getName();
                    showWinAnimation(winner);
                } else if (tabella.tabellaPiena()) {
                    updateStatus("Pareggio!");
                    disableAllButtons();
                } else {
                    isPlayerTurn = !isPlayerTurn;
                    String nextPlayer = isPlayerTurn ? player1.getName() : player2.getName();
                    updateStatus("Turno di " + nextPlayer);
                    highlightCurrentPlayer();
                }
            }
        }
        
        private void handlePvCMove(int row, int col) {
            if (!isPlayerTurn) return;
            
            if (tabella.inserisciSimbolo(row, col, playerPC1.getSymbol())) {
                animateMove(buttons[row][col], String.valueOf(playerPC1.getSymbol()));
                
                if (checkWin(playerPC1.getSymbol())) {
                    showWinAnimation(playerPC1.getName());
                } else if (tabella.tabellaPiena()) {
                    updateStatus("Pareggio!");
                    disableAllButtons();
                } else {
                    isPlayerTurn = false;
                    updateStatus("Turno del Computer...");
                    highlightCurrentPlayer();
                    
                    // Ritardo per la mossa del computer
                    aiTimer = new Timer(1000, e -> {
                        makeComputerMove();
                        aiTimer.stop();
                    });
                    aiTimer.start();
                }
            }
        }
        
        private void makeComputerMove() {
            // Trova una mossa valida random
            int row, col;
            do {
                row = (int)(Math.random() * 3);
                col = (int)(Math.random() * 3);
            } while (!buttons[row][col].getText().isEmpty());
            
            tabella.inserisciSimbolo(row, col, playerPC2.getSymbol());
            animateMove(buttons[row][col], String.valueOf(playerPC2.getSymbol()));
            
            if (checkWin(playerPC2.getSymbol())) {
                showWinAnimation(playerPC2.getName());
            } else if (tabella.tabellaPiena()) {
                updateStatus("Pareggio!");
                disableAllButtons();
            } else {
                isPlayerTurn = true;
                updateStatus("Il tuo turno!");
                highlightCurrentPlayer();
            }
        }
        
        private void animateMove(JButton button, String symbol) {
            button.setText(symbol);
            
            // Animazione di scala
            Timer scaleTimer = new Timer(10, null);
            scaleTimer.addActionListener(new ActionListener() {
                float scale = 0.5f;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    scale += 0.1f;
                    if (scale >= 1.0f) {
                        scale = 1.0f;
                        scaleTimer.stop();
                    }
                    
                    int size = (int)(120 * scale);
                    button.setPreferredSize(new Dimension(size, size));
                    button.revalidate();
                    button.repaint();
                }
            });
            scaleTimer.start();
        }
        
        private void highlightCurrentPlayer() {
            if (isPvP) {
                if (isPlayerTurn) {
                    player1Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.RED, 4),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                    player2Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLUE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                } else {
                    player1Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.RED, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                    player2Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLUE, 4),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                }
            } else {
                if (isPlayerTurn) {
                    player1Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.RED, 4),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                    player2Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLUE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                } else {
                    player1Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.RED, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                    player2Label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLUE, 4),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                }
            }
        }
        
        private boolean checkWin(char symbol) {
            String sym = String.valueOf(symbol);
            
            // Controlla righe
            for (int i = 0; i < 3; i++) {
                if (tabella.getTabella()[i][0].equals(sym) &&
                    tabella.getTabella()[i][1].equals(sym) &&
                    tabella.getTabella()[i][2].equals(sym)) {
                    highlightWinningLine(i, 0, i, 1, i, 2);
                    return true;
                }
            }
            
            // Controlla colonne
            for (int j = 0; j < 3; j++) {
                if (tabella.getTabella()[0][j].equals(sym) &&
                    tabella.getTabella()[1][j].equals(sym) &&
                    tabella.getTabella()[2][j].equals(sym)) {
                    highlightWinningLine(0, j, 1, j, 2, j);
                    return true;
                }
            }
            
            // Controlla diagonali
            if (tabella.getTabella()[0][0].equals(sym) &&
                tabella.getTabella()[1][1].equals(sym) &&
                tabella.getTabella()[2][2].equals(sym)) {
                highlightWinningLine(0, 0, 1, 1, 2, 2);
                return true;
            }
            
            if (tabella.getTabella()[0][2].equals(sym) &&
                tabella.getTabella()[1][1].equals(sym) &&
                tabella.getTabella()[2][0].equals(sym)) {
                highlightWinningLine(0, 2, 1, 1, 2, 0);
                return true;
            }
            
            return false;
        }
        
        private void highlightWinningLine(int r1, int c1, int r2, int c2, int r3, int c3) {
            Color winColor = new Color(255, 215, 0); // Gold
            buttons[r1][c1].setBackground(winColor);
            buttons[r2][c2].setBackground(winColor);
            buttons[r3][c3].setBackground(winColor);
        }
        
        private void showWinAnimation(String winner) {
            updateStatus(winner + " ha vinto! 🎉");
            disableAllButtons();
            
            // Animazione di vittoria
            Timer celebrationTimer = new Timer(100, null);
            celebrationTimer.addActionListener(new ActionListener() {
                int count = 0;
                Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    if (count > 10) {
                        celebrationTimer.stop();
                        return;
                    }
                    
                    statusLabel.setForeground(colors[count % colors.length]);
                }
            });
            celebrationTimer.start();
        }
        
        private void updateStatus(String message) {
            statusLabel.setText(message);
        }
        
        private void disableAllButtons() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
        
        private void resetGame() {
            tabella = new TabellaGioco();
            isPlayerTurn = true;
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    buttons[i][j].setPreferredSize(new Dimension(120, 120));
                }
            }
            
            if (isPvP) {
                updateStatus(player1.getName() + " inizia!");
            } else {
                updateStatus(playerPC1.getName() + " inizia!");
            }
            
            highlightCurrentPlayer();
        }
    }
    
    // Bottone moderno con effetti
    class ModernButton extends JButton {
        private Color baseColor;
        private Color hoverColor;
        private boolean isHovered = false;
        
        public ModernButton(String text, Color color) {
            super(text);
            this.baseColor = color;
            this.hoverColor = color.brighter();
            
            setFont(new Font("Arial", Font.BOLD, 16));
            setForeground(Color.WHITE);
            setBackground(baseColor);
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    animateHover(true);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    animateHover(false);
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    setBackground(baseColor.darker());
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    setBackground(isHovered ? hoverColor : baseColor);
                }
            });
        }
        
        private void animateHover(boolean hover) {
            Timer timer = new Timer(10, null);
            timer.addActionListener(new ActionListener() {
                float progress = 0;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 0.1f;
                    if (progress >= 1.0f) {
                        progress = 1.0f;
                        timer.stop();
                    }
                    
                    if (hover) {
                        setBackground(interpolateColor(baseColor, hoverColor, progress));
                    } else {
                        setBackground(interpolateColor(hoverColor, baseColor, progress));
                    }
                }
            });
            timer.start();
        }
        
        private Color interpolateColor(Color c1, Color c2, float ratio) {
            int red = (int)(c1.getRed() * (1 - ratio) + c2.getRed() * ratio);
            int green = (int)(c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
            int blue = (int)(c1.getBlue() * (1 - ratio) + c2.getBlue() * ratio);
            return new Color(red, green, blue);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Ombra
            g2d.setColor(new Color(0, 0, 0, 30));
            g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 15, 15);
            
            // Sfondo
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 15, 15);
            
            // Testo
            g2d.setColor(getForeground());
            g2d.setFont(getFont());
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(getText(), x, y);
            
            g2d.dispose();
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 50);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrisGUI());
    }
}
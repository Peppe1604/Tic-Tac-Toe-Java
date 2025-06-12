package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.util.ArrayList;
import java.util.Random;

public class TrisGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    
    // Colori tema dark moderno
    private final Color primaryNeon = new Color(0, 255, 255);
    private final Color secondaryNeon = new Color(255, 0, 255);
    private final Color accentNeon = new Color(255, 255, 0);
    private final Color glowRed = new Color(255, 69, 58);
    private final Color glowBlue = new Color(10, 132, 255);
    
    public TrisGUI() {
        setTitle("NEON TRIS - Ultimate Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        setUndecorated(true); // Rimuove la barra del titolo per un look più moderno
        
        // Rende la finestra trascinabile
        addWindowDragListener();
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente di sfondo
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(10, 10, 20),
                    getWidth(), getHeight(), new Color(30, 10, 40)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Particelle di sfondo
                drawBackgroundParticles(g2d);
            }
        };
        
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
            startBackgroundAnimation();
        });
    }
    
    private void addWindowDragListener() {
        final Point[] mousePos = new Point[1];
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePos[0] = e.getPoint();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currentPos = e.getLocationOnScreen();
                setLocation(currentPos.x - mousePos[0].x, currentPos.y - mousePos[0].y);
            }
        });
    }
    
    private ArrayList<Particle> particles = new ArrayList<>();
    
    private void startBackgroundAnimation() {
        // Genera particelle iniziali
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            particles.add(new Particle(
                rand.nextInt(getWidth()),
                rand.nextInt(getHeight()),
                rand.nextDouble() * 2 - 1,
                rand.nextDouble() * 2 - 1,
                rand.nextInt(3) + 1
            ));
        }
        
        Timer animTimer = new Timer(30, e -> {
            // Aggiorna particelle
            for (Particle p : particles) {
                p.update(getWidth(), getHeight());
            }
            mainPanel.repaint();
        });
        animTimer.start();
    }
    
    private void drawBackgroundParticles(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        for (Particle p : particles) {
            g2d.setColor(primaryNeon);
            g2d.fillOval((int)p.x, (int)p.y, p.size, p.size);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
    // Classe per le particelle di sfondo
    class Particle {
        double x, y, vx, vy;
        int size;
        
        Particle(double x, double y, double vx, double vy, int size) {
            this.x = x; this.y = y; this.vx = vx; this.vy = vy; this.size = size;
        }
        
        void update(int maxX, int maxY) {
            x += vx;
            y += vy;
            if (x < 0 || x > maxX) vx = -vx;
            if (y < 0 || y > maxY) vy = -vy;
        }
    }
    
    // Pannello Menu principale
    class MenuPanel extends JPanel {
        public MenuPanel() {
            setLayout(new GridBagLayout());
            setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(20, 50, 20, 50);
            
            // Titolo con effetto neon
            JLabel titleLabel = new JLabel("NEON TRIS") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    String text = getText();
                    g2d.setFont(new Font("Arial Black", Font.BOLD, 72));
                    FontMetrics fm = g2d.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(text)) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    
                    // Effetto glow neon
                    for (int i = 10; i > 0; i--) {
                        float alpha = 0.05f * (10 - i);
                        g2d.setColor(new Color(0, 255, 255, (int)(255 * alpha)));
                        g2d.setStroke(new BasicStroke(i * 2));
                        
                        // Crea il contorno del testo
                        GlyphVector gv = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), text);
                        Shape outline = gv.getOutline(x, y);
                        g2d.draw(outline);
                    }
                    
                    // Testo principale
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(text, x, y);
                }
            };
            titleLabel.setPreferredSize(new Dimension(500, 150));
            add(titleLabel, gbc);
            
            // Bottoni con effetto glassmorphism
            gbc.insets = new Insets(10, 100, 10, 100);
            
            NeonButton pvpButton = new NeonButton("PLAYER VS PLAYER", primaryNeon);
            pvpButton.addActionListener(e -> {
                gamePanel.initPvPGame();
                cardLayout.show(mainPanel, "game");
            });
            add(pvpButton, gbc);
            
            NeonButton pvcButton = new NeonButton("PLAYER VS AI", secondaryNeon);
            pvcButton.addActionListener(e -> {
                gamePanel.initPvCGame();
                cardLayout.show(mainPanel, "game");
            });
            add(pvcButton, gbc);
            
            NeonButton exitButton = new NeonButton("EXIT", glowRed);
            exitButton.addActionListener(e -> {
                // Animazione di uscita
                Timer exitTimer = new Timer(10, null);
                exitTimer.addActionListener(new ActionListener() {
                    float alpha = 1f;
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        alpha -= 0.05f;
                        if (alpha <= 0) {
                            System.exit(0);
                        }
                        setOpacity(alpha);
                    }
                });
                exitTimer.start();
            });
            add(exitButton, gbc);
        }
    }
    
    // Pannello di gioco
    class GamePanel extends JPanel {
        private GameButton[][] buttons;
        private char[][] board;
        private JLabel statusLabel;
        private JPanel player1Panel, player2Panel;
        private boolean isPlayerXTurn = true;
        private boolean isPvP;
        private String player1Name, player2Name;
        private boolean gameOver = false;
        private Timer aiTimer;
        
        public GamePanel() {
            setLayout(new BorderLayout(20, 20));
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            
            // Header con info giocatori
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setOpaque(false);
            
            player1Panel = createPlayerPanel("Player 1", glowRed, 'X');
            player2Panel = createPlayerPanel("Player 2", glowBlue, 'O');
            
            headerPanel.add(player1Panel, BorderLayout.WEST);
            headerPanel.add(player2Panel, BorderLayout.EAST);
            
            // Status centrale
            statusLabel = new JLabel("", SwingConstants.CENTER);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
            statusLabel.setForeground(Color.WHITE);
            headerPanel.add(statusLabel, BorderLayout.CENTER);
            
            add(headerPanel, BorderLayout.NORTH);
            
            // Griglia di gioco con effetto glassmorphism
            JPanel gridContainer = new JPanel(new GridBagLayout());
            gridContainer.setOpaque(false);
            
            JPanel gridPanel = new JPanel(new GridLayout(3, 3, 15, 15)) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Effetto glassmorphism
                    g2d.setColor(new Color(255, 255, 255, 10));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                    
                    // Bordo glow
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(new Color(0, 255, 255, 100));
                    g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
                }
            };
            gridPanel.setOpaque(false);
            gridPanel.setPreferredSize(new Dimension(400, 400));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            buttons = new GameButton[3][3];
            board = new char[3][3];
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                    buttons[i][j] = new GameButton(i, j);
                    gridPanel.add(buttons[i][j]);
                }
            }
            
            gridContainer.add(gridPanel);
            add(gridContainer, BorderLayout.CENTER);
            
            // Footer con bottoni
            JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
            footerPanel.setOpaque(false);
            
            NeonButton resetButton = new NeonButton("NEW GAME", accentNeon);
            resetButton.setPreferredSize(new Dimension(150, 40));
            resetButton.addActionListener(e -> resetGame());
            
            NeonButton menuButton = new NeonButton("MENU", glowRed);
            menuButton.setPreferredSize(new Dimension(150, 40));
            menuButton.addActionListener(e -> {
                if (aiTimer != null) aiTimer.stop();
                cardLayout.show(mainPanel, "menu");
            });
            
            footerPanel.add(resetButton);
            footerPanel.add(menuButton);
            
            add(footerPanel, BorderLayout.SOUTH);
        }
        
        private JPanel createPlayerPanel(String name, Color color, char symbol) {
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Glassmorphism effect
                    g2d.setColor(new Color(255, 255, 255, 20));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    
                    // Bordo colorato
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(color);
                    g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 20, 20);
                }
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(200, 80));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel symbolLabel = new JLabel("[ " + symbol + " ]");
            symbolLabel.setFont(new Font("Arial", Font.BOLD, 24));
            symbolLabel.setForeground(color);
            symbolLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            panel.add(Box.createVerticalGlue());
            panel.add(nameLabel);
            panel.add(symbolLabel);
            panel.add(Box.createVerticalGlue());
            
            return panel;
        }
        
        public void initPvPGame() {
            isPvP = true;
            player1Name = JOptionPane.showInputDialog(this, "Nome Giocatore 1:", "Player 1");
            player2Name = JOptionPane.showInputDialog(this, "Nome Giocatore 2:", "Player 2");
            
            if (player1Name == null || player1Name.trim().isEmpty()) player1Name = "Player 1";
            if (player2Name == null || player2Name.trim().isEmpty()) player2Name = "Player 2";
            
            updatePlayerPanels();
            resetGame();
        }
        
        public void initPvCGame() {
            isPvP = false;
            player1Name = JOptionPane.showInputDialog(this, "Il tuo nome:", "Player");
            player2Name = "AI Bot";
            
            if (player1Name == null || player1Name.trim().isEmpty()) player1Name = "Player";
            
            updatePlayerPanels();
            resetGame();
        }
        
        private void updatePlayerPanels() {
            ((JLabel)player1Panel.getComponent(1)).setText(player1Name);
            ((JLabel)player2Panel.getComponent(1)).setText(player2Name);
            updateStatus(player1Name + " inizia!");
            highlightCurrentPlayer();
        }
        
        class GameButton extends JButton {
            private int row, col;
            private boolean isHovered = false;
            
            GameButton(int row, int col) {
                this.row = row;
                this.col = col;
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setFont(new Font("Arial", Font.BOLD, 0));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (board[row][col] == ' ' && !gameOver) {
                            isHovered = true;
                            repaint();
                        }
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        isHovered = false;
                        repaint();
                    }
                });
                
                addActionListener(e -> handleClick());
            }
            
            private void handleClick() {
                if (board[row][col] != ' ' || gameOver) return;
                
                if (isPvP || isPlayerXTurn) {
                    makeMove(row, col);
                }
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Sfondo glassmorphism
                if (isHovered && board[row][col] == ' ') {
                    g2d.setColor(new Color(255, 255, 255, 30));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 10));
                }
                g2d.fillRoundRect(5, 5, getWidth()-10, getHeight()-10, 15, 15);
                
                // Bordo
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(new Color(255, 255, 255, 50));
                g2d.drawRoundRect(5, 5, getWidth()-10, getHeight()-10, 15, 15);
                
                // Disegna simbolo con effetto neon
                if (board[row][col] == 'X') {
                    drawNeonX(g2d);
                } else if (board[row][col] == 'O') {
                    drawNeonO(g2d);
                }
            }
            
            private void drawNeonX(Graphics2D g2d) {
                int padding = 25;
                
                // Glow effect
                for (int i = 5; i > 0; i--) {
                    g2d.setStroke(new BasicStroke(i * 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.setColor(new Color(255, 69, 58, 50 - i * 8));
                    g2d.drawLine(padding, padding, getWidth() - padding, getHeight() - padding);
                    g2d.drawLine(getWidth() - padding, padding, padding, getHeight() - padding);
                }
                
                // Main X
                g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.setColor(Color.WHITE);
                g2d.drawLine(padding, padding, getWidth() - padding, getHeight() - padding);
                g2d.drawLine(getWidth() - padding, padding, padding, getHeight() - padding);
            }
            
            private void drawNeonO(Graphics2D g2d) {
                int padding = 20;
                
                // Glow effect
                for (int i = 5; i > 0; i--) {
                    g2d.setStroke(new BasicStroke(i * 3));
                    g2d.setColor(new Color(10, 132, 255, 50 - i * 8));
                    g2d.drawOval(padding, padding, getWidth() - 2*padding, getHeight() - 2*padding);
                }
                
                // Main O
                g2d.setStroke(new BasicStroke(4));
                g2d.setColor(Color.WHITE);
                g2d.drawOval(padding, padding, getWidth() - 2*padding, getHeight() - 2*padding);
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        }
        
        private void makeMove(int row, int col) {
            char symbol = isPlayerXTurn ? 'X' : 'O';
            board[row][col] = symbol;
            buttons[row][col].repaint();
            
            // Effetto particella sulla mossa
            createMoveEffect(buttons[row][col], symbol);
            
            if (checkWin(symbol)) {
                String winner = isPlayerXTurn ? player1Name : player2Name;
                gameOver = true;
                showWinAnimation(winner);
            } else if (isBoardFull()) {
                gameOver = true;
                updateStatus("Pareggio! 🤝");
            } else {
                isPlayerXTurn = !isPlayerXTurn;
                highlightCurrentPlayer();
                
                if (!isPvP && !isPlayerXTurn) {
                    updateStatus("AI sta pensando...");
                    aiTimer = new Timer(800, e -> {
                        makeAIMove();
                        aiTimer.stop();
                    });
                    aiTimer.start();
                } else {
                    updateStatus("Turno di " + (isPlayerXTurn ? player1Name : player2Name));
                }
            }
        }
        
        private void makeAIMove() {
            // AI semplice: cerca prima di vincere, poi di bloccare, poi mossa casuale
            Point move = findBestMove();
            if (move != null) {
                makeMove(move.x, move.y);
            }
        }
        
        private Point findBestMove() {
            // Cerca mossa vincente per O
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        if (checkWin('O')) {
                            board[i][j] = ' ';
                            return new Point(i, j);
                        }
                        board[i][j] = ' ';
                    }
                }
            }
            
            // Blocca mossa vincente di X
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        if (checkWin('X')) {
                            board[i][j] = ' ';
                            return new Point(i, j);
                        }
                        board[i][j] = ' ';
                    }
                }
            }
            
            // Centro se libero
            if (board[1][1] == ' ') return new Point(1, 1);
            
            // Angoli
            int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
            for (int[] corner : corners) {
                if (board[corner[0]][corner[1]] == ' ') {
                    return new Point(corner[0], corner[1]);
                }
            }
            
            // Qualsiasi mossa rimanente
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        return new Point(i, j);
                    }
                }
            }
            
            return null;
        }
        
        private boolean checkWin(char symbol) {
            // Righe
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                    highlightWinLine(i, 0, i, 2, true);
                    return true;
                }
            }
            
            // Colonne
            for (int j = 0; j < 3; j++) {
                if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                    highlightWinLine(0, j, 2, j, false);
                    return true;
                }
            }
            
            // Diagonali
            if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
                highlightWinLine(0, 0, 2, 2, false);
                return true;
            }
            
            if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
                highlightWinLine(0, 2, 2, 0, false);
                return true;
            }
            
            return false;
        }
        
        private void highlightWinLine(int startRow, int startCol, int endRow, int endCol, boolean isRow) {
            Timer highlightTimer = new Timer(100, null);
            highlightTimer.addActionListener(new ActionListener() {
                int count = 0;
                Color[] colors = {accentNeon, primaryNeon, secondaryNeon};
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    if (count > 20) {
                        highlightTimer.stop();
                        return;
                    }
                    
                    if (isRow) {
                        for (int j = startCol; j <= endCol; j++) {
                            buttons[startRow][j].setBackground(colors[count % 3]);
                        }
                    } else if (startCol == endCol) {
                        for (int i = startRow; i <= endRow; i++) {
                            buttons[i][startCol].setBackground(colors[count % 3]);
                        }
                    } else {
                        // Diagonale
                        int rowStep = (endRow - startRow) / 2;
                        int colStep = (endCol - startCol) / 2;
                        for (int i = 0; i < 3; i++) {
                            buttons[startRow + i*rowStep][startCol + i*colStep].setBackground(colors[count % 3]);
                        }
                    }
                }
            });
            highlightTimer.start();
        }
        
        private boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') return false;
                }
            }
            return true;
        }
        
        private void createMoveEffect(JButton button, char symbol) {
            // Animazione di espansione
            Timer effectTimer = new Timer(20, null);
            effectTimer.addActionListener(new ActionListener() {
                float scale = 0.8f;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    scale += 0.05f;
                    if (scale >= 1.2f) {
                        effectTimer.stop();
                        
                        // Torna alla dimensione normale
                        Timer shrinkTimer = new Timer(20, evt -> {
                            scale -= 0.05f;
                            if (scale <= 1.0f) {
                                ((Timer)evt.getSource()).stop();
                            }
                        });
                        shrinkTimer.start();
                    }
                }
            });
            effectTimer.start();
        }
        
        private void highlightCurrentPlayer() {
            // Animazione sui pannelli giocatore
            Timer pulseTimer = new Timer(50, null);
            pulseTimer.addActionListener(new ActionListener() {
                int alpha = 100;
                boolean increasing = true;
                int count = 0;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (increasing) {
                        alpha += 10;
                        if (alpha >= 255) {
                            alpha = 255;
                            increasing = false;
                        }
                    } else {
                        alpha -= 10;
                        if (alpha <= 100) {
                            alpha = 100;
                            count++;
                            if (count >= 3) {
                                pulseTimer.stop();
                            }
                            increasing = true;
                        }
                    }
                    
                    JPanel activePanel = isPlayerXTurn ? player1Panel : player2Panel;
                    activePanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(255, 255, 255, alpha), 3),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                }
            });
            pulseTimer.start();
        }
        
        private void showWinAnimation(String winner) {
            updateStatus(winner + " VINCE! 🏆");
            
            // Effetto fuochi d'artificio
            Timer fireworksTimer = new Timer(100, null);
            fireworksTimer.addActionListener(new ActionListener() {
                int count = 0;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    if (count > 20) {
                        fireworksTimer.stop();
                        return;
                    }
                    
                    // Cambia colore del testo status
                    statusLabel.setForeground(new Color(
                        (int)(Math.random() * 255),
                        (int)(Math.random() * 255),
                        (int)(Math.random() * 255)
                    ));
                }
            });
            fireworksTimer.start();
        }
        
        private void updateStatus(String message) {
            statusLabel.setText(message);
        }
        
        private void resetGame() {
            gameOver = false;
            isPlayerXTurn = true;
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                    buttons[i][j].setBackground(null);
                    buttons[i][j].repaint();
                }
            }
            
            updateStatus(player1Name + " inizia!");
            highlightCurrentPlayer();
        }
    }
    
    // Bottone con effetto neon
    class NeonButton extends JButton {
        private Color neonColor;
        private Timer pulseTimer;
        private float glowIntensity = 0.5f;
        private boolean increasing = true;
        
        public NeonButton(String text, Color neonColor) {
            super(text);
            this.neonColor = neonColor;
            
            setFont(new Font("Arial Black", Font.BOLD, 16));
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Animazione pulsante
            pulseTimer = new Timer(50, e -> {
                if (increasing) {
                    glowIntensity += 0.05f;
                    if (glowIntensity >= 1f) {
                        glowIntensity = 1f;
                        increasing = false;
                    }
                } else {
                    glowIntensity -= 0.05f;
                    if (glowIntensity <= 0.3f) {
                        glowIntensity = 0.3f;
                        increasing = true;
                    }
                }
                repaint();
            });
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pulseTimer.start();
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    pulseTimer.stop();
                    glowIntensity = 0.5f;
                    repaint();
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    glowIntensity = 1f;
                    repaint();
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Glassmorphism background
            g2d.setColor(new Color(255, 255, 255, 20));
            g2d.fillRoundRect(5, 5, getWidth()-10, getHeight()-10, 25, 25);
            
            // Neon glow
            for (int i = 5; i > 0; i--) {
                int alpha = (int)(30 * glowIntensity * (5 - i) / 5);
                g2d.setColor(new Color(neonColor.getRed(), neonColor.getGreen(), neonColor.getBlue(), alpha));
                g2d.setStroke(new BasicStroke(i));
                g2d.drawRoundRect(5, 5, getWidth()-10, getHeight()-10, 25, 25);
            }
            
            // Bordo principale
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(neonColor);
            g2d.drawRoundRect(5, 5, getWidth()-10, getHeight()-10, 25, 25);
            
            // Testo con ombra
            g2d.setFont(getFont());
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            
            // Ombra testo
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.drawString(getText(), x + 2, y + 2);
            
            // Testo principale
            g2d.setColor(getForeground());
            g2d.drawString(getText(), x, y);
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(250, 60);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrisGUI());
    }
}
package org.cis120.TwentyFourtyEight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private TwentyFourtyEight tfe; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 500;

    private Tile[][] tiles;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new TwentyFourtyEight(); // initializes model for the game
        tiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile(0);
            }
        }
        status = statusInit; // initializes the status JLabel
        reset();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tfe.slideLeft();
                    tfe.placeNewTile();
                    updateTileValues();
                    updateStatus();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tfe.slideRight();
                    tfe.placeNewTile();
                    updateTileValues();
                    updateStatus();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tfe.slideDown();
                    tfe.placeNewTile();
                    updateTileValues();
                    updateStatus();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    tfe.slideUp();
                    tfe.placeNewTile();
                    updateTileValues();
                    updateStatus();
                }
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        status.setText("New Game Has Started. Use the Arrow Keys to Slide the Tiles Around");
        updateTileValues();
        repaint();
        requestFocusInWindow();
    }

    public void saveData() {
        tfe.writeToFile();
        status.setText("Save Successful.");
        requestFocusInWindow();
    }

    public void readData() {
        tfe.readFile();
        status.setText("Save Data Loaded Successfully.");
        updateTileValues();
        repaint();
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        tfe.checkGameOver();
        if (tfe.getGameOver()) {
            status.setText("Game Over. Press reset to try again.");
        } else if (tfe.checkWinner()) {
            status.setText("You win!");
        } else {
            status.setText("Your next goal is to get to the " + tfe.getNextGoal() + " tile!");
        }
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                add(tiles[i][j]);
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void setTileValue(int row, int col, int val) {
        tiles[row][col].setValue(val);
    }

    public void updateTileValues() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                setTileValue(i, j, tfe.getTileValue(i, j));
            }
        }
        repaint();
    }
}

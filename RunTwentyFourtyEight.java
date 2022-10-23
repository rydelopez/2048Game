package org.cis120.TwentyFourtyEight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunTwentyFourtyEight implements Runnable {
    public void run() {
        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(500, 250);
        frame.getContentPane().setBackground(Color.decode("#bcad9f"));

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("New Game Has Started. " +
                "Use the Arrow Keys to Slide the Tiles Around");
        status_panel.add(status);

        final GameBoard game_panel = new GameBoard(status);
        frame.add(game_panel);

        final JButton reset_button = new JButton("Reset");
        status_panel.add(reset_button);
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game_panel.reset();
            }
        });

        final JButton save_button = new JButton("Save");
        status_panel.add(save_button);
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game_panel.saveData();
            }
        });

        final JButton load_button = new JButton("Load");
        status_panel.add(load_button);
        load_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game_panel.readData();
            }
        });

        final JButton instructions_button = new JButton("Instructions");
        status_panel.add(instructions_button);
        instructions_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This is the traditional version of 2048. \n" +
                        "The goal of the game is to reach the number 2048 by " +
                                "combining tiles of equal values. \n" +
                                "Use the arrow keys to slide the pieces around the board " +
                                "(the left arrow key will slide every piece left, etc.). \n" +
                                "You can save your progress by pressing Save, " +
                                "and load it again by pressing Load. You can also restart " +
                                "by pressing Reset. \n" +
                                "Close this window to begin playing!");
                game_panel.requestFocusInWindow();
            }
        });

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
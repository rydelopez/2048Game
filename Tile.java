package org.cis120.TwentyFourtyEight;

import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {
    static final int SCALE = 100;
    static final int FONT_SIZE = 45;
    static final Font FONT = new Font("Clear Sans", Font.PLAIN, FONT_SIZE);

    private int value;

    public Tile(int value) {
        this.value = value;
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE, SCALE));
    }


    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Color color;
        if (value == 0) {
            color = Color.decode("#cdbfb4");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
        } else if (value == 2) {
            color = Color.decode("#eee4da");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#776e65"));
        } else if (value == 4) {
            color = Color.decode("#ede0c9");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#776e65"));
        } else if (value == 8) {
            color = Color.decode("#f2b178");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 16) {
            color = Color.decode("#f59662");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 32) {
            color = Color.decode("#f67d60");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 64) {
            color = Color.decode("#f65e3a");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 128) {
            color = Color.decode("#edcf73");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 256) {
            color = Color.decode("#eecc61");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 512) {
            color = Color.decode("#edc850");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 1024) {
            color = Color.decode("#ecc43f");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else if (value == 2048) {
            color = Color.decode("#edc22e");
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#faf6f3"));
        } else {
            color = Color.black;
            g.setColor(color);
            g.fillRect(10, 10, width - 10, height - 10);
            g.setColor(Color.decode("#D3D3D3"));
        }
        FontMetrics metrics = getFontMetrics(FONT);
        String text = Integer.toString(value);
        if (value != 0) {
            g.drawString(text, ((width + 10) - metrics.stringWidth(text)) / 2,
                    (height + 10) / 2 + metrics.getAscent() / 3);
        }
    }
}

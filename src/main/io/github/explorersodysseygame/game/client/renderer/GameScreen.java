package io.github.explorersodysseygame.game.client.renderer;

import io.github.explorersodysseygame.game.client.game.InGameMenu;
import io.github.explorersodysseygame.game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements ActionListener, KeyListener {
    public static final int GRID_SIZE = 20; // Pixel size of each square on the game grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static final Player player = new Player();

    private final InGameMenu inGameMenu;
    private static GameScreen screen;

    private final int width;
    private final int height;
    public GameScreen() {
        width = (GRID_SIZE * GRID_ROWS + GRID_SIZE) - 4;
        height = (GRID_SIZE * GRID_COLUMNS + (GRID_SIZE * 2)) - 1;
        inGameMenu = new InGameMenu(new Dimension(width, height));
        add(inGameMenu);
        setPreferredSize(new Dimension(width, height));
        setVisible(false);
        setLayout(null);
        setBackground(new Color(162, 255, 162));
        // Amount of ticks per second, as 1/[ticks]. Defaults to 1/20, as 20 TPS.
        int TPS = 1 / 20;
        Timer timer = new Timer(TPS, this);
        timer.start();
        screen = this;
    }

    public static GameScreen getScreen() {
        return screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.tick();

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        player.draw(g, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!inGameMenu.isOpen()) { // Only run these events if the in-game menu is closed
            player.keyPressed(e);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            inGameMenu.toggleVisibility();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void drawBackground(Graphics g) {
        // draw a checkered background
        g.setColor(new Color(81, 128, 81));
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                if ((row + col) % 2 == 1) {
                    g.fillRect(
                            col * GRID_SIZE,
                            row * GRID_SIZE,
                            GRID_SIZE,
                            GRID_SIZE
                    );
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public void toggleInGameMenu() { inGameMenu.setVisible(false); }
}

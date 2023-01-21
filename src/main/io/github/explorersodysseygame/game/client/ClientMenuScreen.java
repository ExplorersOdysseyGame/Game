package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.menu.MenuButton;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;
import io.github.explorersodysseygame.game.common.util.image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientMenuScreen extends MenuScreen {
    public static final int GRID_SIZE = 20; // Pixel size of each square on the menu grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static ClientMenuScreen screen;

    private final int width;
    private final int height;
    public ClientMenuScreen() {
        width = GRID_SIZE * GRID_ROWS - 1;
        height = GRID_SIZE * GRID_COLUMNS;
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(255, 162, 162));
        this.setLayout(null);
        screen = this;

        MenuButton playButton = new MenuButton("Play", new ImageIcon(ImageReader.read("menu/play.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed %s menu button", this.getText()));
                ClientWindow.loadGame(ClientWindow.getWindowAsJFrame(), ClientMenuScreen.getScreen());

            }
        };
        playButton.setBounds(GRID_SIZE * (GRID_ROWS/3), GRID_SIZE * 3, GRID_SIZE * (GRID_ROWS / 3), GRID_SIZE);

        this.add(playButton);
    }

    public static ClientMenuScreen getScreen() {
        return screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
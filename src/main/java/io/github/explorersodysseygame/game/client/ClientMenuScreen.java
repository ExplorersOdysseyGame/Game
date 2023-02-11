package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.menu.MenuButton;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ClientMenuScreen extends MenuScreen {
    public static final int GRID_SIZE = 20; // Pixel size of each square on the menu grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static ClientMenuScreen screen;

    public ClientMenuScreen(Main main) {
        super(main);
        int width = GRID_SIZE * GRID_ROWS - 1;
        int height = GRID_SIZE * GRID_COLUMNS;
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(255, 162, 162));
        this.setLayout(null);
        screen = this;

        BufferedImage IMG_play = Objects.requireNonNull(main.imageReader.read("game/menu/play.png")).getImage();

        MenuButton singleplayerButton = new MenuButton(main, "Play Singleplayer", new ImageIcon(IMG_play)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed %s menu button", this.getText()));
                ClientWindow.loadSingleplayerGame(ClientWindow.getWindowAsJFrame(), ClientMenuScreen.getScreen());

            }
        };
        singleplayerButton.setBounds(GRID_SIZE * (GRID_ROWS/3), GRID_SIZE * 3, GRID_SIZE * (GRID_ROWS / 3), GRID_SIZE);
        this.add(singleplayerButton);
        MenuButton multiplayerButton = new MenuButton(main, "Play Multiplayer", new ImageIcon(IMG_play)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed %s menu button", this.getText()));
                ClientWindow.loadMultiplayerGame(ClientWindow.getWindowAsJFrame(), ClientMenuScreen.getScreen());

            }
        };
        multiplayerButton.setBounds(GRID_SIZE * (GRID_ROWS/3), GRID_SIZE * 5, GRID_SIZE * (GRID_ROWS / 3), GRID_SIZE);
        this.add(multiplayerButton);
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
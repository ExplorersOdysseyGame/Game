package io.github.explorersodysseygame.game.server;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.menu.MenuButton;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ServerMenuScreen extends MenuScreen {
    public static final int GRID_SIZE = 20; // Pixel size of each square on the menu grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static ServerMenuScreen screen;

    public ServerMenuScreen(Main main) {
        super(main);
        int width = GRID_SIZE * GRID_ROWS - 1;
        int height = GRID_SIZE * GRID_COLUMNS;
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(255, 162, 162));
        this.setLayout(null);
        screen = this;

        BufferedImage IMG_host = Objects.requireNonNull(main.imageReader.read("menu/host.png")).getImage();

        MenuButton hostButton = new MenuButton(main, "Begin Hosting", new ImageIcon(IMG_host)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed %s menu button", this.getText()));
                ServerWindow.loadGame(ServerWindow.getWindowAsJFrame(), ServerMenuScreen.getScreen());

            }
        };
        hostButton.setBounds(GRID_SIZE * (GRID_ROWS/3), GRID_SIZE * 3, GRID_SIZE * (GRID_ROWS / 3), GRID_SIZE);

        this.add(hostButton);
    }

    public static ServerMenuScreen getScreen() {
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

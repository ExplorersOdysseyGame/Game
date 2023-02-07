package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.entity.Player;
import io.github.explorersodysseygame.game.common.util.Image.*;
import io.github.explorersodysseygame.game.common.util.Spritesheet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameScreen extends JPanel implements ActionListener, KeyListener {
    public static final int GRID_SIZE = 20; // Pixel size of each square on the game grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static Player player;

    private final InGameMenu inGameMenu;
    private static GameScreen screen;

    private static final ArrayList<int[]> chunkDecorationPositions = new ArrayList<>();
    private static final ArrayList<ImageClass> chunkDecorationImages = new ArrayList<>();

    private final int width;
    private final int height;
    public GameScreen(Main main) {
        player = new Player(main);
        width = (GRID_SIZE * GRID_ROWS + GRID_SIZE) - 4;
        height = (GRID_SIZE * GRID_COLUMNS + (GRID_SIZE * 2)) - 1;
        inGameMenu = new InGameMenu(main, new Dimension(width, height));
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

        SpritesheetClass cDSheet = main.spritesheetReader.read("entity/player.png");
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                Random rand = new Random();
                if (rand.nextInt(8) == 4) {
                    chunkDecorationPositions.add(new int[]{row * GRID_SIZE, col * GRID_SIZE});
                    chunkDecorationImages.add(cDSheet.getImage(rand.nextInt(cDSheet.getRows()),rand.nextInt(cDSheet.getCols())));
                }
            }
        }
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
        g.setColor(new Color(81, 128, 81));
        int iter = 0;
        for (int[] pos : chunkDecorationPositions) {
            g.drawImage(chunkDecorationImages.get(iter).getImage().getScaledInstance(GRID_SIZE, GRID_SIZE, Image.SCALE_FAST), pos[0], pos[1], new Color(162, 255, 162), null);
            iter += 1;
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

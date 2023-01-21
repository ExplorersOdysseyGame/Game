package io.github.explorersodysseygame.game.player;

import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.util.image.ImageReader;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;

import static io.github.explorersodysseygame.game.util.image.ImageReader.ImageMemory;

public class Player {

    private BufferedImage image;
    private Point pos;

    public Player() {
        ImageReader.read("player.png");
        loadImage();

        pos = new Point(0, 0);
    }

    private void loadImage() {
        image = ImageMemory.findImage("player.png");
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                pos.x * GameScreen.GRID_SIZE,
                pos.y * GameScreen.GRID_SIZE,
                observer
        );
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            pos.translate(-1, 0);
        }
    }

    public void tick() {
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= GameScreen.GRID_COLUMNS) {
            pos.x = GameScreen.GRID_COLUMNS - 1;
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= GameScreen.GRID_ROWS) {
            pos.y = GameScreen.GRID_ROWS - 1;
        }
    }

    public Point getPos() {
        return pos;
    }

}
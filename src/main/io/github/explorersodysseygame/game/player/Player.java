package io.github.explorersodysseygame.game.player;

import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.util.image.ImageReader;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;

public class Player {

    // image that represents the player's position on the board
    private BufferedImage image;
    // current position of the player on the board grid
    private Point pos;

    public Player() {
        // load the assets
        loadImage();

        // initialize the state
        pos = new Point(0, 0);
    }

    private void loadImage() {
        image = ImageReader.read("player.png");
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
        // every keyboard get has a certain code. get the value of that code from the
        // keyboard event so that we can compare it to KeyEvent constants
        int key = e.getKeyCode();

        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP) {
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN) {
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT) {
            pos.translate(-1, 0);
        }
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= GameScreen.GRID_COLUMNS) {
            pos.x = GameScreen.GRID_COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
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
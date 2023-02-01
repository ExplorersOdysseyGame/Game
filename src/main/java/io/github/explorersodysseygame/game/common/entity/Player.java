package io.github.explorersodysseygame.game.common.entity;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.data.EntityData;
import io.github.explorersodysseygame.game.common.util.Spritesheet.SpritesheetReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Objects;

import static io.github.explorersodysseygame.game.client.renderer.GameScreen.GRID_SIZE;
import static io.github.explorersodysseygame.game.common.util.Spritesheet.SpritesheetReader.SpritesheetMemory;

public class Player {

    private Image image;
    private final Point pos;

    protected String lastDir; // NESW
    private Color hairColour = new Color(126, 70, 3);
    private Color skinColour = new Color(222, 169, 107);
    private Color shirtColour = new Color(134, 0, 95);
    private Color shoeColour = new Color(26, 26, 26);

    private boolean altFrame = false;
    private int curTick = 0;
    private boolean canMove = true;

    public void changeColour(Color to, String type) {
        if (Objects.equals(type, "Hair")) {hairColour = to.darker();}
        else if (Objects.equals(type, "Skin")) {skinColour = to;}
        else if (Objects.equals(type, "Shirt")) {shirtColour = to;}
        else if (Objects.equals(type, "Shoe")) {shoeColour = to.darker().darker().darker();}
        else {
            Main.log("Player unable to change colour for any element, no element given");}
    }

    public Player() {
        EntityData data = new EntityData("player");
        SpritesheetReader.read("entity/player.png");
        loadImage(false);

        pos = new Point(0, 0);
    }

    public Image getImage() { return image; }
    public Color[] getColorData() {return new Color[]{hairColour, skinColour, shirtColour, shoeColour};}

    public void updateImage() {
        loadImage(false);
    }

    private void loadImage(boolean isMoving) {
        int row;int col;
        if (isMoving) {
            row = 0;
            if (altFrame) {col = 2;} else {col = 1;}
            if (Objects.equals(lastDir, "n")) {
                row = 1;
            }
            else if (Objects.equals(lastDir, "e")) {
                row = 2;
            }
            else if (Objects.equals(lastDir, "w")) {
                row = 3;
            }
        } else { col = 0; row = 0; }

        image = SpritesheetMemory.findSheet("entity/player.png").getImage(row, col)
                .changeColour(new Color(42, 255, 0).getRGB(), hairColour.getRGB())
                .changeColour(new Color(255, 0, 0).getRGB(), skinColour.getRGB())
                .changeColour(new Color(165, 0, 255).getRGB(), shirtColour.getRGB())
                .changeColour(new Color(0, 203, 255).getRGB(), shoeColour.getRGB())
                .getImage().getScaledInstance(GRID_SIZE, GRID_SIZE, Image.SCALE_FAST);
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                pos.x * GRID_SIZE,
                pos.y * GRID_SIZE,
                observer
        );
    }

    private void move(int dx, int dy) {
        pos.translate(dx, dy);
        canMove = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (canMove) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                move(0, -1);
                lastDir = "n";
                loadImage(true);
            }
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                move(1, 0);
                lastDir = "e";
                loadImage(true);
            }
            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                move(0, 1);
                lastDir = "s";
                loadImage(true);
            }
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                move(-1, 0);
                lastDir = "w";
                loadImage(true);
            }
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
        curTick += 1;
        if (curTick >= 10) {
            altFrame = !altFrame;
            curTick = 0;
            canMove = true;
        }
    }

    public Point getPos() {
        return pos;
    }

}
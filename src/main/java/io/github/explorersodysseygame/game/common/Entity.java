package io.github.explorersodysseygame.game.common;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.game.GameScreen;
import io.github.explorersodysseygame.game.common.data.EntityData;
import io.github.explorersodysseygame.game.common.util.Image.*;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Objects;

import static io.github.explorersodysseygame.game.client.game.GameScreen.GRID_SIZE;

public class Entity {

    private Image image;
    private final Point pos;

    protected String lastDir; // NESW

    private boolean altFrame = false;
    private int curTick = 0;
    public boolean canMove = true;

    private ArrayList<Color[]> switchedColours = new ArrayList<>();

    private final String name;
    private final Main main;

    public void setSwitchedColours(ArrayList<Color[]> arg) {switchedColours = arg;}

    public Entity(Main main, String name) {
        this.main = main;
        this.name = name;
        EntityData data = new EntityData(name);
        main.spritesheetReader.read(String.format("game/entity/%s.png", name));
        loadImage(false);

        this.pos = new Point(0, 0);
    }

    public Image getImage() { return image; }

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

        ImageClass tempimage = main.spritesheetReader.getMemory().findSheet(String.format("game/entity/%s.png", name)).getImage(row, col);
        for (Color[] swc : switchedColours) {
            tempimage = tempimage.changeColour(swc[0].getRGB(), swc[1].getRGB());
        }
        image = tempimage.getImage().getScaledInstance(GRID_SIZE, GRID_SIZE, Image.SCALE_FAST);
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                pos.x * GRID_SIZE,
                pos.y * GRID_SIZE,
                observer
        );
    }

    protected void move(int dx, int dy) {
        pos.translate(dx, dy);
        canMove = false;
    }

    public void moveOperation(String dir) {
        if (canMove) {
            if (Objects.equals(dir, "n")) {
                move(0, -1);
                lastDir = "n";
                loadImage(true);
            }
            if (Objects.equals(dir, "e")) {
                move(1, 0);
                lastDir = "e";
                loadImage(true);
            }
            if (Objects.equals(dir, "s")) {
                move(0, 1);
                lastDir = "s";
                loadImage(true);
            }
            if (Objects.equals(dir, "w")) {
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
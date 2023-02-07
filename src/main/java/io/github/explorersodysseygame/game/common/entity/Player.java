package io.github.explorersodysseygame.game.common.entity;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;


public class Player {

    private Image image;

    protected String lastDir; // NESW
    private Color hairColour = new Color(126, 70, 3);
    private Color skinColour = new Color(222, 169, 107);
    private Color shirtColour = new Color(134, 0, 95);
    private Color shoeColour = new Color(26, 26, 26);

    private final ArrayList<Color[]> switchedColours = new ArrayList<>();

    private final Entity entity;

    public void changeColour(Color to, String type) {
        if (Objects.equals(type, "Hair")) {
            hairColour = to.darker();
        } else if (Objects.equals(type, "Skin")) {
            skinColour = to;
        } else if (Objects.equals(type, "Shirt")) {
            shirtColour = to;
        } else if (Objects.equals(type, "Shoe")) {
            shoeColour = to.darker().darker().darker();
        } else {
            Main.log("Player unable to change colour for any element, incorrect element given");
        }
        updateSwitchedColours();
    }
    public void updateSwitchedColours() {
        switchedColours.clear();
        switchedColours.add(new Color[]{new Color(42, 255, 0), hairColour});
        switchedColours.add(new Color[]{new Color(255, 0, 0), skinColour});
        switchedColours.add(new Color[]{new Color(165, 0, 255), shirtColour});
        switchedColours.add(new Color[]{new Color(0, 203, 255), shoeColour});
        this.entity.setSwitchedColours(switchedColours);
    }

    public Entity getEntity() {return entity;}

    public Player(Main main) {
        this.entity = new Entity(main, "player");
    }

    public Color[] getColorData() {return new Color[]{hairColour, skinColour, shirtColour, shoeColour};}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (this.entity.canMove) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                this.entity.moveOperation("n");
            }
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                this.entity.moveOperation("e");
            }
            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                this.entity.moveOperation("s");
            }
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                this.entity.moveOperation("w");
            }
        }
    }
}
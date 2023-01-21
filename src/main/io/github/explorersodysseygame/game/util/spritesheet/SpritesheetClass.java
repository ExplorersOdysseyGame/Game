package io.github.explorersodysseygame.game.util.spritesheet;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SpritesheetClass {
    /*
     Spritesheet class for the game, made for SpritesheetMemory to be easily searchable.
     */
    public static BufferedImage sheet;
    public static String alias;

    public SpritesheetClass(BufferedImage img, String als) {
        sheet = img;
        alias = als;
    }

    public String getName() {
        return alias;
    }
    public BufferedImage getSheet() {
        return sheet;
    }
    public BufferedImage getImage(Integer row, Integer col) {
        return sheet.getSubimage((8*row)-8, (8*col)-8, 8*row, 8*col);
    }
}

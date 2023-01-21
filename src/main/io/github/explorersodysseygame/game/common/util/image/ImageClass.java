package io.github.explorersodysseygame.game.common.util.image;

import java.awt.image.BufferedImage;

public class ImageClass {
    /*
     Image class for the game, made for ImageMemory to be easily searchable.
     */
    public static BufferedImage image;
    public static String alias;

    public ImageClass(BufferedImage img, String als) {
        image = img;
        alias = als;
    }

    public String getName() {
        return alias;
    }
    public BufferedImage getImage() {
        return image;
    }
}

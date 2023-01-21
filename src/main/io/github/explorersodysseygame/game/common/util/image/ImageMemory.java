package io.github.explorersodysseygame.game.common.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageMemory extends ArrayList<ImageClass> {
    /*
     Image memory for the entire game, cleared on game end.
    */
    public BufferedImage findImage(String name) {
        for (ImageClass imageClass : this) {
            String s = imageClass.getName();
            //search the string
            if (name.equals(s)) {
                return imageClass.getImage();
            }
        }
        return null;
    }
}

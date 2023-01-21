package io.github.explorersodysseygame.game.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageMemory extends ArrayList<ImageClass> {
    /*
     Image memory for the entire game, cleared on game end.
    */
    public BufferedImage findImage(String name) {
        for(int i=0; i < this.size(); i++) {
            String s = this.get(i).getName();
            //search the string
            if(name.equals(s)) {
                return this.get(i).getImage();
            }
        }
        return null;
    }
}

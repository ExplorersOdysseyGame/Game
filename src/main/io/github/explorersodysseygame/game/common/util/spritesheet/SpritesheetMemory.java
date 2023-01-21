package io.github.explorersodysseygame.game.common.util.spritesheet;

import java.util.ArrayList;

public class SpritesheetMemory extends ArrayList<SpritesheetClass> {
    /*
     Spritesheet memory for the entire game, cleared on game end.
    */
    public SpritesheetClass findSheet(String name) {
        for (SpritesheetClass spritesheetClass : this) {
            String s = spritesheetClass.getName();
            //search the string
            if (name.equals(s)) {
                return spritesheetClass;
            }
        }
        return null;
    }
}

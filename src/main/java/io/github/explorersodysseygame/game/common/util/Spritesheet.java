package io.github.explorersodysseygame.game.common.util;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;
import io.github.explorersodysseygame.game.common.util.Image.ImageClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Spritesheet {

    public static class SpritesheetClass {
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
        public ImageClass getImage(Integer row, Integer col) {
            return new ImageClass(sheet.getSubimage((8*row)-8, (8*col)-8, (8*row), (8*col)), "spritesheetSelection");
        }
    }

    public static class SpritesheetMemory extends ArrayList<SpritesheetClass> {
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

        public ArrayList<String> outputMemory() {
            ArrayList<String> out = new ArrayList<>();
            for (SpritesheetClass spritesheetClass : this) {
                out.add(spritesheetClass.getName());
            }
            Main.log(String.valueOf(out));
            return out;
        }
    }

    public static class SpritesheetReader {
        /*
         Reads a spritesheet from the resources directory and saves it to SpritesheetMemory.
         */
        public static final SpritesheetMemory SpritesheetMemory = new SpritesheetMemory();

        @SuppressWarnings("UnusedReturnValue")
        public static SpritesheetClass read(String file) {
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
                SpritesheetClass sprClass = new SpritesheetClass(image, file);
                SpritesheetMemory.add(sprClass);
                Main.log(String.format("Loaded spritesheet file: %s", file));
                return sprClass;
            } catch (Exception exc) {
                Main.log(String.format("Error opening spritesheet file '%s': %s", file, detailError(exc.getMessage())));
                try {
                    return new SpritesheetClass(ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource("/resources/notfound.png"))), "notfound.png");
                } catch (Exception exc1) {
                    Main.log(String.format("Unable to load fallback image 'notfound.png': %s", detailError(exc1.getMessage())));
                    Main.log("Force-stopping game");
                    Main.stopGame();
                }
            }
            return null;
        }

        private static String detailError(String exc) {
            if (exc == null) {
                return "Spritesheet not found";
            }
            return exc;
        }
    }

}

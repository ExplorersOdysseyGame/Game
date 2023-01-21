package io.github.explorersodysseygame.game.common.util.spritesheet;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.util.image.ImageReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpritesheetReader extends ImageReader {
    /*
     Reads a spritesheet from the resources directory and saves it to SpritesheetMemory.
     */
    public static SpritesheetMemory SpritesheetMemory = new SpritesheetMemory();

    public static java.awt.image.BufferedImage read(String file) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
            SpritesheetClass sprClass = new SpritesheetClass(image, file);
            SpritesheetMemory.add(sprClass);
            Main.log(String.format("Loaded spritesheet file: %s", file));
            return image;
        } catch (Exception exc) {
            Main.log(String.format("Error opening spritesheet file '%s': %s", file, detailError(exc.getMessage())));
            try {
                return ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource("/resources/notfound.png")));
            } catch (Exception exc1) {
                Main.log(String.format("Unable to load fallback image 'notfound.png': %s", detailError(exc1.getMessage())));
                Main.log(String.format("Force-stopping game"));
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

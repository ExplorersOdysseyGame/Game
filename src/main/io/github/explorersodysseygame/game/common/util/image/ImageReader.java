package io.github.explorersodysseygame.game.common.util.image;

import io.github.explorersodysseygame.game.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageReader {
    /*
     Reads an image from the resources directory and saves it to ImageMemory.
     */

    public static ImageMemory ImageMemory = new ImageMemory();

    public static java.awt.image.BufferedImage read(String file) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
            ImageClass imgClass = new ImageClass(image, file);
            ImageMemory.add(imgClass);
            Main.log(String.format("Loaded image file: %s", file));
            return image;
        } catch (Exception exc) {
            Main.log(String.format("Error opening image file '%s': %s", file, detailError(exc.getMessage())));
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
            return "Image not found";
        }
        return exc;
    }
}

package io.github.explorersodysseygame.game.util.image;

import io.github.explorersodysseygame.game.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class ImageReader {

    public static ArrayList<ImageClass> ImageMemory = new ArrayList<ImageClass>();

    public static java.awt.image.BufferedImage read(String file) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
            ImageClass imgClass = new ImageClass(image, file);
            ImageMemory.add(imgClass);
            Main.log(String.format("Loaded image file: %s", file));
            return image;
        } catch (Exception exc) {
            Main.log(String.format("Error opening image file '%s': %s", file, exc.getMessage()));
        }
        return null;
    }
}

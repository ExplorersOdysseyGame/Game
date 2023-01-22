package io.github.explorersodysseygame.game.common.util;

import io.github.explorersodysseygame.game.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Image {

    public static class ImageClass {
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

    public static class ImageMemory extends ArrayList<ImageClass> {
        /*
         Image memory for the entire game, cleared on game end.
        */
        public ImageClass findImage(String name) {
            for (ImageClass imageClass : this) {
                String s = imageClass.getName();
                if (name.equals(s)) {
                    return imageClass;
                }
            }
            return new ImageClass(ImageReader.readWithoutSave("notfound.png"), "notfound.png");
        }

        public ImageMemory outputMemory() {
            Main.log(String.valueOf(this));
            return this;
        }
    }

    public static class ImageReader {
        /*
         Reads an image from the resources directory and saves it to ImageMemory.
         */

        public static final ImageMemory ImageMemory = new ImageMemory();

        @SuppressWarnings("SameReturnValue")
        public static BufferedImage read(String file) {
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
                    Main.log("Force-stopping game");
                    Main.stopGame();
                }
            }
            return null;
        }
        public static BufferedImage readWithoutSave(String file) {
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
                ImageClass imgClass = new ImageClass(image, file);
                Main.log(String.format("Loaded image file without saving: %s", file));
                return image;
            } catch (Exception exc) {
                Main.log(String.format("Error opening image file '%s': %s", file, detailError(exc.getMessage())));
                try {
                    return ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource("/resources/notfound.png")));
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
                return "Image not found";
            }
            return exc;
        }
    }
}

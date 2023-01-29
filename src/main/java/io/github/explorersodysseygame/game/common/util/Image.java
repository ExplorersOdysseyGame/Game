package io.github.explorersodysseygame.game.common.util;

import io.github.explorersodysseygame.game.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

import static io.github.explorersodysseygame.game.Main.ImageMemory;

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
        public ImageClass changeColour(int from, int to) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int color = image.getRGB(i, j);
                    if (color == from) {
                        newImage.setRGB(i, j, to);
                    }
                    else {
                        newImage.setRGB(i, j, color);
                    }
                }
            }
            return new ImageClass(newImage, "editedImage");
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
            try {
                return new ImageClass(ImageIO.read(Objects.requireNonNull(ImageMemory.class.getResource("/resources/notfound.png"))), "notfound.png");
            } catch (Exception exc) {
                return null;
            }
        }

        @SuppressWarnings("UnusedReturnValue")
        public ArrayList<String> outputMemory() {
            ArrayList<String> out = new ArrayList<>();
            for (ImageClass imageClass : this) {
                out.add(imageClass.getName());
            }
            Main.log(String.valueOf(out));
            return out;
        }
    }

    public static class ImageReader {
        /*
         Reads an image from the resources directory and saves it to ImageMemory.
         */

        public static ImageClass read(String file) {
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource(String.format("/resources/%s", file))));
                ImageClass imgClass = new ImageClass(image, file);
                ImageMemory.add(imgClass);
                return imgClass;
            } catch (Exception exc) {
                Main.log(String.format("Error opening image file '%s': %s", file, detailError(exc.getMessage())));
                try {
                    return new ImageClass(ImageIO.read(Objects.requireNonNull(ImageReader.class.getResource("/resources/notfound.png"))), "notfound.png");
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

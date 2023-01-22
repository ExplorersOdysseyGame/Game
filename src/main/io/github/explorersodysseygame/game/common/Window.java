package io.github.explorersodysseygame.game.common;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;

import static io.github.explorersodysseygame.game.common.util.Image.ImageReader.ImageMemory;

public class Window {
    public static JFrame window;

    private static void initWindow() {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageReader.read("icon.png");
        window.setIconImage(ImageMemory.findImage("icon.png"));

        window.setSize(256, 384);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(Main::stopGame, "Shutdown-thread"));
    }

    public static JFrame getWindowAsJFrame() {
        return window;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::initWindow);
    }
}

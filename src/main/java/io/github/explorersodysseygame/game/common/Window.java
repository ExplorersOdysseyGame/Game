package io.github.explorersodysseygame.game.common;

import io.github.explorersodysseygame.game.Main;

import javax.swing.*;

public class Window {
    public static JFrame window;

    private static void initWindow(Main main) {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.imageReader.read("misc/icon.png");
        window.setIconImage(main.imageReader.getMemory().findImage("misc/icon.png").getImage());

        window.setSize(256, 384);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(new Main()::stopGame, "Shutdown-thread"));
    }

    public static JFrame getWindowAsJFrame() {
        return window;
    }

    public static void main(Main main, String[] args) {
        SwingUtilities.invokeLater(() -> initWindow(main));
    }
}

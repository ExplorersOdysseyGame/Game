package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.client.menu.MenuScreen;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;

import javax.swing.*;

public class Window {

    public static JFrame window;

    private static void initWindow() {
        window = new JFrame(Client.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MenuScreen menu = new MenuScreen();
        window.add(menu);
        window.setSize(menu.getWidth(), menu.getHeight());
        window.addKeyListener(menu);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(Client::stopGame, "Shutdown-thread"));
    }

    public static JFrame getWindowAsJFrame() {
        return window;
    }

    public static void loadGame(JFrame window, MenuScreen menu) {
        GameScreen gameScreen = new GameScreen();
        gameScreen.setVisible(true);
        Client.log("Loading game");
        Client.log("Hiding start menu");
        window.remove(menu);
        Client.log("Start menu hidden");
        Client.log("Showing game screen");
        window.add(gameScreen);
        Client.log("Resizing window to fit game screen");
        window.setSize(gameScreen.getWidth(), gameScreen.getHeight());
        Client.log("Adding key listener to game screen");
        window.addKeyListener(gameScreen);
        gameScreen.repaint();
        Client.log("Loaded game");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> initWindow());
    }
}

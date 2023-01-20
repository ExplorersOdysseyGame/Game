package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.client.menu.MenuScreen;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;

import javax.swing.*;

public class Window {
    private static void initWindow() {
        JFrame window = new JFrame(Client.gameName);
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

    public static void loadGame(JFrame window, GameScreen board, MenuScreen menu) {
        window.remove(menu);
        window.add(board);
        window.setSize(board.getWidth(), board.getHeight());
        window.addKeyListener(board);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> initWindow());
    }
}

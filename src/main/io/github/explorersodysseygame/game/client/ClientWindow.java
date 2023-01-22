package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.Window;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;

import static io.github.explorersodysseygame.game.Main.ImageMemory;

public class ClientWindow extends Window {

    public static JFrame window;

    private static void initWindow() {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageReader.read("icon.png");
        window.setIconImage(ImageMemory.findImage("icon.png").getImage());

        MenuScreen menu = new ClientMenuScreen();
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
        GameScreen gameScreen = GameScreen.getScreen();
        gameScreen.setVisible(true);
        Client.log("Loading game");
        Client.log("Hiding start menu");
        menu.setVisible(false);
        Client.log("Showing game screen");
        window.add(gameScreen);
        Client.log("Adding key listener to game screen");
        window.removeKeyListener(menu);
        window.addKeyListener(gameScreen);
        gameScreen.repaint();
        Client.log("Loaded game");
    }
    public static void exitGame(JFrame window, GameScreen game) {
        MenuScreen menuScreen = ClientMenuScreen.getScreen();
        menuScreen.setVisible(true);
        game.toggleInGameMenu();
        Client.log("Loading menu");
        Client.log("Hiding game screen");
        game.setVisible(false);
        Client.log("Showing menu screen");
        window.add(menuScreen);
        Client.log("Adding key listener to menu screen");
        window.removeKeyListener(game);
        window.addKeyListener(menuScreen);
        menuScreen.repaint();
        Client.log("Loaded menu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientWindow::initWindow);
    }
}
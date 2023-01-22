package io.github.explorersodysseygame.game.server;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.Window;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;

import static io.github.explorersodysseygame.game.common.util.Image.ImageReader.ImageMemory;

public class ServerWindow extends Window {

    public static JFrame window;

    private static void initWindow() {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageReader.read("icon.png");
        window.setIconImage(ImageMemory.findImage("icon.png").getImage());

        MenuScreen menu = new ServerMenuScreen();
        window.add(menu);
        window.setSize(menu.getWidth(), menu.getHeight());
        window.addKeyListener(menu);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(Server::stopGame, "Shutdown-thread"));
    }

    public static JFrame getWindowAsJFrame() {
        return window;
    }

    public static void loadGame(JFrame window, MenuScreen menu) {
        // TODO: Server game loading
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServerWindow::initWindow);
    }
}

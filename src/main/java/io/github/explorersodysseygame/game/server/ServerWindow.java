package io.github.explorersodysseygame.game.server;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.Window;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;

import javax.swing.*;

public class ServerWindow extends Window {

    public static JFrame window;

    private void initWindow(Main main) {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.imageReader.read("misc/icon.png");
        window.setIconImage(main.imageReader.getMemory().findImage("misc/icon.png").getImage());

        MenuScreen menu = new ServerMenuScreen(main);
        window.add(menu);
        window.setSize(menu.getWidth(), menu.getHeight());
        window.addKeyListener(menu);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(new Main()::stopGame, "Shutdown-thread"));
    }

    public static JFrame getWindowAsJFrame() {
        return window;
    }

    public static void loadGame(JFrame window, MenuScreen menu) {
        // TODO: Server game loading
        //  This will have the server begin listening on a port and IP or localhost depending on user option.
        //  Before doing this, the Client must have a multiplayer connection option.
    }

    public static void main(Main main, String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        SwingUtilities.invokeLater(() -> serverWindow.initWindow(main));
    }
}

package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.logger.Logger;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;

public class Client extends Main {

    public static final ClientWindow CLIENT_WINDOW = new ClientWindow();

    public static void main(String[] args) {

        // Begin game
        MenuScreen menuScreen = new MenuScreen();
        GameScreen gameScreen = new GameScreen();
        Logger.log(String.format("Starting ExplorersOdyssey v%s", versionString));
        runInstance(args);
        ClientWindow.main(args);

    }

    @SuppressWarnings("SameReturnValue")
    public static ClientWindow getWindow() {
        return CLIENT_WINDOW;
    }

    public static void runInstance(String[] args) {
        Logger.log("Running ExplorersOdyssey client");
    }
}
package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.game.GameScreen;
import io.github.explorersodysseygame.game.common.logger.Logger;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;

import static io.github.explorersodysseygame.game.Main.gameID;
import static io.github.explorersodysseygame.game.Main.versionString;

public class Client {

    public static final ClientWindow CLIENT_WINDOW = new ClientWindow();

    public static final Logger logger = new Logger(gameID+":client");

    public static void main(String[] args) {
        new Client().main2(new Main(), args);
    }

    public void main2(Main main, String[] args) {

        // Begin game
        MenuScreen menuScreen = new MenuScreen(main);
        GameScreen gameScreen = new GameScreen(main);
        log(String.format("Starting ExplorersOdyssey v%s", versionString));
        runInstance(args);
        ClientWindow.main(main, args);

    }

    public static void log(String log) {
        logger.log(log);
    }

    @SuppressWarnings("SameReturnValue")
    public static ClientWindow getWindow() {
        return CLIENT_WINDOW;
    }

    public static void runInstance(String[] args) {
        log("Running ExplorersOdyssey client");
    }
}
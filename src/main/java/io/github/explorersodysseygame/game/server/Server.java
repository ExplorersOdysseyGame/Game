package io.github.explorersodysseygame.game.server;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.Client;
import io.github.explorersodysseygame.game.common.logger.Logger;

import static io.github.explorersodysseygame.game.Main.gameID;
import static io.github.explorersodysseygame.game.Main.versionString;

public class Server {
    public static final ServerWindow SERVER_WINDOW = new ServerWindow();

    public static final Logger logger = new Logger(gameID+":server");

    public static void main(String[] args) {
        new Client().main2(new Main(), args);
    }

    public void main2(Main main, String[] args) {

        // Begin game
        log(String.format("Starting ExplorersOdyssey v%s", versionString));
        Server.runInstance(args);
        ServerWindow.main(main, args);

    }

    public static void log(String log) { logger.log(log); }

    @SuppressWarnings("SameReturnValue")
    public static ServerWindow getWindow() {
            return SERVER_WINDOW;
        }
    public static void runInstance(String[] args) {
            log("Running ExplorersOdyssey server");
        }
}

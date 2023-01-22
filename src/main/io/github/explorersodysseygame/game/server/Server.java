package io.github.explorersodysseygame.game.server;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.server.ServerWindow;
import io.github.explorersodysseygame.game.common.logger.Logger;

public class Server extends Main {
    public static final ServerWindow SERVER_WINDOW = new ServerWindow();

    public static void main(String[] args) {

        // Begin game
        Logger.log(String.format("Starting ExplorersOdyssey v%s", versionString));
        runInstance(args);
        ServerWindow.main(args);

    }

    @SuppressWarnings("SameReturnValue")
    public static ServerWindow getWindow() {
            return SERVER_WINDOW;
        }
    public static void runInstance(String[] args) {
            Logger.log("Running ExplorersOdyssey server");
        }
}

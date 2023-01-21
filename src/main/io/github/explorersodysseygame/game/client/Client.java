package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.logger.Logger;

public class Client extends Main {

    public static final Window WINDOW = new Window();

    public static void main(String[] args) {

        // Begin game
        Logger.log(String.format("Starting ExplorersOdyssey v%s", versionString));
        runInstance(args);
        Window.main(args);

    }

    public static Window getWindow() {
        return WINDOW;
    }

    public static void runInstance(String[] args) {
        Logger.log("Running ExplorersOdyssey client");
    }
}
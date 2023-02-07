package io.github.explorersodysseygame.game;

import io.github.explorersodysseygame.game.common.Logger;
import io.github.explorersodysseygame.game.common.util.Image;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;
import io.github.explorersodysseygame.game.common.util.Spritesheet;
import io.github.explorersodysseygame.game.common.util.Spritesheet.SpritesheetReader;

public class Main {
    public static final String gameName = "ExplorersOdyssey"; // Name of the game. Do not change, this is mainly for referencing the name in other classes.
    public static final String gameID = gameName.toLowerCase(); // Lowercase version of the game name.
    public static final String gameType = "vanilla"; // CHANGE TO SOMETHING ELSE IN MODDED VERSIONS

    public static final Logger logger = new Logger(gameID);
    public static final String versionString = "0.0.1-prealpha-1"; // Semantic versioning. Uses a basic version (e.g. 1.2.3) then a status (prealpha, alpha, beta, rc) then a status version.
    public static final int versionNumeric = 1; // The numeric version. Increment this every time versionString changes.

    public final SpritesheetReader spritesheetReader = new Spritesheet().new SpritesheetReader();
    public final ImageReader imageReader = new Image().new ImageReader();

    public static void main(String[] args) {new Main().main2(args);}

    public void main2(String[] args) {

        // Begin game
        log(String.format("Starting ExplorersOdyssey v%s (nv-%s)", versionString, versionNumeric));
        log("Running through Main class! This is not supported, so please run using the Client or Server class");

    }

    public static void log(String log) {
        logger.log(log);
    }

    public void stopGame() {
        log("Stopping ExplorersOdyssey");
        log("Clearing ImageMemory");
        imageReader.getMemory().clear();
        log("Cleared ImageMemory");
        log("Clearing SpritesheetMemory");
        spritesheetReader.getMemory().clear();
        log("Cleared SpritesheetMemory");
        log("Stopped ExplorersOdyssey");
    }
}

package io.github.explorersodysseygame.game;

import io.github.explorersodysseygame.game.logger.Logger;
import io.github.explorersodysseygame.game.util.image.ImageReader;
import io.github.explorersodysseygame.game.util.spritesheet.SpritesheetReader;

public class Main {
    public static String gameName = "ExplorersOdyssey"; // Name of the game. Do not change, this is mainly for referencing the name in other classes.
    public static String gameID = gameName.toLowerCase(); // Lowercase version of the game name.
    public static String gameType = "vanilla"; // CHANGE TO SOMETHING ELSE IN MODDED VERSIONS

    public static Logger logger = new Logger(gameID);
    public static String versionString = "0.0.1-prealpha-1"; // Semantic versioning. Uses a basic version (eg. 1.2.3) then a status (prealpha, alpha, beta, rc) then a status version.
    public static int versionNumeric = 1; // The numeric version. Increment this every time versionString changes.
    public static void main(String[] args) {

        // Begin game
        Logger.log(String.format("Starting ExplorersOdyssey v%s (nv-%s)", versionString, versionNumeric));
        runInstance(args);

    }

    public static void log(String log) {
        Logger.log(log);
    }

    public static void runInstance(String[] args) {
        Logger.log("Running through Main class! This is not allowed, so please run using the Client or Server class");
    }

    public static void stopGame() {
        Logger.log("Stopping ExplorersOdyssey");
        Logger.log("Clearing ImageMemory");
        ImageReader.ImageMemory.clear();
        Logger.log("Cleared ImageMemory");
        Logger.log("Clearing SpritesheetMemory");
        SpritesheetReader.SpritesheetMemory.clear();
        Logger.log("Cleared SpritesheetMemory");
        Logger.log("Stopped ExplorersOdyssey");
    }
}

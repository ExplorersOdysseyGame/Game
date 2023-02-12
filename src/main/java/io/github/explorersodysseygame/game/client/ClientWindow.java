package io.github.explorersodysseygame.game.client;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.menu.MenuScreen;
import io.github.explorersodysseygame.game.client.game.GameScreen;
import io.github.explorersodysseygame.game.common.Window;
import io.github.explorersodysseygame.game.common.ui.Popup.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ClientWindow extends Window {

    public static JFrame window;

    private static void initWindow(Main main) {
        window = new JFrame(Main.gameName);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.imageReader.read("misc/icon.png");
        window.setIconImage(main.imageReader.getMemory().findImage("misc/icon.png").getImage());

        MenuScreen menu = new ClientMenuScreen(main);
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

    public static void loadSingleplayerGame(JFrame window, MenuScreen menu) {
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

    static void multiplayerDetailEntryResponseFunction(TwoEntryPopup popup, MenuScreen menu, JFrame window) {
            Client.log(Arrays.toString(popup.responses));
            Client.log("Validating input");
            String serverIP = popup.responses[0];
            String username = popup.responses[1];
            if (Pattern.matches("[0-9]+(.[0-9]+)+:[0-9]+", serverIP)) {
                Client.log("Given IP address matches regex verification");
                if (username.length() > 5 && username.length() < 25 && Pattern.matches("^[A-Z|a-z][A-Z|a-z|0-9]+", username)) {
                    Client.log("Username matches length and regex verification");
                    Client.log("Pinging address");
                    try {
                        HttpURLConnection pingConnection = (HttpURLConnection) new URL("http://"+serverIP).openConnection();
                        if (pingConnection.getResponseCode() == 200) {
                            Client.log("Address is reachable.");
                            BufferedReader br = new BufferedReader(new InputStreamReader((pingConnection.getInputStream())));
                            StringBuilder sb = new StringBuilder();
                            String output;
                            while ((output = br.readLine()) != null) {
                              sb.append(output);
                            }
                            String content = sb.toString();
                            if (content.startsWith("<!")) {
                                Client.log("Address is a HTTP(s) server, not an Explorer's Odyssey server");
                            } else {
                                // TODO: Basic Multiplayer Support
                                //  This section requires handling of server data, thus meaning the Server must be made first.
                                Client.log("Hiding start menu");
                                menu.setVisible(false);
                            }
                        }
                    } catch (IOException ex) {
                        Client.log(String.format("Error whilst pinging server: %s", ex.getMessage()));
                    }
                } else {
                    if (username.length() < 5) { Client.log("Username too short"); }
                    else if (username.length() > 25) { Client.log("Username too long"); }
                    else { Client.log("Invald username"); }
                }
            } else {
                Client.log(String.format("Invalid IP address: %s", serverIP));
            }
            /*
            Client.log("Showing game screen");
            window.add(gameScreen);
            Client.log("Adding key listener to game screen");
            window.removeKeyListener(menu);
            window.addKeyListener(gameScreen);
            gameScreen.repaint();
            Client.log("Loaded game");
             */
        }
    public static void loadMultiplayerGame(JFrame window, MenuScreen menu) {
        GameScreen gameScreen = GameScreen.getScreen();
        gameScreen.setVisible(true);
        Client.log("Loading game");
        Client.log("Prompting for server details");
        TwoEntryPopup popup = new TwoEntryPopup("Server Information", "Address (eg. 1.2.3.4:5678)", "Username (5-25 characters)");
        popup.setOnResponse(() -> multiplayerDetailEntryResponseFunction(popup, menu, window));
        Client.log("Waiting for server detail entry");
    }
    public static void exitGame(JFrame window, GameScreen game) {
        MenuScreen menuScreen = ClientMenuScreen.getScreen();
        menuScreen.setVisible(true);
        game.toggleInGameMenu();
        Client.log("Loading menu");
        Client.log("Hiding game screen");
        game.setVisible(false);
        window.remove(game);
        Client.log("Showing menu screen");
        window.add(menuScreen);
        Client.log("Adding key listener to menu screen");
        window.removeKeyListener(game);
        window.addKeyListener(menuScreen);
        menuScreen.repaint();
        Client.log("Loaded menu");
    }

    public static void main(Main main, String[] args) {
        SwingUtilities.invokeLater(() -> initWindow(main));
    }
}
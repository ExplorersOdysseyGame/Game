package io.github.explorersodysseygame.game.common.menu;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MenuScreen extends JPanel implements ActionListener, KeyListener {

    private static class SocialButtonSelectedUI extends MenuButton.MenuButtonSelectedUI {
        SocialButtonSelectedUI(Color color) {
            this.selectColor = Objects.requireNonNull(color);
        }
    }
    public static final int GRID_SIZE = 20; // Pixel size of each square on the menu grid.
    public static final int GRID_ROWS = 24; // Amount to multiply GRID_SIZE by for width.
    public static final int GRID_COLUMNS = GRID_ROWS; // Window is always square, so this is GRID_ROWS
    public static MenuScreen screen;

    private int width;
    private int height;
    public MenuScreen() {
        int width = GRID_SIZE * GRID_ROWS - 1;
        int height = GRID_SIZE * GRID_COLUMNS;
        Color BGCOLOUR = new Color(255, 162, 162);
        setPreferredSize(new Dimension(width, height));
        setBackground(BGCOLOUR);
        setLayout(null);
        screen = this;

        BufferedImage IMG_discord = Objects.requireNonNull(ImageReader.read("social/discord.png")).getImage();
        BufferedImage IMG_reddit = Objects.requireNonNull(ImageReader.read("social/reddit.png")).getImage();

        MenuButton discordSocial = new MenuButton("", new ImageIcon(IMG_discord)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log("Pressed Discord menu button");
                try {
                    Desktop.getDesktop().browse(java.net.URI.create("https://discord.gg/2Ns2aNkYAS"));
                } catch (Exception exc) {
                    Main.log(String.format("Unable to open Discord social link: %s", exc.getMessage()));
                }
            }
        };
        discordSocial.setBackground(BGCOLOUR);
        discordSocial.setUI(new SocialButtonSelectedUI(BGCOLOUR));
        discordSocial.setBounds(width-37,height-37,32,32);
        add(discordSocial);

        MenuButton redditSocial = new MenuButton("", new ImageIcon(IMG_reddit)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log("Pressed Reddit menu button");
                try {
                    Desktop.getDesktop().browse(java.net.URI.create("https://www.reddit.com/r/explorersodyssey/"));
                } catch (Exception exc) {
                    Main.log(String.format("Unable to open Reddit social link: %s", exc.getMessage()));
                }
            }
        };
        redditSocial.setBackground(BGCOLOUR);
        redditSocial.setUI(new SocialButtonSelectedUI(BGCOLOUR));
        redditSocial.setBounds(5,height-37,32,32);
        add(redditSocial);
    }

    public static MenuScreen getScreen() {
        return screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

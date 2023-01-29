package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class AvatarMenu extends JPanel implements ActionListener {
    public AvatarMenu(Dimension screenDimensions) {
        int width = (int) (screenDimensions.getWidth() / 8) + 32;
        int height = (int) (screenDimensions.getHeight()) - 159;

        setName("Avatar Menu");

        BufferedImage IMG_menu = Objects.requireNonNull(ImageReader.read("menu/menu.png")).getImage();

        setPreferredSize(screenDimensions);
        setBounds(0, 0, screenDimensions.width, screenDimensions.height);
        setLayout(null);
        setVisible(false);
        setOpaque(true);
        setBackground(new Color(25, 25, 25));

        JPanel outerPanel = new JPanel();
        outerPanel.setName("Outer Panel");
        outerPanel.setPreferredSize(new Dimension(width, height));
        outerPanel.setBounds(screenDimensions.width-((width*3)+25), 60, width*3, height);
        outerPanel.setLayout(null);
        outerPanel.setBackground(new Color(45, 45, 45));
        add(outerPanel);

        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(new Color(60, 60, 60));
        innerPanel.setBounds(10, 10, (width*3)-20, height-20);
        innerPanel.setLayout(null);
        outerPanel.add(innerPanel);

        JPanel picBack = new JPanel();
        picBack.setBackground(new Color(60,60,60));
        picBack.setLayout(null);
        picBack.setBounds(5, 60, width, height);
        add(picBack);
        JLabel picture = new JLabel(new ImageIcon(GameScreen.player.getImage().getScaledInstance(width, width, Image.SCALE_FAST)));
        picture.setBackground(new Color(60, 60, 60));
        picture.setBounds(5, 5, width-10, height-10);
        picture.setLayout(null);
        picBack.add(picture);

        InGameMenu.IGMButton exitButton = new InGameMenu.IGMButton("Back", new ImageIcon(IMG_menu)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed avatar %s button", this.getText()));
                InGameMenu.avatarMenu.setVisible(false);InGameMenu.outerPanel.setVisible(true);
            }
        };
        exitButton.setBounds(5, 5, (width*3)-30, 20);
        exitButton.setBackground(new Color(75, 75, 75));
        innerPanel.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }
    public boolean isOpen() {
        return isVisible();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image plrImage = GameScreen.player.getImage();
        Toolkit.getDefaultToolkit().sync();
    }
}

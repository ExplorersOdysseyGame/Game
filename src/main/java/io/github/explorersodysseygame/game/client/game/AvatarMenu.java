package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.Client;
import io.github.explorersodysseygame.game.common.ui.PickerBar.BasicPickerBar;
import io.github.explorersodysseygame.game.common.ui.PickerBar.RainbowPickerBar;
import io.github.explorersodysseygame.game.common.ui.PickerBar.SkinshadePickerBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class AvatarMenu extends JPanel implements ActionListener {
    public AvatarMenu(Main main, Dimension screenDimensions) {
        int width = (int) (screenDimensions.getWidth() / 8) + 32;
        int height = (int) (screenDimensions.getHeight()) - 159;

        setName("Avatar Menu");

        BufferedImage IMG_menu = Objects.requireNonNull(main.imageReader.read("game/menu/menu.png")).getImage();
        BufferedImage IMG_refresh = Objects.requireNonNull(main.imageReader.read("game/menu/reload.png")).getImage();

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
        JLabel picture = new JLabel(new ImageIcon(GameScreen.player.getEntity().getImage().getScaledInstance(width, width, Image.SCALE_FAST)));
        picture.setBackground(new Color(60, 60, 60));
        picture.setBounds(5, 5, width-10, height-10);
        picture.setLayout(null);
        picBack.add(picture);

        InGameMenu.IGMButton exitButton = new InGameMenu.IGMButton(main, "Back", new ImageIcon(IMG_menu)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed avatar %s button", this.getText()));
                InGameMenu.avatarMenu.setVisible(false);InGameMenu.outerPanel.setVisible(true);
            }
        };
        exitButton.setBounds(5, 5, (width*3)-30, 20);
        exitButton.setBackground(new Color(75, 75, 75));
        innerPanel.add(exitButton);

        String[] barNames = {"Hair", "Skin", "Shirt", "Shoe"};
        JLabel[] barTitles = {new JLabel(), new JLabel(), new JLabel(), new JLabel()};
        BasicPickerBar[] barTypes = {new RainbowPickerBar(), new SkinshadePickerBar(), new RainbowPickerBar(), new RainbowPickerBar()};
        int barIndex = 0;
        for (String barName : barNames) {
            barTypes[barIndex].setBounds(5, (50*(barIndex+1))+5, (width*3)-55, 20);
            innerPanel.add(barTypes[barIndex]);
            int finalBarIndex = barIndex;
            InGameMenu.IGMButton tempReload = new InGameMenu.IGMButton(main, "", new ImageIcon(IMG_refresh)) {
                @Override public void actionPerformed(ActionEvent e) {
                    Color c = barTypes[finalBarIndex].getSelectedColor();
                    c = new Color(c.getRed(), c.getGreen(), c.getBlue());
                    GameScreen.player.changeColour(c, barName);
                    GameScreen.player.getEntity().updateImage();
                    picture.setIcon(new ImageIcon(GameScreen.player.getEntity().getImage().getScaledInstance(width, width, Image.SCALE_FAST)));
                }
            };
            tempReload.setBounds(barTypes[barIndex].getWidth()+10, (50*(barIndex+1))+5, 20, 20);
            tempReload.setBackground(new Color(75, 75, 75));
            innerPanel.add(tempReload);
            barTitles[barIndex].setText(String.format("%s Customisation", barName));
            barTitles[barIndex].setBounds(5, barTypes[barIndex].getY()-25, (width*3-55), 20);
            barTitles[barIndex].setForeground(new Color(255, 255, 255));
            innerPanel.add(barTitles[barIndex]);
            Client.log(String.format("Added tempReload, tempTitle, colourBar for %s", barName));
            Client.log(String.valueOf(barTitles[barIndex].getLocation()));
            barIndex += 1;
        }
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
        Image plrImage = GameScreen.player.getEntity().getImage();
        Toolkit.getDefaultToolkit().sync();
    }
}

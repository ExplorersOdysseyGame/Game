package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.common.entity.Player;
import io.github.explorersodysseygame.game.common.ui.PickerBar.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
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

        String[] sectionNames = {"Hair Customisation", "Skin Customisation", "Shirt Customisation", "Shoe Customisation", "Presets"};
        JLabel[] barTitles = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()};
        BasicPickerBar[] barTypes = {new AdvancedPickerBar(), new SkinshadePickerBar(), new RainbowPickerBar(), new AdvancedPickerBar(), null};
        int barIndex = 0;
        for (String sectionName : sectionNames) {
            barTitles[barIndex].setText(String.format("%s", sectionName));
            barTitles[barIndex].setBounds(5, (50 * (barIndex + 1)) - 20, (width*3-55), 20);
            barTitles[barIndex].setForeground(new Color(255, 255, 255));
            innerPanel.add(barTitles[barIndex]);
            if (barTypes[barIndex] != null) {
                barTypes[barIndex].setBounds(5, barTitles[barIndex].getY()+25, (width * 3) - 55, 20);
                innerPanel.add(barTypes[barIndex]);
                int finalBarIndex = barIndex;
                InGameMenu.IGMButton tempReload = new InGameMenu.IGMButton(main, "", new ImageIcon(IMG_refresh)) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color c = barTypes[finalBarIndex].getSelectedColor();
                        c = new Color(c.getRed(), c.getGreen(), c.getBlue());
                        GameScreen.player.changeColour(c, sectionName.split(" ")[0]);
                        GameScreen.player.getEntity().updateImage();
                        picture.setIcon(new ImageIcon(GameScreen.player.getEntity().getImage().getScaledInstance(width, width, Image.SCALE_FAST)));
                    }
                };
                tempReload.setBounds(barTypes[barIndex].getWidth() + 10, (50 * (barIndex + 1)) + 5, 20, 20);
                tempReload.setBackground(new Color(75, 75, 75));
                innerPanel.add(tempReload);
            }
            barIndex += 1;
        }

        new AvatarPreset("John", new Color(178, 98, 0), new Color(227, 179, 141), new Color(0, 255, 93), new Color(0, 0, 0));
        new AvatarPreset("Alex", new Color(178, 62, 0), new Color(188, 141, 87), new Color(102, 0, 255), new Color(0, 0, 0));
        AvatarPreset[] presets = AvatarPreset.definedPresets;
        JPanel presetWindow = new JPanel();
        presetWindow.setBackground(new Color(75, 75, 75));
        presetWindow.setBounds(5, (50 * (barTitles.length)), (width*3-30), height-(50 * (barTitles.length + 1))+25);
        presetWindow.setLayout(null);
        innerPanel.add(presetWindow);
        JScrollPane presetScroll = new JScrollPane(presetWindow);
        presetScroll.setBounds(presetWindow.getBounds());
        presetWindow.setPreferredSize(new Dimension(70*presets.length, presetWindow.getHeight()));
        presetScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        presetScroll.setBorder(new EmptyBorder(0,0,0,0));
        presetScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        innerPanel.add(presetScroll);
        int presetIndex = 0;
        for (AvatarPreset preset : presets) {
            JPanel presetFrame = new JPanel();
            presetFrame.setBackground(new Color(90, 90, 90));
            int size = presetWindow.getHeight()-25;
            presetFrame.setBounds(5+(presetIndex*size)+5*presetIndex, 5, size, size);
            presetWindow.add(presetFrame);
            Player presetPlayer = new Player(main);
            presetPlayer.changeColour(preset.getHairColour(), "Hair");presetPlayer.changeColour(preset.getSkinColour(), "Skin");presetPlayer.changeColour(preset.getShirtColour(), "Shirt");presetPlayer.changeColour(preset.getShoeColour(), "Shoe");
            presetPlayer.updateSwitchedColours();presetPlayer.getEntity().updateImage();
            JLabel presetPic = new JLabel(new ImageIcon(presetPlayer.getEntity().getImage().getScaledInstance(presetFrame.getWidth()-10, presetFrame.getWidth()-10, Image.SCALE_FAST)));
            presetPic.setBackground(new Color(105, 105, 105));
            presetPic.setBounds(5, 5, presetFrame.getWidth()-10, presetFrame.getWidth()-5);
            presetPic.setLayout(null);
            presetPic.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    GameScreen.player.changeColour(preset.getHairColour(), "Hair");
                    GameScreen.player.changeColour(preset.getSkinColour(), "Skin");
                    GameScreen.player.changeColour(preset.getShirtColour(), "Shirt");
                    GameScreen.player.changeColour(preset.getShoeColour(), "Shoe");
                    GameScreen.player.updateSwitchedColours();GameScreen.player.getEntity().updateImage();
                    picture.setIcon(new ImageIcon(GameScreen.player.getEntity().getImage().getScaledInstance(width, width, Image.SCALE_FAST)));
                }

                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            });
            presetFrame.add(presetPic);
            presetIndex += 1;
        }
    }

    static class AvatarPreset {
        public static AvatarPreset[] definedPresets = new AvatarPreset[0];

        private Color hairColour;
        private Color skinColour;
        private Color shirtColour;
        private Color shoeColour;

        AvatarPreset(String presetName, Color hairColour, Color skinColour, Color shirtColour, Color shoeColour) {
            this.hairColour = hairColour;
            this.skinColour = skinColour;
            this.shirtColour = shirtColour;
            this.shoeColour = shoeColour;
            ArrayList<AvatarPreset> ndfp = new ArrayList<>();
            if (definedPresets.length != 0) {ndfp.addAll(Arrays.asList(definedPresets));}
            ndfp.add(this);definedPresets = ndfp.toArray(new AvatarPreset[definedPresets.length+1]);
        }

        public Color getHairColour() {return hairColour;}
        public Color getShirtColour() {return shirtColour;}

        public Color getShoeColour() {return shoeColour;}
        public Color getSkinColour() {return skinColour;}
        public Color[] getColourData() {return new Color[]{hairColour, skinColour, shirtColour, shoeColour};}
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

package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.ClientWindow;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.menu.MenuButton;
import io.github.explorersodysseygame.game.common.util.Image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class InGameMenu extends JPanel implements ActionListener {

    protected static AvatarMenu avatarMenu;
    protected static JPanel outerPanel;

    static class IGMButton extends MenuButton {
        public IGMButton(String text, Icon icon) {
            super("Menu", icon);
            this.setText(text);
            this.setIcon(icon);
            this.setBackground(new Color(89, 182, 89));
            this.addActionListener(this);
            this.setForeground(new Color(255, 255, 255));
            this.setBorder(BorderFactory.createEmptyBorder());
            this.setFocusable(false);

            final MenuButton.MenuButtonSelectedUI ui = new MenuButton.MenuButtonSelectedUI();
            ui.setSelectColor(this.getBackground().darker());
            this.setUI(ui);
        }
        public IGMButton(String text) {
            super("Menu");
            this.setText(text);
            this.setBackground(new Color(89, 182, 89));
            this.addActionListener(this);
            this.setForeground(new Color(255, 255, 255));
            this.setBorder(BorderFactory.createEmptyBorder());
            this.setFocusable(false);

            final MenuButton.MenuButtonSelectedUI ui = new MenuButton.MenuButtonSelectedUI();
            ui.setSelectColor(this.getBackground().darker());
            this.setUI(ui);
        }
    }

    public InGameMenu(Dimension screenDimensions) {
        avatarMenu = new AvatarMenu(screenDimensions);
        int width = (int) (screenDimensions.getWidth() / 2) + 32;
        int height = (int) (screenDimensions.getHeight()) - 159;

        setName("In Game Menu");

        setPreferredSize(screenDimensions);
        setBounds(0, 0, screenDimensions.width, screenDimensions.height);
        setLayout(null);
        setVisible(false);
        setOpaque(true);
        setBackground(new Color(25, 25, 25));

        outerPanel = new JPanel();
        outerPanel.setName("Outer Panel");
        outerPanel.setPreferredSize(new Dimension(width, height));
        outerPanel.setBounds(screenDimensions.width/5+1, 60, width, height);
        outerPanel.setLayout(null);
        outerPanel.setBackground(new Color(45, 45, 45));
        add(outerPanel);

        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(new Color(60, 60, 60));
        innerPanel.setBounds(10, 10, width -20, height -20);
        innerPanel.setLayout(null);
        outerPanel.add(innerPanel);

        BufferedImage IMG_menu = Objects.requireNonNull(ImageReader.read("menu/menu.png")).getImage();
        BufferedImage IMG_pause = Objects.requireNonNull(ImageReader.read("menu/pause.png")).getImage();
        BufferedImage IMG_avatar = Objects.requireNonNull(ImageReader.read("menu/avatar.png")).getImage();

        IGMButton pauseIndicator = new IGMButton("", new ImageIcon(IMG_pause.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        pauseIndicator.setName("Pause Indicator");
        pauseIndicator.setBackground(new Color(25, 25, 25));
        pauseIndicator.setBounds((screenDimensions.width/2)-20,16, 32, 32);
        final MenuButton.MenuButtonSelectedUI pauseIndicatorUI = new MenuButton.MenuButtonSelectedUI();
        pauseIndicatorUI.setSelectColor(pauseIndicator.getBackground());
        pauseIndicator.setUI(pauseIndicatorUI);
        add(pauseIndicator);

        IGMButton menuButton = new IGMButton("Menu", new ImageIcon(IMG_menu)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed in-game %s button", this.getText()));
                ClientWindow.exitGame(ClientWindow.getWindowAsJFrame(), GameScreen.getScreen());
            }
        };
        menuButton.setBounds(5, 5, width-30, 20);
        menuButton.setBackground(new Color(75, 75, 75));
        innerPanel.add(menuButton);

        IGMButton avatarButton = new IGMButton("Avatar", new ImageIcon(IMG_avatar)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed in-game %s button", this.getText()));
                avatarMenu.setVisible(true);outerPanel.setVisible(false);
            }
        };
        avatarButton.setBounds(5, 30, width-30, 20);
        avatarButton.setBackground(new Color(75, 75, 75));
        innerPanel.add(avatarButton);

        add(avatarMenu);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }
    public boolean isOpen() {
        return isVisible();
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
}

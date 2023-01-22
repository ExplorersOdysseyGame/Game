package io.github.explorersodysseygame.game.client.game;

import io.github.explorersodysseygame.game.Main;
import io.github.explorersodysseygame.game.client.ClientWindow;
import io.github.explorersodysseygame.game.client.renderer.GameScreen;
import io.github.explorersodysseygame.game.common.menu.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameMenu extends JPanel implements ActionListener {

    private static class IGMButton extends MenuButton {
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
        int width = (int) (screenDimensions.getWidth() / 2) + 32;
        int height = (int) (screenDimensions.getHeight()) - 159;
        setPreferredSize(new Dimension(width, height));
        setBounds(screenDimensions.width/5+1, 60, width, height);
        setLayout(null);
        setVisible(false);
        setBackground(new Color(40, 64, 40));

        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(new Color(60, 84, 60));
        innerPanel.setBounds(10, 10, width -20, height -20);
        innerPanel.setLayout(null);
        add(innerPanel);

        IGMButton menuButton = new IGMButton("Menu") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.log(String.format("Pressed %s in-game menu button", this.getText()));
                ClientWindow.exitGame(ClientWindow.getWindowAsJFrame(), GameScreen.getScreen());
            }
        };
        menuButton.setBounds(5, 5, width -30, 20);
        innerPanel.add(menuButton);
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

package io.github.explorersodysseygame.game.common.menu;

import io.github.explorersodysseygame.game.Main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
public class MenuButton extends JButton implements ActionListener {

    public static class MenuButtonSelectedUI extends BasicButtonUI {
        protected Color selectColor;

        public void setSelectColor(final Color selectColor) {
            this.selectColor = Objects.requireNonNull(selectColor);
        }

        public Color getSelectColor() {
            return selectColor;
        }

        @Override
        protected void paintButtonPressed(final Graphics g,
                                          final AbstractButton b){
            if (b.isContentAreaFilled()) {
                Dimension size = b.getSize();
                g.setColor(getSelectColor());
                g.fillRect(0, 0, size.width, size.height);
            }
        }
    }
    public MenuButton(Main main, String text, Icon icon) {
        this.setText(text);
        try {
            this.setIcon(icon);
        } catch (Exception exc) {
            Main.log(String.format("Failure loading a menu button icon: %s", exc.getMessage()));
            this.setIcon(new ImageIcon(main.imageReader.getMemory().findImage("notfound.png").getImage()));
        }
        this.setBackground(new Color(222, 129, 129));
        this.addActionListener(this);
        this.setForeground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusable(false);
        final MenuButtonSelectedUI ui = new MenuButtonSelectedUI();
        ui.setSelectColor(this.getBackground().darker());
        this.setUI(ui);
    }
    public MenuButton(Main main, String text) {
        this.setText(text);
        this.setIcon(new ImageIcon(main.imageReader.getMemory().findImage("notfound.png").getImage()));
        this.setBackground(new Color(222, 129, 129));
        this.addActionListener(this);
        this.setForeground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusable(false);
        final MenuButtonSelectedUI ui = new MenuButtonSelectedUI();
        ui.setSelectColor(this.getBackground().darker());
        this.setUI(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.log(String.format("Pressed %s menu button", this.getText()));
    }
}

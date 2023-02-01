package io.github.explorersodysseygame.game.common.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class PickerBar {
    public static class BasicPickerBar extends JPanel implements MouseListener {
        float markerX = 0.5f; // Float out of 1
        JPanel marker = new JPanel();
        boolean clickingElement = false;
        Color selectedColor;

        public BasicPickerBar() {
            marker.setBackground(Color.BLACK);
            addMouseListener(this);
        }
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(getWidth(), 0);
            float[] dist = {.142f, .284f, .426f, .568f, .71f, .852f, 1f};
            Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};
            LinearGradientPaint gp =
                    new LinearGradientPaint(start, end, dist, colors);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            marker.setBounds((int) (markerX*getWidth()), 0, 1, getHeight());
            add(marker);
        };

        private static Color getColorAt(BasicPickerBar frm, Point p) {
            Rectangle rect = frm.getBounds();
            BufferedImage img = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
            frm.paintAll(img.createGraphics());
            return new Color(img.getRGB(p.x, p.y), true);
        }

        public Color getSelectedColor() {
            return selectedColor;
        }
        public void setSelectedColor(Color c) {
            selectedColor = c;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, this);

            Color c = getColorAt(this, p);
            setSelectedColor(c);

            markerX = (float)(p.getX()/getWidth());
        }
        @Override public void mousePressed(MouseEvent e) {
            clickingElement = true;
        }
        @Override public void mouseReleased(MouseEvent e) {
            clickingElement = false;
        }
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    public static class RainbowPickerBar extends BasicPickerBar {
        public RainbowPickerBar() {
            super();
        }
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(getWidth(), 0);
            float[] dist = {0f, .142f, .284f, .426f, .568f, .71f, .852f, 1f};
            Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED};
            LinearGradientPaint gp =
                    new LinearGradientPaint(start, end, dist, colors);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        };
    }

    public static class SkinshadePickerBar extends BasicPickerBar {
        public SkinshadePickerBar() {
            super();
        }
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(getWidth(), 0);
            float[] dist = {0f, 0.17f, 0.34f, 0.51f, 0.68f, 1f};
            Color[] colors = {Color.decode("#F9ECE4"), Color.decode("#F0D4C6"), Color.decode("#E3B38D"), Color.decode("#BC8D57"), Color.decode("#A96C4F"), Color.decode("#704733")};
            LinearGradientPaint gp =
                    new LinearGradientPaint(start, end, dist, colors);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        };
    }
}

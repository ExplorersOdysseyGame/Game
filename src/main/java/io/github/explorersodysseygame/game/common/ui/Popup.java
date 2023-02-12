package io.github.explorersodysseygame.game.common.ui;

import io.github.explorersodysseygame.game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Popup {

    public static class TwoEntryPopup extends JFrame {

        public boolean hasResponded = false;
        private boolean hasDrawnPopup = false;

        private final String entryOneName;
        private final String entryTwoName;
        private Runnable onResponse;

        private String entryOneResponse;
        private String entryTwoResponse;


        public String[] responses;

        public void setOnResponse(Runnable onResponse1) {
            onResponse = onResponse1;
        }

        public TwoEntryPopup(String popupName, String entryOneName, String entryTwoName) {
            super(popupName);
            this.entryOneName = entryOneName;
            this.entryTwoName = entryTwoName;
            setAlwaysOnTop(true);
            try {
                setIconImage(ImageIO.read(new File("resources/misc/icon.png")));
            } catch (Exception e) {
                setIconImage(null);
            }
            int width = 425;
            int height = 151;
            setMaximizedBounds(new Rectangle(width, height));
            setMinimumSize(new Dimension(width, height));
            setLayout(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            tick();

            setSize(width, height);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);

            Runtime.getRuntime().addShutdownHook(new Thread(this::close, "Shutdown-thread"));
        }

        public void close() {
            responses = new String[]{entryOneResponse, entryTwoResponse};
            hasResponded = true;
            setVisible(false);
            dispose();
        }

        public void draw() {
            JLabel entryOneLabel = new JLabel(entryOneName);
            entryOneLabel.setPreferredSize(new Dimension(200, 33));
            entryOneLabel.setBounds(5, 5, 200, 33);
            add(entryOneLabel);
            JTextField entryOneField = new JTextField();
            entryOneField.setPreferredSize(new Dimension(200, 33));
            entryOneField.setBounds(205, 5, 200, 33);
            add(entryOneField);
            JLabel entryTwoLabel = new JLabel(entryTwoName);
            entryTwoLabel.setPreferredSize(new Dimension(200, 33));
            entryTwoLabel.setBounds(5, 39, 200, 33);
            add(entryTwoLabel);
            JTextField entryTwoField = new JTextField();
            entryTwoField.setPreferredSize(new Dimension(200, 33));
            entryTwoField.setBounds(205, 39, 200, 33);
            add(entryTwoField);

            JButton confirmButton = new JButton("Confirm");
            confirmButton.setPreferredSize(new Dimension(100, 33));
            confirmButton.setBounds(10, 73, 390, 33);
            confirmButton.addActionListener(e -> {
                entryOneResponse = entryOneField.getText();
                entryTwoResponse = entryTwoField.getText();
                close();
                onResponse.run();
            });
            add(confirmButton);
            hasDrawnPopup = true;
        }

        public void tick() {
            if (!hasDrawnPopup) {
                draw();
            }
        }

    }
}

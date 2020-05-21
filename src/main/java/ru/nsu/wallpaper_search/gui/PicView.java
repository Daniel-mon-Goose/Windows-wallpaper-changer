package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PicView extends JFrame {
    private JPanel contentPane;
    private JButton setButton, cancelButton;

    public PicView(BufferedImage pic) {
        contentPane = new JPanel();
        JPanel picPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pic, 0, 0, this);
            }
        };
        picPane.setPreferredSize(new Dimension(pic.getWidth(), pic.getHeight()));

        setButton = new JButton();
        cancelButton = new JButton();
        setButtonDesign(setButton, 100, 30, "OK");
        setButtonDesign(cancelButton, 100, 30, "Cancel");

        contentPane.add(picPane);
        contentPane.add(setButton);
        contentPane.add(cancelButton);
        this.add(contentPane);

        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Wallpaper search");

        this.setPreferredSize(new Dimension((int)(pic.getWidth() * 1.1), pic.getHeight() + 50));
        this.setResizable(false);
    }

    private void setButtonDesign(JButton button, int width, int height, String text) {
        button.setPreferredSize(new Dimension(width, height));
        button.setText(text);
    }
}

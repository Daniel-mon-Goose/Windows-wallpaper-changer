package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PicView extends JFrame {
    private JPanel contentPane;
    private JButton setButton;
    private JButton cancelButton;
    private int picWidth;
    private int picHeight;

    public PicView(BufferedImage pic) {
        contentPane = new JPanel();

        picWidth = pic.getWidth();
        picHeight = pic.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (picWidth >= screenSize.getWidth() - 100 || picHeight >= screenSize.getHeight() - 100) {
            picWidth =  (int)(picWidth * 0.4);
            picHeight = (int)(picHeight * 0.4);
        }

        JPanel picPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pic, 0, 0, picWidth, picHeight, this);
            }
        };
        picPane.setPreferredSize(new Dimension(picWidth, picHeight));

        setButton = new JButton();
        cancelButton = new JButton();
        setButtonDesign(setButton, 200, 30, "Set as wallpaper");
        setButtonDesign(cancelButton, 100, 30, "Cancel");

        contentPane.add(picPane);
        contentPane.add(setButton);
        contentPane.add(cancelButton);
        this.add(contentPane);

        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Wallpaper search");

        this.setPreferredSize(new Dimension(picWidth + 10, picHeight + 50));
        this.setResizable(false);
    }

    private void setButtonDesign(JButton button, int width, int height, String text) {
        button.setPreferredSize(new Dimension(width, height));
        button.setText(text);
    }

    void addOkButtonActionListener(ActionListener l) { setButton.addActionListener(l); }

    void addCancelButtonActionListener(ActionListener l) { cancelButton.addActionListener(l); }

    void closeWindow() { dispose(); }
}

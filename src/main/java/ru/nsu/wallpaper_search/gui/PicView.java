package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class PicView extends JFrame {
    private JPanel contentPane;
    private JButton setButton;
    private JButton cancelButton;
    private int picWidth;
    private int picHeight;
    private static final int BUTTONWIDTH = 200;
    private int BUTTONHEIGHT = 30;

    public PicView(BufferedImage pic, Runnable notifyOnClose) {
        setUndecorated(true);
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
        setButtonDesign(setButton, BUTTONWIDTH, BUTTONHEIGHT, "Set as wallpaper");
        setButtonDesign(cancelButton, BUTTONWIDTH, BUTTONHEIGHT, "Cancel");

        contentPane.add(picPane);
        contentPane.add(setButton);
        contentPane.add(cancelButton);
        this.add(contentPane);

        setContentPane(contentPane);
        setTitle("Wallpaper search");

        this.setPreferredSize(new Dimension(picWidth + 10, picHeight + BUTTONHEIGHT + 50));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notifyOnClose.run();
            }
        });
    }

    private void setButtonDesign(JButton button, int width, int height, String text) {
        button.setPreferredSize(new Dimension(width, height));
        button.setText(text);
    }

    void addOkButtonActionListener(ActionListener l) { setButton.addActionListener(l); }

    void addCancelButtonActionListener(ActionListener l) { cancelButton.addActionListener(l); }

    void closeWindow() { dispose(); }
}

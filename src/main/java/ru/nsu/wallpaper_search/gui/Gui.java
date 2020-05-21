package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private JPanel contentPane, prefPane, queryPane, resultsPane;
    private JComboBox<Integer> widthBox, heightBox;
    private JScrollPane pictures;
    private JLabel widthLabel, heightLabel;
    private JTextField queryField;
    private JButton searchButton;
    private int viewWidth, viewHeight;

    public Gui() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wallpaper search");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        viewWidth = (int)(screenSize.getWidth() * 0.45);
        viewHeight = (int)(screenSize.getHeight() * 0.6);
        this.setPreferredSize(new Dimension(viewWidth, viewHeight));
        this.setResizable(false);

        resultsPane.setPreferredSize(new Dimension(viewWidth, (int)(viewHeight * 0.5)));

        widthBox.addItem((int)screenSize.getWidth());
        heightBox.addItem((int)screenSize.getHeight());
        int[] widthItems = {1920, 1366, 1536};
        int[] heightItems = {1080, 768, 864};
        for (int widthItem : widthItems) {
            if (!(widthItem == (int)screenSize.getWidth())) widthBox.addItem(widthItem);
        }
        for (int heightItem : heightItems) {
            if (!(heightItem == (int)screenSize.getHeight())) {
                heightBox.addItem(heightItem);
            }
        }
    }
}

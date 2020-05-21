package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private JPanel contentPane, prefPane, queryPane, resultsPane;
    private JComboBox widthBox, heightBox;
    private JScrollPane pictures;
    private JLabel width, height;
    private JTextField queryField;
    private JButton searchButton;
    private int viewWidth, viewHeight;

    public Gui() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Wallpaper search");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        viewWidth = (int)(screenSize.getWidth() * 0.45);
        viewHeight = (int)(screenSize.getHeight() * 0.6);
        this.setPreferredSize(new Dimension(viewWidth, viewHeight));
        this.setResizable(false);

    //    queryField.setMaximumSize(new Dimension((int)(viewWidth * 0.2), 10));
        resultsPane.setPreferredSize(new Dimension((int)viewWidth, (int)(viewHeight * 0.5)));
    }
}

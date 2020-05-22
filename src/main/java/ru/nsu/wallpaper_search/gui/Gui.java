package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Gui extends JFrame {

    private JPanel contentPane, prefPane, queryPane, galleryPane;
    private JComboBox<Integer> widthBox, heightBox;
    private JLabel widthLabel, heightLabel;
    private JTextField queryField;
    private JButton searchButton;
    private JScrollPane resultsPane;

    private int startWidth, startHeight, showResultHeight;
    private int cellSize;
    private int spaceSize;
    private BufferedImage gallery;

    public JTextField getQueryField() {
        return queryField;
    }

    public Gui() {
        add(contentPane);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wallpaper search");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        startWidth = (int) (screenSize.getWidth() * 0.3);
        startHeight = (int) (screenSize.getHeight() * 0.2);
        setPreferredSize(new Dimension(startWidth, startHeight));
        prefPane.setPreferredSize(new Dimension(startWidth, (int)(startHeight * 0.7)));
        setResizable(false);

        widthBox.addItem((int) screenSize.getWidth());
        heightBox.addItem((int) screenSize.getHeight());
        int[] widthItems = {1920, 1366, 1536};
        int[] heightItems = {1080, 768, 864};
        for (int widthItem : widthItems) {
            if (!(widthItem == (int) screenSize.getWidth())) widthBox.addItem(widthItem);
        }
        for (int heightItem : heightItems) {
            if (!(heightItem == (int) screenSize.getHeight())) {
                heightBox.addItem(heightItem);
            }
        }
    }

    public void addSearshListener(ActionListener l) {
        searchButton.addActionListener(l);
    }

    public void createPlaceholder(String placeholder, FocusListener f) {
        queryField.setText(placeholder);
        queryField.setForeground(Color.GRAY);
        queryField.addFocusListener(f);
    }

    public void setText(JTextField field, String text) {
        field.setText(text);
    }

    public void setForeground(JTextField field, Color color) {
        field.setForeground(color);
    }

    public String getText(JTextField field) {
        return field.getText();
    }

    public void resizeWindow() {
        setResizable(true);
        showResultHeight = startHeight * 3 - 100;
        resize(new Dimension(startWidth, showResultHeight));
        prefPane.setPreferredSize(new Dimension(startWidth, (int)(showResultHeight * 0.4)));
        setResizable(false);
    }


    public void addResultsPane() {
        resizeWindow();
        galleryPane = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gallery, 0, 0, this);
            }

        };

        resultsPane = new JScrollPane(galleryPane);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        layout.putConstraint(SpringLayout.SOUTH, resultsPane, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, prefPane, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, queryPane, 0, SpringLayout.SOUTH, prefPane);
        contentPane.add(resultsPane, layout);

        resultsPane.setVisible(true);
        galleryPane.setVisible(true);
        resultsPane.setPreferredSize(new Dimension(startWidth, (int) (showResultHeight * 0.5)));
        galleryPane.setPreferredSize(new Dimension(startWidth-50, (int) (showResultHeight * 0.8)));

        cellSize = (int) (startWidth * 0.2);
        spaceSize = (int) (startWidth * 0.06);
    }

    public void drawImages(ArrayList<Image> images) {
        addResultsPane();
    }
}

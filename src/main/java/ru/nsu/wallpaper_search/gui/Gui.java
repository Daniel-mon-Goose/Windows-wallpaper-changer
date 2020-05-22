package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gui extends JFrame {

    private JPanel contentPane, prefPane, queryPane, galleryPane;
    private JComboBox<Integer> widthBox, heightBox;
    private JLabel widthLabel, heightLabel;
    private JTextField queryField;
    private JButton searchButton;
    private JScrollPane resultsPane;

    private int width, height, resultPaneHeight, galleryWidth, galleryHeight;
    private int cellSize;
    private int imagesNum;
    private static final int SPACE_SIZE = 10;
    private static final int IMAGES_IN_ROW = 5;
    private BufferedImage gallery;
    private Map<BufferedImage, Coords> imageCoords = new HashMap<>();

    public Gui() {
        add(contentPane);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wallpaper search");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) (screenSize.getWidth() * 0.3);
        height = (int) (screenSize.getHeight() * 0.2);
        setPreferredSize(new Dimension(width, height));
        prefPane.setPreferredSize(new Dimension(width, (int)(height * 0.7)));
        setResizable(false);

        widthBox.addItem((int) screenSize.getWidth());
        heightBox.addItem((int) screenSize.getHeight());
        int[] widthItems = {1920, 1366, 1536};
        int[] heightItems = {1080, 768, 864};
        for (int widthItem : widthItems) {
            if (!(widthItem == (int) screenSize.getWidth())) {
                widthBox.addItem(widthItem);
            }
        }
        for (int heightItem : heightItems) {
            if (!(heightItem == (int) screenSize.getHeight())) {
                heightBox.addItem(heightItem);
            }
        }
    }

    public Map<BufferedImage, Coords> getImageCoords() {
        return imageCoords;
    }

    public JTextField getQueryField() {
        return queryField;
    }

    public String getText(JTextField field) {
        return field.getText();
    }

    public void setText(JTextField field, String text) {
        field.setText(text);
    }

    public void setForeground(JTextField field, Color color) {
        field.setForeground(color);
    }

    public void resizeWindow() {
        setResizable(true);
        resize(new Dimension(width, height + resultPaneHeight));
        resultsPane.setPreferredSize(new Dimension(width, resultPaneHeight));
        galleryPane.setPreferredSize(new Dimension(galleryWidth, galleryHeight));
        setResizable(false);
    }


    public void addResultsPane() {
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

        galleryWidth = width - 2 * SPACE_SIZE;
        cellSize = galleryWidth / IMAGES_IN_ROW - 2 * SPACE_SIZE;
        int rowNum = imagesNum / IMAGES_IN_ROW;
        if (imagesNum % IMAGES_IN_ROW != 0) rowNum++;
        galleryHeight = (cellSize + 2 * SPACE_SIZE) * rowNum;
        resultPaneHeight = cellSize + 2 * SPACE_SIZE;
        resizeWindow();
    }

    public void drawImages(ArrayList<BufferedImage> images) {
        imagesNum = images.size();
        addResultsPane();
        gallery = new BufferedImage(galleryWidth, galleryHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = gallery.createGraphics();
        graphics.setPaint(Color.PINK);
        graphics.fillRect(0, 0, gallery.getWidth(), gallery.getHeight());
        int x = 0;
        int y = 0;
        for (int i = 0; i < images.size(); i++) {
            if (i % IMAGES_IN_ROW == 0 && i != 0) {
                y += (cellSize + SPACE_SIZE * 2);
                x = 0;
            }
            BufferedImage image = images.get(i);
            graphics.drawImage(image, SPACE_SIZE + x, SPACE_SIZE + y, cellSize, cellSize, null);
            imageCoords.put(image, new Coords(SPACE_SIZE + x, SPACE_SIZE + y));
            x += (cellSize + SPACE_SIZE * 2);
        }
        repaint();
    }

    public void addSearshListener(ActionListener l) {
        searchButton.addActionListener(l);
    }

    public void createPlaceholder(String placeholder, FocusListener f) {
        queryField.setText(placeholder);
        queryField.setForeground(Color.GRAY);
        queryField.addFocusListener(f);
    }

    public void addViewPictureListener(MouseListener l) {
        galleryPane.addMouseListener(l);
    }

}

package ru.nsu.wallpaper_search.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gui extends JFrame {

    private JPanel contentPane;
    private JPanel prefPane;
    private JPanel queryPane;
    private JPanel galleryPane;
    private JComboBox<Integer> widthBox;
    private JComboBox<Integer> heightBox;
    private JTextField queryField;
    private JButton searchButton;
    private JPanel popularThemesPane;
    private BufferedImage popularThemes;
    private JScrollPane resultsPane;
    private BufferedImage gallery;
    private SpringLayout layout = new SpringLayout();

    private int popularThemesWidth;
    private int popularThemesHeight;

    private int paneWidth;
    private int paneHeight;
    private int resultPaneHeight;
    private int galleryWidth;
    private int windowWidth;
    private int galleryHeight;
    private int xCellSize;
    private int yCellSize;
    private int imagesNum;
    private static final int SPACE_SIZE = 10;
    private static final int IMAGES_IN_ROW = 5;
    private Map<BufferedImage, Coords> imageCoords = new HashMap<>();

    public Gui() {
        add(contentPane);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wallpaper search");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowWidth = (int) (screenSize.getWidth() * 0.5);
        paneWidth = (int) (screenSize.getWidth() * 0.3);

        popularThemesWidth = (int )(paneWidth / 1.5);
        popularThemesHeight = (int)(popularThemesWidth / 1.5);
        paneHeight = (int) (screenSize.getHeight() * 0.1) + popularThemesHeight;

        setPreferredSize(new Dimension(windowWidth + 100, paneHeight));
        prefPane.setPreferredSize(new Dimension(paneWidth, (int)((paneHeight - popularThemesHeight) * 0.5)));
        queryPane.setPreferredSize(new Dimension(paneWidth, (int)((paneHeight - popularThemesHeight) * 0.5)));
        setResizable(false);

        widthBox.addItem((int) screenSize.getWidth());
        heightBox.addItem((int) screenSize.getHeight());
        int[] widthItems = {1920, 1366, 1536};
        int[] heightItems = {1080, 768, 864};
        for (int widthItem : widthItems) {
            if (widthItem != (int) screenSize.getWidth()) {
                widthBox.addItem(widthItem);
            }
        }
        for (int heightItem : heightItems) {
            if (heightItem != (int) screenSize.getHeight()) {
                heightBox.addItem(heightItem);
            }
        }
    }

    public JTextField getQueryField() {
        return queryField;
    }

    public int getWidth() {
        return (int) widthBox.getSelectedItem();
    }

    public int getHeight() {
        return (int) heightBox.getSelectedItem();
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
        resize(new Dimension(windowWidth + 100, paneHeight + resultPaneHeight + 30));
        resultsPane.setPreferredSize(new Dimension(windowWidth, resultPaneHeight));
        galleryPane.setPreferredSize(new Dimension(galleryWidth, galleryHeight));
        setResizable(false);
    }

    private void addPopularThemes() {}

    public void drawPopularThemes() {
        addPopularThemes();
    }

    private void addResultsPane() {
        galleryPane = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gallery, 0, 0, this);
            }
        };

        resultsPane = new JScrollPane(galleryPane);
        setLayout(layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resultsPane, 0, SpringLayout.HORIZONTAL_CENTER, queryPane);
        layout.putConstraint(SpringLayout.NORTH, resultsPane, 0, SpringLayout.SOUTH, queryPane);
        contentPane.add(resultsPane, layout);

        resultsPane.setVisible(true);
        galleryPane.setVisible(true);

        galleryWidth = windowWidth - 20;
        xCellSize = galleryWidth / IMAGES_IN_ROW - 2 * SPACE_SIZE;
        yCellSize = xCellSize * (int)heightBox.getSelectedItem() / (int)widthBox.getSelectedItem();
        int rowNum = imagesNum / IMAGES_IN_ROW;
        if (imagesNum % IMAGES_IN_ROW != 0) rowNum++;
        galleryHeight = (yCellSize + 2 * SPACE_SIZE) * rowNum;
        resultPaneHeight = 2 * yCellSize + 4 * SPACE_SIZE;
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
                y += (yCellSize + SPACE_SIZE * 2);
                x = 0;
            }
            BufferedImage image = images.get(i);
            graphics.drawImage(image, SPACE_SIZE + x, SPACE_SIZE + y, xCellSize, yCellSize, null);
            imageCoords.put(image, new Coords(SPACE_SIZE + x, SPACE_SIZE + y));
            x += (xCellSize + SPACE_SIZE * 2);
        }
        repaint();
    }

    public void addSearchListener(ActionListener l) {
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

    BufferedImage getImage(Coords coords) {
        int x, y;
        for(Map.Entry<BufferedImage, Coords> entry : imageCoords.entrySet()) {
            x = entry.getValue().getX();
            y = entry.getValue().getY();
            if (x <= coords.getX() && coords.getX() <= x + xCellSize &&
                    y <= coords.getY() && coords.getY() <= y + xCellSize) {
                return entry.getKey();
            }
        }
        return null;
    }

}

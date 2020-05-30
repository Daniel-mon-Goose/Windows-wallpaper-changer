package ru.nsu.wallpaper_search.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private int buttonSize;
    private static final int SPACE_SIZE = 10;
    private static final int IMAGES_IN_ROW = 5;
    private Map<BufferedImage, Coords> imageCoords;
    private Map<String, Coords> buttonsCoords = new HashMap<>();

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
        paneHeight = (int) (screenSize.getHeight() * 0.2) + popularThemesHeight + 30;

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

    private void addPopularThemes() {
        popularThemesPane = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(popularThemes, 0, 0, this);
            }
        };
        setLayout(layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, prefPane, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, popularThemesPane, 0, SpringLayout.HORIZONTAL_CENTER, prefPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, queryPane, 0, SpringLayout.HORIZONTAL_CENTER, popularThemesPane);
        layout.putConstraint(SpringLayout.NORTH, prefPane, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, popularThemesPane, 0, SpringLayout.SOUTH, prefPane);
        layout.putConstraint(SpringLayout.NORTH, queryPane, 0, SpringLayout.SOUTH, popularThemesPane);
        contentPane.add(popularThemesPane, layout);

        contentPane.setBackground(new Color (81, 76, 108));
        popularThemesPane.setBackground(new Color (81, 76, 108));
//        try {
//            BufferedImage image = ImageIO.read(new File("./src/main/resources/background.jpg"));
//            JLabel label = new JLabel(new ImageIcon(image));
//            contentPane.add(label);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        popularThemesPane.setVisible(true);
        popularThemesPane.setPreferredSize(new Dimension(popularThemesWidth, popularThemesHeight));
    }

    public void drawPopularThemes() {
        addPopularThemes();
        Map<BufferedImage, String> buttons = new HashMap<>();
        try {
            buttons.put(ImageIO.read(new File("./src/main/resources/cat.jpg")), "cat");
            buttons.put(ImageIO.read(new File("./src/main/resources/dog.jpg")), "dog");
            buttons.put(ImageIO.read(new File("./src/main/resources/nature.jpg")), "nature");
            buttons.put(ImageIO.read(new File("./src/main/resources/anime.jpg")), "anime");
            buttons.put(ImageIO.read(new File("./src/main/resources/food.jpg")), "food");
            buttons.put(ImageIO.read(new File("./src/main/resources/cars.jpg")), "cars");
        } catch (IOException e) {
            e.printStackTrace();
        }
        popularThemes = new BufferedImage(popularThemesWidth, popularThemesHeight, BufferedImage.TYPE_INT_RGB);

        buttonSize = (popularThemesWidth * 10 / 36);
        int space = (buttonSize / 10);

        Graphics2D graphics = popularThemes.createGraphics();
        graphics.setPaint(new Color (81, 76, 108));
        graphics.fillRect(0, 0, popularThemes.getWidth(), popularThemes.getHeight());
        int x = 0;
        int y = 0;
        int i = 0;
        for (Map.Entry<BufferedImage, String> entry : buttons.entrySet()) {
            if (i % 3 == 0 && i != 0) {
                y += (buttonSize + space * 2);
                x = 0;
            }
            BufferedImage image = entry.getKey();
            graphics.drawImage(image, space + x, space + y, buttonSize, buttonSize, null);
            buttonsCoords.put(entry.getValue(), new Coords(space + x, space + y));
            x += (buttonSize + space * 2);
            i++;
        }
        repaint();
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
        resultsPane.getVerticalScrollBar().setUnitIncrement(30);
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
        imageCoords = new HashMap<>();
        if (imagesNum == 0) {
            JOptionPane.showMessageDialog(this, "No results.");
            return;
        }

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

    public void addThemesListener(MouseListener l) {
        popularThemesPane.addMouseListener(l);
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
        int x;
        int y;
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

    String getImageLabel(Coords coords) {
        for(Map.Entry<String, Coords> entry : buttonsCoords.entrySet()) {
            if (entry.getValue().getX() <= coords.getX() && coords.getX() <= entry.getValue().getX() + buttonSize &&
                    entry.getValue().getY() <= coords.getY() && coords.getY() <= entry.getValue().getY() + buttonSize) {
                return entry.getKey();
            }
        }
        return null;
    }

    void setQueryField(String text) {
        queryField.setText(text);
    }

}
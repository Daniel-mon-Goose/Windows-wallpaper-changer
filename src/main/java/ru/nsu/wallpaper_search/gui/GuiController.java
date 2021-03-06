package ru.nsu.wallpaper_search.gui;

import ru.nsu.wallpaper_search.tools.DataHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GuiController {
    private Gui view;
    private DataHandler searcher;
    private Runnable changeWP;
    private static final String PLACEHOLDER = "Enter your request...";
    private PicController picController;

    public GuiController(Runnable searcher, Runnable changeWP) {
        this.searcher = (DataHandler) searcher;
        this.changeWP = changeWP;
        view = new Gui();
        view.drawPopularThemes();
        view.pack();
        view.setVisible(true);
        view.createPlaceholder(PLACEHOLDER, new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (view.getQueryField().getForeground() == Color.GRAY) {
                    view.setText(view.getQueryField(), "");
                    view.setForeground(view.getQueryField(), Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (view.getText(view.getQueryField()).isEmpty()) {
                    view.setForeground(view.getQueryField(), Color.GRAY);
                    view.setText(view.getQueryField(), PLACEHOLDER);
                }
            }
        });

        view.addSearchListener(this::sendRequest);
        view.addThemesListener(new ThemesListener());
    }

    private void sendRequest(ActionEvent e) {
        searcher.setWidth(getWidth());
        searcher.setHeight(getHeight());
        searcher.setTheme(getRequest());
        searcher.run();
        drawImages((ArrayList<BufferedImage>) searcher.getThumbnails());
    }

    public String getRequest() {
        return view.getText(view.getQueryField());
    }

    public int getWidth() {
        return view.getWidth();
    }

    public int getHeight() {
        return view.getHeight();
    }

    public BufferedImage getPickedImage() {
        return picController.getPickedImage();
    }

    public void drawImages(ArrayList<BufferedImage> images) {
        view.drawImages(images);
        view.addViewPictureListener(new PicListener());
    }

    class PicListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                BufferedImage img = view.getImage(new Coords(e.getX(), e.getY()));

                if (img != null) {
                    int picNumber = searcher.getThumbnails().indexOf(img);
                    view.setEnabled(false);
                    picController = new PicController(img, searcher.getLinks().get(picNumber), changeWP, () -> view.setEnabled(true));
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }
    }

    class ThemesListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                String label = view.getImageLabel(new Coords(e.getX(), e.getY()));
                if (label != null) {
                    view.setQueryField(label);
                    searcher.setWidth(getWidth());
                    searcher.setHeight(getHeight());
                    searcher.setTheme(label);
                    searcher.run();
                    drawImages((ArrayList<BufferedImage>) searcher.getThumbnails());
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // mouse listener implementation requires to override all methods (even if they aren't necessary)
        }
    }

}

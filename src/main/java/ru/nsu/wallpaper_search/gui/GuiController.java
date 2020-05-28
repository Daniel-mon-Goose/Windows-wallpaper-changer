package ru.nsu.wallpaper_search.gui;

import ru.nsu.wallpaper_search.tools.DataHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GuiController {
    private Gui view;
    DataHandler searcher;
    private Runnable changeWP;
    private static final String PLACEHOLDER = "Enter your request...";
    private PicController picController;

    public GuiController(Runnable searcher, Runnable changeWP) {
        this.searcher = (DataHandler) searcher;
        this.changeWP = changeWP;
        view = new Gui();
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

        view.addSearshListener(this::sendRequest);
    }

    private void sendRequest(ActionEvent e) {
        searcher.run();
        drawImages(searcher.getThumbnails());
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
                    view.setEnabled(false);
                    picController = new PicController(img, changeWP, () -> view.setEnabled(true));
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}

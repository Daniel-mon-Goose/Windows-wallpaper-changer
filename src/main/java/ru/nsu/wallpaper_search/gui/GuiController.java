package ru.nsu.wallpaper_search.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GuiController {
    private Gui view;
    private Runnable notifier;
    private static final String placeholder = "Enter your request.....";

    public GuiController(Runnable notifier) {
        this.notifier = notifier;
        view = new Gui();
        view.pack();
        view.setVisible(true);
        view.createPlaceholder(placeholder, new FocusListener() {
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
                    view.setText(view.getQueryField(), placeholder);
                }
            }
        });

        view.addSearshListener(this::notifyOnSearch);
    }

    private void notifyOnSearch(ActionEvent e) {
        notifier.run();
    }

    public String getRequest() {
        return view.getText(view.getQueryField());
    }

    public void drawImages(ArrayList<BufferedImage> images) {
        view.drawImages(images);
        view.addViewPictureListener(new PicListener());
    }

    class PicListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}

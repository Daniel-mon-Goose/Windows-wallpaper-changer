package ru.nsu.wallpaper_search.gui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GuiController {
    private Gui view;
    private static final String placeholder = "Enter your request.....";

    public GuiController() {
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
    }
}

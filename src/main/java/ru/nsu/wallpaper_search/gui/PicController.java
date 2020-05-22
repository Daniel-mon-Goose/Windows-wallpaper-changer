package ru.nsu.wallpaper_search.gui;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class PicController {
    private PicView view;

    public PicController(BufferedImage pic) {
        // предполагается, что GuiController при двойном клике на изображение из списка
        // дёргает конструктор PicController'a, передавая ему уже конвертированное в
        // BufferedImage изображение
        view = new PicView(pic);
        view.pack();
        view.setVisible(true);

        view.addOkButtonActionListener(this::ok);
        view.addCancelButtonActionListener(this::cancel);
    }

    // TODO: задействовать установщика обоев
    private void ok(ActionEvent actionEvent) {

    }

    private void cancel(ActionEvent actionEvent) { view.closeWindow(); }

}

package ru.nsu.wallpaper_search.gui;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicController {
    private PicView view;

    public PicController() throws IOException {
        BufferedImage pic = ImageIO.read(new File("/home/alena/Desktop/cat.jpg"));
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

package ru.nsu.wallpaper_search.gui;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class PicController {
    private PicView view;
    private Runnable changeWP;
    Runnable notifyOnClose;
    private BufferedImage pickedImage;

    public PicController(BufferedImage pic, Runnable changeWP, Runnable notifyOnClose) {
        pickedImage = pic;
        this.changeWP = changeWP;
        this.notifyOnClose = notifyOnClose;
        view = new PicView(pic, notifyOnClose);
        view.pack();
        view.setVisible(true);

        view.addOkButtonActionListener(this::ok);
        view.addCancelButtonActionListener(this::cancel);
    }

    public BufferedImage getPickedImage() {
        return pickedImage;
    }

    private void ok(ActionEvent actionEvent) {
        changeWP.run();
        notifyOnClose.run();
        view.closeWindow();
    }

    private void cancel(ActionEvent actionEvent) {
        notifyOnClose.run();
        view.closeWindow();
    }
}

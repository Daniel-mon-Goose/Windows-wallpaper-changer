package ru.nsu.wallpaper_search.gui;

import ru.nsu.wallpaper_search.tools.ChangeHandler;
import ru.nsu.wallpaper_search.tools.PicCell;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class PicController {
    private PicView view;
    private ChangeHandler changeWP;
    Runnable notifyOnClose;
    private BufferedImage pickedImage;
    private PicCell pickedCell;

    public PicController(BufferedImage pic, PicCell cell, Runnable changeWP, Runnable notifyOnClose) {
        pickedImage = pic;
        this.changeWP = (ChangeHandler) changeWP;
        this.notifyOnClose = notifyOnClose;
        view = new PicView(pic, notifyOnClose);
        view.pack();
        view.setVisible(true);

        view.addOkButtonActionListener(this::ok);
        view.addCancelButtonActionListener(this::cancel);
        pickedCell = cell;
    }

    public BufferedImage getPickedImage() {
        return pickedImage;
    }

    private void ok(ActionEvent actionEvent) {
        changeWP.setCell(pickedCell);
        changeWP.run();
        notifyOnClose.run();
        view.closeWindow();
    }

    private void cancel(ActionEvent actionEvent) {
        notifyOnClose.run();
        view.closeWindow();
    }
}

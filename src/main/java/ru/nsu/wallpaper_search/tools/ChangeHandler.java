package ru.nsu.wallpaper_search.tools;

import java.awt.image.BufferedImage;

public class ChangeHandler implements Runnable{

    private BufferedImage pickedImage;

    @Override
    public void run() {
        System.out.println("changer run");
    }


}

package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.changer.Changer;
import ru.nsu.wallpaper_search.exceptions.ImageLoadException;

import java.nio.file.FileSystemException;

public class ChangeHandler implements Runnable{

    private PicCell cell;

    @Override
    public void run() {
        try {
            String path = ImageLoader.load(cell);
            Changer.changeDesktopImage(path);
        } catch (ImageLoadException | FileSystemException e) {
            //ignore
        }
    }

    public void setCell(PicCell cell) {
        this.cell = cell;
    }

    public PicCell getCell() { return cell; }
}

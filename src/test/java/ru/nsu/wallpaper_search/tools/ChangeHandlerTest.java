package ru.nsu.wallpaper_search.tools;

import org.junit.Assert;
import org.junit.Test;

public class ChangeHandlerTest {

    PicCell cell = new PicCell("https://im0-tub-ru.yandex.net/i?id=81ef90734d287839980ce545c9dc3696&n=13");
    private static final String workingLink = "https://www.wallpapermaiden.com/wallpaper/25017/" +
            "download/1600x900/princess-zelda-elf-ears-the-legend-of-zelda-smiling-blonde-anime-games.jpeg";

    @Test
    public void testSetCell() {
        cell.addOriginal(workingLink);
        var handler = new ChangeHandler();
        handler.setCell(cell);
        handler.run();
        Assert.assertSame(cell, handler.getCell());
        Assert.assertFalse(handler.isError());
    }

}
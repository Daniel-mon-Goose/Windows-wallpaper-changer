package ru.nsu.wallpaper_search.tools;

import junit.framework.TestCase;
import org.junit.Assert;

public class ChangeHandlerTest extends TestCase {

    PicCell cell = new PicCell("https://im0-tub-ru.yandex.net/i?id=81ef90734d287839980ce545c9dc3696&n=13");

    public void testSetCell() {
        var handler = new ChangeHandler();
        handler.setCell(cell);
        Assert.assertSame(cell, handler.getCell());
    }
    
}
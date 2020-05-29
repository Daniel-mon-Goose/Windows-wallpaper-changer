package ru.nsu.wallpaper_search.tools;

import org.junit.Assert;
import org.junit.Test;

public class DataHandlerTest{

    private static final int width = 1920, height = 1080;
    private static final String request = "cat";

    private static DataHandler handler = new DataHandler();

    @Test
    public void testDataHand() {
        handler.setWidth(width);
        handler.setHeight(height);
        handler.setTheme(request);
        handler.run();
        Assert.assertNotNull(handler.getThumbnails());
        Assert.assertNotNull(handler.getLinks());
    }

}
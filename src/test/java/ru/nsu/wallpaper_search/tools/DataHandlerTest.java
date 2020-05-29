package ru.nsu.wallpaper_search.tools;

import junit.framework.TestCase;
import org.junit.Assert;

public class DataHandlerTest extends TestCase {

    private static final int width = 1920, height = 1080;
    private static final String request = "cat";

    private static DataHandler handler = new DataHandler();

    public void testGetThumbnails() {
        handler.setWidth(width);
        handler.setHeight(height);
        handler.setTheme(request);
        handler.run();
        Assert.assertNotNull(handler.getThumbnails());
    }

    public void testGetLinks() {
        handler.setWidth(width);
        handler.setHeight(height);
        handler.setTheme(request);
        handler.run();
        Assert.assertNotNull(handler.getLinks());
    }
}
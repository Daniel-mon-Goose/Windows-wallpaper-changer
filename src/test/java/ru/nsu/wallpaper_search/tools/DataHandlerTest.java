package ru.nsu.wallpaper_search.tools;

import junit.framework.TestCase;
import org.junit.Assert;

public class DataHandlerTest extends TestCase {

    private static final int width = 1920, height = 1080;
    private static final String request = "cat";

    private static DataHandler handler = new DataHandler();

    public void testSetParams() {
        handler.setWidth(width);
        handler.setHeight(height);
        handler.setTheme(request);

        Assert.assertTrue(handler.getWidth().equals(width) && handler.getHeight().equals(height) && handler.getTheme().equals(request));

    }

    public void testGetThumbnails() {
        handler.run();
        Assert.assertNotNull(handler.getThumbnails());
    }

    public void testGetLinks() {
        handler.run();
        Assert.assertNotNull(handler.getLinks());
    }
}
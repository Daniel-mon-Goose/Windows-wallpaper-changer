package ru.nsu.wallpaper_search.tools;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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

    @Test
    public void failureDataHand() {
        handler.setWidth(0);
        handler.setHeight(0);
        handler.setTheme(request);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.run();
        Assert.assertTrue(handler.getLinks().isEmpty());
    }

}
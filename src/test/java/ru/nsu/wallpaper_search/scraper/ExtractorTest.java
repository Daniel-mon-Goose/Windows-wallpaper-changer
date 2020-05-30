package ru.nsu.wallpaper_search.scraper;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ExtractorTest {
    private static final Class<IllegalStateException> error = IllegalStateException.class;

    @Test
    public void instanceTest() {
        Assert.assertThrows(error, () -> {
            var instance = new Extractor();
        });
    }

    @Test
    public void getPicturesTest() {
        try {
            var doc = Extractor.accessPicturesPage("1920x1080", "zelda");
            Assert.assertNotNull(Extractor.getPictures(doc));
        } catch (IOException e) {
            Assert.assertThrows(IOException.class, () -> Extractor.getDoc("dummy", false));
        }
    }
}

package ru.nsu.wallpaper_search.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
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
            File input = new File("./test.html");
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Assert.assertFalse(Extractor.getPictures(doc).isEmpty());
        } catch (IOException e) {
            Assert.assertThrows(IOException.class, () -> Extractor.getDoc("dummy", false));
        }
    }

    @Test
    public void failGetPicturesTest() {
        try {
            var doc = Extractor.accessPicturesPage("1920x1080", "zelda");
            Assert.assertNotNull(Extractor.getPictures(doc);
        } catch (IOException e) {
            Assert.assertThrows(IOException.class, () -> Extractor.getDoc("dummy", false));
        }
    }

}

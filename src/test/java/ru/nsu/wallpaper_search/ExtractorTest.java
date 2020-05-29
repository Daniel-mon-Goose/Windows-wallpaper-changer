package ru.nsu.wallpaper_search;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.wallpaper_search.scraper.Extractor;

public class ExtractorTest {
    private static final Class<IllegalStateException> error = IllegalStateException.class;

    @Test
    public void instanceTest() {
        Assert.assertThrows(error, () -> {
            var instance = new Extractor();
        });
    }
}

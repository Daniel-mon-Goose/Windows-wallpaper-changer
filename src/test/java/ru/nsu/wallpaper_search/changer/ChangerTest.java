package ru.nsu.wallpaper_search.changer;

import org.junit.Assert;
import org.junit.Test;

public class ChangerTest {
    private static final Class<IllegalStateException> error = IllegalStateException.class;

    @Test
    public void instanceTest() {
        Assert.assertThrows(error, () -> {
            var instance = new Changer();
        });
    }
}

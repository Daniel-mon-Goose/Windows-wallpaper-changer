package ru.nsu.wallpaper_search;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.wallpaper_search.exceptions.ImageLoadException;
import ru.nsu.wallpaper_search.tools.ImageLoader;
import ru.nsu.wallpaper_search.tools.PicCell;

import java.io.IOException;

public class ImageLoaderTest {
    private static final Class<ImageLoadException> error = ImageLoadException.class;

    private static final String blockedLink = "https://picstatio.com/download/1600x900/utngox/The-Legend" +
            "-Of-Zelda-Breath-Of-The-Wild-video-game-gaming-art-wallpaper.jpg";
    private static final String workingLink = "https://www.wallpapermaiden.com/wallpaper/25017/" +
            "download/1600x900/princess-zelda-elf-ears-the-legend-of-zelda-smiling-blonde-anime-games.jpeg";

    private static final String alternatePath = "C:/Users/%s/WallpaperSearcher/found.jpg";
    private static final String path = "C:/Users/%s/found.jpg";

    @Test
    public void loadTest() throws IOException {
        var workingCell = new PicCell("https://im0-tub-ru.yandex.net/i?id=81ef90734d287839980ce545c9dc3696&n=13");
        workingCell.addOriginal(blockedLink);
        workingCell.addOriginal(workingLink);
        workingCell.addOriginal(blockedLink);

        var path = ImageLoader.load(workingCell);
        var user = System.getProperty("user.name");
        Assert.assertTrue(String.format(path, user).equals(path)
                || String.format(alternatePath, user).equals(path));
    }

    @Test
    public void failureTest() {
        var blockedCell = new PicCell("https://im0-tub-ru.yandex.net/i?id=b92098ac236759f72aa489975efb76c8&n=13");
        blockedCell.addOriginal(blockedLink);

        Assert.assertThrows(error, () -> ImageLoader.load(blockedCell));
    }
}

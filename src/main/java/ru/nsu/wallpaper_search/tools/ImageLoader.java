package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.exceptions.ImageLoadException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageLoader {
    private static final String ALTERNATEPATH = "C:/Users/%s/WallpaperSearcher/found.jpg";
    private static final String PATH = "C:/Users/%s/found.jpg";
    private static final String FOLDER = "C:/Users/%s/WallpaperSearcher";

    private ImageLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static String load(PicCell links) throws ImageLoadException {
        long flag = -1;
        String user = System.getProperty("user.name");
        String datPath =  String.format(FOLDER, user);

        try {
            if (!Files.exists(Paths.get(datPath))) {
                Files.createDirectory(Paths.get(datPath));
            }
            datPath = ALTERNATEPATH;
        } catch (IOException ignored) {
            datPath = PATH;
        }

        datPath = String.format(datPath, user);
        for (var link: links.getOriginals()) {
            try (var netStream = new URL(link).openStream()) {
                flag = Files.copy(netStream, Paths.get(datPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ignored) { }

            if (flag != -1) {
                return datPath;
            }
        }

        throw new ImageLoadException("Failed to access an image via any link");
    }
}

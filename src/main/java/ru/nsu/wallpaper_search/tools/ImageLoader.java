package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.exceptions.ImageLoadException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageLoader {
    private static final String FOLDER = "C:/Users/%s/WallpaperSearcher";
    private static final String ALTERNATEFOLDER = "C:/Users/%s/";
    private static final String PICNAME = "/found.jpg";

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
            datPath = FOLDER + PICNAME;
        } catch (IOException e) {
            datPath = ALTERNATEFOLDER + PICNAME;
        }

        datPath = String.format(datPath, user);
        for (var link: links.getOriginals()) {
            try (var netStream = new URL(link).openStream()) {
                flag = Files.copy(netStream, Paths.get(datPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) { flag = -1; }

            if (flag != -1) {
                return datPath;
            }
        }

        throw new ImageLoadException("Failed to access an image via any link");
    }

    public static String loadThumnail(PicCell links) throws ImageLoadException {
        long flag = -1;
        String user = System.getProperty("user.name");
        String datPath = String.format(FOLDER, user);

        try {
            if (!Files.exists(Paths.get(datPath))) {
                Files.createDirectory(Paths.get(datPath));
            }
            datPath = FOLDER + PICNAME;
        } catch (IOException e) {
            datPath = ALTERNATEFOLDER + PICNAME;
        }

        datPath = String.format(datPath, user);
        var link = links.getThumb();
        try (var netStream = new URL(link).openStream()) {
            flag = Files.copy(netStream, Paths.get(datPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            flag = -1;
        }

        if (flag != -1) {
            return datPath;
        }


        throw new ImageLoadException("Failed to access an thumbnail image");
    }

}

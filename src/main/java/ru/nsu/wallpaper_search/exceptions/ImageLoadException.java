package ru.nsu.wallpaper_search.exceptions;

import java.io.IOException;

public class ImageLoadException extends IOException {

    public ImageLoadException(String message) {
        super(message);
    }
}

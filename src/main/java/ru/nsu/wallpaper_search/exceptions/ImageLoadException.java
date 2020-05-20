package ru.nsu.wallpaper_search.exceptions;

import java.io.IOException;

public class ImageLoadException extends IOException {
    public ImageLoadException() {
        super();
    }

    public ImageLoadException(String message) {
        super(message);
    }

    public ImageLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageLoadException(Throwable cause) {
        super(cause);
    }
}

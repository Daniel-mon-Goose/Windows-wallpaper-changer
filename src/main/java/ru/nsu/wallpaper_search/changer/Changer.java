package ru.nsu.wallpaper_search.changer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Changer {
    private static final String SCRIPTPATH = "ru/nsu/wallpaper_search/changer/change.ps1";

    private Changer() {
        throw new IllegalStateException("Utility class");
    }

    public static void changeDesktopImage(String path) throws FileSystemException {
        var powershellPath = Objects.requireNonNull(Changer.class.getClassLoader()
                .getResource(SCRIPTPATH)).getPath();
        powershellPath = String.join(File.separator, powershellPath.split("/"));
        if (powershellPath.startsWith(File.separator)) {
            powershellPath = powershellPath.replaceFirst(Pattern.quote(File.separator), "");
        }

        try {
            var pb = new ProcessBuilder();
            pb.command("cmd", "/c", "powershell", "-ExecutionPolicy", "Unrestricted", "-File", powershellPath, path);
            pb.start();
        } catch (IOException e) {
            throw new FileSystemException(path);
        }
    }
}

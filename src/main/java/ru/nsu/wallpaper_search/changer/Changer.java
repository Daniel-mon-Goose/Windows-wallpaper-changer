package ru.nsu.wallpaper_search.changer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.nio.file.Paths;
import java.util.Objects;

public class Changer {
    private static final String SCRIPTPATH = "ru/nsu/wallpaper_search/changer/change.ps1";

    private Changer() {
        throw new IllegalStateException("Utility class");
    }

    public static void changeDesktopImage(String path) throws FileSystemException {
        URI powershellURI;
        try {
            powershellURI = Objects.requireNonNull(Changer.class.getClassLoader()
                    .getResource(SCRIPTPATH)).toURI();
        } catch (URISyntaxException | NullPointerException e) {
            throw new FileSystemException(SCRIPTPATH);
        }
        var powershellFile = Paths.get(powershellURI).toFile();
        var powershellPath = powershellFile.getAbsolutePath();
        try {
            var pb = new ProcessBuilder();
            pb.command("cmd", "/c", "powershell", "-ExecutionPolicy", "Unrestricted", "-File", powershellPath, path);
            pb.start();
        } catch (IOException e) {
            throw new FileSystemException(path);
        }
    }
}

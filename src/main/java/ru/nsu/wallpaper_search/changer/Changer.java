package ru.nsu.wallpaper_search.changer;

import com.github.frimtec.libraries.jpse.PowerShellExecutor;

import java.io.File;
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
            PowerShellExecutor executor = PowerShellExecutor.instance();
            executor.execute("Set-ExecutionPolicy -ExecutionPolicy Bypass -Scope CurrentUser");
            executor.execute(powershellPath + " " + path);
        } catch (Exception e) {
            throw new FileSystemException(path);
        }
    }
}

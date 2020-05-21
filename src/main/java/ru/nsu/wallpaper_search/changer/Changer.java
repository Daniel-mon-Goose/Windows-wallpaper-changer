package ru.nsu.wallpaper_search.changer;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import java.util.Map;

public class Changer {
    public static void changeDesktopImage(String path) {
        SPI.INSTANCE.SystemParametersInfo(
                new WinDef.UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new WinDef.UINT_PTR(0),
                path,
                new WinDef.UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }

    public interface SPI extends StdCallLibrary {
        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        SPI INSTANCE = Native.load("user32", SPI.class, Map.of(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE,
                OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE));

        void SystemParametersInfo(WinDef.UINT_PTR uiAction, WinDef.UINT_PTR uiParam,
                                  String pvParam, WinDef.UINT_PTR fWinIni
        );
    }
}

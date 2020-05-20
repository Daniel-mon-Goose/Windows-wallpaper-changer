module ru.nsu.wallpaper_search.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires org.json;
    requires org.jsoup;
    requires java.desktop;

    opens ru.nsu.wallpaper_search.gui to javafx.fxml;
    exports ru.nsu.wallpaper_search.gui;
}
package ru.nsu.wallpaper_search.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrimaryController {

    @FXML
    public TextField width;
    @FXML
    public TextField height;
    @FXML
    public TextField request;

    @FXML
    private void switchToSecondary() throws IOException {
        Gui.setRoot("secondary");
    }

    private static void check(TextField field) {

    }

    @FXML
    private void checkWidth() {
        check(width);
    }

    @FXML
    private void checkHeight() {
        check(height);
    }
}


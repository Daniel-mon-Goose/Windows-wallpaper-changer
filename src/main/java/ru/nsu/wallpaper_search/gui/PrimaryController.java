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
        int maxCharacters = 4;
        String line = field.getText();
        var size = line.length() - 1;

        if (!line.isEmpty() && !Character.isDigit(line.charAt(size))) {
            field.setText(line.substring(0, size));
            field.positionCaret(size);
        }

        line = field.getText();
        if (line.length() > maxCharacters) {
            field.setText(line.substring(0, maxCharacters));
            field.positionCaret(maxCharacters);
        }
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


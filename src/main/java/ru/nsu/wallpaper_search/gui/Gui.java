package ru.nsu.wallpaper_search.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import java.io.IOException;

public class Gui extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Rectangle2D screenRes = Screen.getPrimary().getBounds();
        scene = new Scene(loadFXML("primary"), screenRes.getWidth() * 0.3, screenRes.getHeight() * 0.4);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

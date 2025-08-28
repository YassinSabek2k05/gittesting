package com.yassin.javafx;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/yassin/javafx/hello-view.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 1200, 800);
        
        // Add modern CSS styling
        scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/com/yassin/javafx/style.css")).toExternalForm());
        
        stage.setTitle("JavaFX Modern Dashboard");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        
        // Add application icon (if available)
        try {
            stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/com/yassin/javafx/icon.png"))));
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
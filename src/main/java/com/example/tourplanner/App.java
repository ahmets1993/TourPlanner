package com.example.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("tourPanel.fxml"));
        primaryStage.setTitle("Happy Wheels");
        File file = new File("C:/Users/ahmet/IdeaProjects/TourPlanner/src/main/resources/Images/logo.png");
        Image image = new Image(file.toURI().toString());
        primaryStage.getIcons().add(image);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1200, 870));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


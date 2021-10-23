package com.example.dohoa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Overall.DictionaryManagement;
import Overall.Dictionary;
import Overall.ReadFileWithBufferedReader;
import Overall.Word;
import Overall.DictionaryPractice;
import Overall.HistorySearching;
import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage window;
    public static Scene sceneAdd;
    public static Scene sceneMain;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoaderAdd = new FXMLLoader(HelloApplication.class.getResource("Add.fxml"));
        sceneMain = new Scene(fxmlLoader.load(), 1212, 769);
        sceneAdd = new Scene(fxmlLoaderAdd.load(), 1212, 769);
        window = stage;
        window.setTitle("Hello!");
        window.setScene(sceneMain);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
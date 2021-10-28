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
    public static Scene sceneLoading;
    public static Scene sceneHistorySearching;
    public static Scene sceneFavouriteWord;
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoaderLoading = new FXMLLoader(HelloApplication.class.getResource("Loading.fxml"));
        sceneLoading = new Scene(fxmlLoaderLoading.load(), 500, 500);
        window.setTitle("Loading");
        window.setScene(sceneLoading);
        window.show();
    }

    @Override
    public void stop() throws IOException {
        System.out.println("Stage is closing");
        DictionaryManagement.JSonCreate(HelloController.getDt());
        System.out.println("saved");
    }

    public static void main(String[] args) {
        launch();
    }
}
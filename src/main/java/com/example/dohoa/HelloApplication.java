package com.example.dohoa;

import javafx.application.Application;
import javafx.application.Platform;
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
  public static Scene sceneSaving;

  @Override
  public void start(Stage stage) throws IOException {
    window = stage;
    FXMLLoader fxmlLoaderSaving = new FXMLLoader(
        HelloApplication.class.getResource("Saving.fxml"));
    sceneSaving = new Scene(fxmlLoaderSaving.load(), 1150, 769);
    window.setScene(sceneSaving);
    FXMLLoader fxmlLoaderLoading = new FXMLLoader(
        HelloApplication.class.getResource("Loading.fxml"));
    sceneLoading = new Scene(fxmlLoaderLoading.load(), 1150, 769);
    window.setTitle("Loading");
    window.setScene(sceneLoading);
    window.show();
    window.setOnCloseRequest(event -> {
      try {
        save();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public void save() throws IOException {
    window.setScene(sceneSaving);
    window.setTitle("Saving");
    window.show();
    System.out.println("Stage is closing");
    try {
      DictionaryManagement.JSonCreate(HelloController.getDt());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("saved");
  }

  public static void main(String[] args) {
    launch();
  }
}
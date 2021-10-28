package com.example.dohoa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class LoadingController implements Initializable {

  @FXML
  private StackPane rootPane;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    new SplashScreen().start();
  }

  class SplashScreen extends Thread {

    @Override
    public void run() {

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          FXMLLoader fxmlLoader = new FXMLLoader(
              HelloApplication.class.getResource("hello-view.fxml"));
          FXMLLoader fxmlLoaderAdd = new FXMLLoader(
              HelloApplication.class.getResource("Add.fxml"));
//          FXMLLoader fxmlLoaderHistory = new FXMLLoader(
//                  HelloApplication.class.getResource("HistorySearching.fxml"));
//          FXMLLoader fxmlFavouriteWord = new FXMLLoader(
//                  HelloApplication.class.getResource("FavouriteWord.fxml"));
          try {
            HelloApplication.sceneMain = new Scene(fxmlLoader.load(), 1212, 769);
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            HelloApplication.sceneAdd = new Scene(fxmlLoaderAdd.load(), 1212, 769);
          } catch (IOException e) {
            e.printStackTrace();
          }
//          try {
//            HelloApplication.sceneHistorySearching = new Scene(fxmlLoaderHistory.load(), 1212, 769);
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//          try {
//            HelloApplication.sceneFavouriteWord = new Scene(fxmlFavouriteWord.load(), 1212, 769);
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
          HelloApplication.window.setTitle("Dictionary");
          HelloApplication.window.setScene(HelloApplication.sceneMain);
         // rootPane.getScene().getWindow().hide();
        }
      });

    }
  }
}

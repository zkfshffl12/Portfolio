package org.zerock.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class InsertScene extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    } // main

    @Override
    public void init() throws Exception {
        super.init();
    } // init

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Insert.fxml"));
        Parent root = loader.<AnchorPane>load();
        primaryStage.setScene(new Scene(root));

        primaryStage.setTitle("등록");
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initStyle(StageStyle.UTILITY);

        primaryStage.show();
    } // start

} // end class
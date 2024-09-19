package org.zerock.myapp;

import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class MainScene extends Application {

	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		Application.launch(args);
	} // main
	
	@Override
	public void init() throws Exception {
		log.trace("init() invoked.");
		
		super.init();
	} // init
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		log.trace("start({}) invoked.", primaryStage);
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
		
		primaryStage.setTitle("사원관리");
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
//		primaryStage.initStyle(StageStyle.UTILITY);
		
		primaryStage.show();
	} // start

} // end class

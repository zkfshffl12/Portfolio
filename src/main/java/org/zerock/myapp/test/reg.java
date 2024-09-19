package org.zerock.myapp.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class reg extends Application{

	@Override
	public void init() throws Exception {

	} // init

	@Override
	public void stop() throws Exception {
	
	} // stop
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// -------------
		// Step1. FXML 파일의 적용
		// -------------
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("reg.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
		
		
		primaryStage.setTitle("등록");
		primaryStage.initStyle(StageStyle.UTILITY);
		
		
		
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		
		primaryStage.show();
	} // start

	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	} // main

} // end class

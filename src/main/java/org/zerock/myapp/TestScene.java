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
public class TestScene extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	} // main
	
	@Override
	public void init() throws Exception {
		super.init();
	} // init
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 1st.method
//		URL fxml = this.getClass().getResource("MainScene.fxml");
//		Parent root = FXMLLoader.<HBox>load(fxml);
//		Scene scene = new Scene(root);
		
		// 2nd.method
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TestScene.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
		
		primaryStage.setTitle("테스트입니다.");
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.initStyle(StageStyle.UTILITY);
		
		primaryStage.show();
	} // start

} // end class

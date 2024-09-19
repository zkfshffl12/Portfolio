package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class MainSceneController implements Initializable{
	@FXML private Button search;
	@FXML private Button insert;
	@FXML private Button insert1;
	@FXML private Button update;
	@FXML private Button update1;
	@FXML private Button remove;
	@FXML private Button home;
	@FXML private Button back;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		log.trace("initialize({}, {}) invoked.", url, rb);
	} // initialize
	
	
	@FXML
	public void search(ActionEvent e) throws IOException {
		log.trace("search({}) invoked.", e);
		
		// 1. ActionEvent e 에서 윈도우 객체 얻기
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		// 2. 해당 객체(버튼)가 있는 위치의 윈도우 객체 얻기
//		Stage primaryStage = (Stage) search.getScene().getWindow();
		
		loadPage("Search", primaryStage);
	} // search 
	
	@FXML
	public void insert(ActionEvent e) throws IOException {
		log.trace("insert({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("minb", primaryStage);
	} // insert
	
	@FXML
	public void insert1(ActionEvent e) throws IOException {
		log.trace("insert1({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("reg", primaryStage);
	} // insert1
	
	@FXML
	public void update(ActionEvent e) throws IOException {
		log.trace("update({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("dpt", primaryStage);
	} // update
	
	@FXML
	public void update1(ActionEvent e) throws IOException {
		log.trace("update1({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("Main", primaryStage);
	} // update1
	
	@FXML
	public void remove(ActionEvent e) throws IOException {
		log.trace("remove({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("Delete", primaryStage);
	} // remove
	
	@FXML
	public void home(ActionEvent e) throws IOException {
		log.trace("home({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("MainScene", primaryStage);
	} // home 
	
	@FXML
	public void back(ActionEvent e) throws IOException {
		log.trace("back({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("MainScene", primaryStage);
	} // back 
	
	
	private void loadPage(String page, Stage primaryStage) throws IOException {
		try {    
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(page + ".fxml"));
			Parent root = loader.<AnchorPane>load();
			primaryStage.setScene(new Scene(root));
		} catch (IOException e) {  
			throw new IOException(e);
		} // try catch
	} // loadPage
	
} // end class

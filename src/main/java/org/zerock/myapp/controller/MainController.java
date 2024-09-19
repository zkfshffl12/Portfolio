package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MainController implements Initializable {
    
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Text text1;
    @FXML private Text text2;
    @FXML private ComboBox<String> combolist;
    
    ObservableList<String> table = FXCollections.observableArrayList();
    
    public MainController() {
    	for(int i = 100; i < 207; i++) {
    		table.add(String.valueOf(i));
    	} // for
    } 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	combolist.setItems(table);
    } // initialize
    
    @FXML
    public void btn1Method(ActionEvent e) throws IOException {
    	log.trace("Back button cliked");
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
    	Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	
    	primaryStage.setScene(new Scene(root));
    } // btn1Method 뒤로가기
    
    @FXML
    public void btn2Method(ActionEvent e) throws IOException {
    	log.trace("Home button cliked");
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
    	Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	
    	primaryStage.setScene(new Scene(root));
    } // btn2Method 홈으로 가기
    
    @FXML
    public void btn3Method(ActionEvent e) throws IOException {
    	log.trace("correction button clicked");
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Info.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        InfoController root1 = loader.getController();
        root1.setlist(combolist.getSelectionModel().getSelectedItem());
        
        
        primaryStage.setScene(new Scene(root));
    } // btn3Method 정보수정 화면으로
    
    

} // end class

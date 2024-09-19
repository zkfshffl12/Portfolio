package org.zerock.myapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class D_departmentsController implements Initializable {
	
	@FXML private ComboBox<String> cDepartmentId;
	@FXML private ComboBox<String> cDepartmentName;
	@FXML private ComboBox<String> cManagerId;
	@FXML private ComboBox<String> cLocationId;

	@FXML private TextField tDepartmentId, tDepartmentName, tManagerId, tLocationId;
	
	@FXML private Label lDepartmentId, lDepartmentName, lManagerId, lLocationId;
	
	ObservableList<String> cInt = FXCollections.observableArrayList("=","!=",">=",">","<=","<");
	ObservableList<String> cString = FXCollections.observableArrayList("IN","LIKE");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.cDepartmentId.setItems(cInt);
		this.cDepartmentName.setItems(cString);
		this.cManagerId.setItems(cInt);
		this.cLocationId.setItems(cInt);
		
		this.cDepartmentId.getSelectionModel().selectFirst();
		this.cDepartmentName.getSelectionModel().selectFirst();
		this.cManagerId.getSelectionModel().selectFirst();
		this.cLocationId.getSelectionModel().selectFirst();

	}//initialize
	String[] labelArray = new String[4];
	String[] comboArray = new String[4];
	String[] textArray = new String[4];
	
	public void getLabel() {
		labelArray[0] = this.lDepartmentId.getText();
		labelArray[1] = this.lDepartmentName.getText(); 
		labelArray[2] = this.lManagerId.getText();
		labelArray[3] = this.lLocationId.getText(); 

	}//getLabel
	
	public void getcombo() {
		comboArray[0] = this.cDepartmentId.getValue();
		comboArray[1] = this.cDepartmentName.getValue(); 
		comboArray[2] = this.cManagerId.getValue();
		comboArray[3] = this.cLocationId.getValue(); 

	}//get combo
	
	public void getTextField() {
		textArray[0] = this.tDepartmentId.getText();
		textArray[1] = this.tDepartmentName.getText(); 
		textArray[2] = this.tManagerId.getText();
		textArray[3] = this.tLocationId.getText(); 
 
	}//getText
	
	
}//end class

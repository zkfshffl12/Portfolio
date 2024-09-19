package org.zerock.myapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class D_employeesController implements Initializable {
	
	@FXML private ComboBox<String> cEmployeeId;
	@FXML private ComboBox<String> cFirstName;
	@FXML private ComboBox<String> cLastName;
	@FXML private ComboBox<String> cEmail;
	@FXML private ComboBox<String> cPhoneNumber;
	@FXML private ComboBox<String> cHireDate;
	@FXML private ComboBox<String> cJobId;
	@FXML private ComboBox<String> cSalary;
	@FXML private ComboBox<String> cCommissionPct;
	@FXML private ComboBox<String> cManagerId;
	@FXML private ComboBox<String> cDepartmentId;
	
	@FXML private TextField tEmployeeId,tFirstName, tLastName, tEmail, tPhoneNumber, 
	                  		tJobId, tSalary, tCommissionPct, tManagerId, tDepartmentId;
	
	@FXML private DatePicker datepicker; 
	
	@FXML private Label lEmployeeId,lFirstName, lLastName, lEmail, lPhoneNumber, lHireDate,
						lJobId, lSalary, lCommissionPct, lManagerId, lDepartmentId;
	
	ObservableList<String> cInt = FXCollections.observableArrayList("=","!=",">=",">","<=","<");
	ObservableList<String> cString = FXCollections.observableArrayList("IN","LIKE");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.cEmployeeId.setItems(cInt);
		this.cFirstName.setItems(cString);
		this.cLastName.setItems(cString);
		this.cEmail.setItems(cString);
		this.cPhoneNumber.setItems(cString);
		this.cHireDate.setItems(cInt);
		this.cJobId.setItems(cString);
		this.cSalary.setItems(cInt);
		this.cCommissionPct.setItems(cInt);
		this.cManagerId.setItems(cInt);
		this.cDepartmentId.setItems(cInt);
		
		this.cEmployeeId.getSelectionModel().selectFirst();
		this.cFirstName.getSelectionModel().selectFirst();
		this.cLastName.getSelectionModel().selectFirst();
		this.cEmail.getSelectionModel().selectFirst();
		this.cPhoneNumber.getSelectionModel().selectFirst();
		this.cHireDate.getSelectionModel().selectFirst();
		this.cJobId.getSelectionModel().selectFirst();
		this.cSalary.getSelectionModel().selectFirst();
		this.cCommissionPct.getSelectionModel().selectFirst();
		this.cManagerId.getSelectionModel().selectFirst();
		this.cDepartmentId.getSelectionModel().selectFirst();

	}//initialize
	
	String[] labelArray = new String[11];
	String[] comboArray = new String[11];
	String[] textArray = new String[11];
	
	public void getLabel() {
		labelArray[0] = this.lEmployeeId.getText();
		labelArray[1] = this.lFirstName.getText(); 
		labelArray[2] = this.lLastName.getText();
		labelArray[3] = this.lEmail.getText(); 
		labelArray[4] = this.lPhoneNumber.getText(); 
		labelArray[5] = this.lHireDate.getText();
		labelArray[6] = this.lJobId.getText(); 
		labelArray[7] = this.lSalary.getText(); 
		labelArray[8] = this.lCommissionPct.getText(); 
		labelArray[9] = this.lManagerId.getText(); 
		labelArray[10] = this.lDepartmentId.getText();
	}//getLabel
	
	public void getcombo() {
		comboArray[0] = this.cEmployeeId.getValue();
		comboArray[1] = this.cFirstName.getValue(); 
		comboArray[2] = this.cLastName.getValue();
		comboArray[3] = this.cEmail.getValue(); 
		comboArray[4] = this.cPhoneNumber.getValue(); 
		comboArray[5] = this.cHireDate.getValue();
		comboArray[6] = this.cJobId.getValue(); 
		comboArray[7] = this.cSalary.getValue(); 
		comboArray[8] = this.cCommissionPct.getValue(); 
		comboArray[9] = this.cManagerId.getValue(); 
		comboArray[10] = this.cDepartmentId.getValue();
		
		log.info(comboArray[10]);
	}//get combo
	
	public void getTextField() {
		textArray[0] = this.tEmployeeId.getText();
		textArray[1] = this.tFirstName.getText(); 
		textArray[2] = this.tLastName.getText();
		textArray[3] = this.tEmail.getText(); 
		textArray[4] = this.tPhoneNumber.getText(); 
		
		if(datepicker.getValue() == null) {
			textArray[5] = "";
		}else {
			textArray[5] = "TO_DATE('" + datepicker.getValue() + "', 'YYYY-MM-DD')";
		}//date picker if-else
		
		textArray[6] = this.tJobId.getText(); 
		textArray[7] = this.tSalary.getText(); 
		textArray[8] = this.tCommissionPct.getText(); 
		textArray[9] = this.tManagerId.getText(); 
		textArray[10] = this.tDepartmentId.getText();
	}//getText
	
	
}//end class

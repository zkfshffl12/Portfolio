package org.zerock.myapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class ConditionController implements Initializable{
	@FXML private AnchorPane condition;
	@FXML private Label table;
	@FXML private TextField column;
	@FXML private ComboBox<String> comboAndOr;
	@FXML private ComboBox<String> comboOperator;
	@FXML private TextField value;
	@FXML private DatePicker date;
	@FXML private Button remove;
	
	private List<ConditionController> parentList;
	private VBox parentVBox;

	ObservableList<String> andOr = FXCollections.observableArrayList("AND", "OR");
	// 숫자, 날짜 타입 전용 연산자
	ObservableList<String> operator1 = FXCollections.observableArrayList("=", "!=", ">", ">=", "<", "<=");
	// 문자 타입 전용 연산자
	ObservableList<String> operator2 = FXCollections.observableArrayList("IN", "LK");

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		log.trace("initialize({}, {}) invoked.", url, rb);
		
		comboAndOr.setItems(andOr);
		comboAndOr.getSelectionModel().selectFirst();
	} // initialize
	
	
	@FXML
	public void date(ActionEvent e) {
		log.trace("date({}) invoked.", e);

		value.setText("TO_DATE('" + date.getValue() + "', 'YYYY-MM-DD')");
	} // date
	
	@FXML
	public void remove(ActionEvent e) {
		log.trace("remove({}) invoked.", e);
		
		int removeIndex = parentVBox.getChildren().indexOf(condition);
		parentList.remove(removeIndex);

		parentVBox.getChildren().remove(condition);
		
		if(removeIndex == 0 & parentList.size() != 0) {
			parentList.get(0).setVisible();
		} // if
	} // remove
	
	
	// set
	public void setParentVBox(VBox parentVBox) {
        this.parentVBox = parentVBox;
    } // setParentVBox
	
	public void setParentList(List<ConditionController> parentList) {
        this.parentList = parentList;
    } // setList
	
	public void setVisible() {
		this.comboAndOr.setVisible(false);
	} // setVisible

	
	// get
	public String getTable() {
		return table.getText();
    } // getTable
	
	public String getColumn() {
		return column.getText();
    } // getColumn
	
	public String getValue() {
        return value.getText();
    } // getValue
	
	public String getComboOperator() {
        return comboOperator.getSelectionModel().getSelectedItem();
    } // getComboOperator
	
	public String getComboAndOr() {
		return comboAndOr.getSelectionModel().getSelectedItem();
    } // getComboAndOr
	
	
	// Condition 객체의 테이블, 컬럼, 연산자, DatePicker 사용여부 결정
	public void initData(String tableName, String columnName) {
		switch(tableName) {
		case "EMPLOYEES" 
			-> { table.setText(tableName + " e"); column.setText("e." + columnName); }	
		case "DEPARTMENTS" 
			-> { table.setText(tableName + " d"); column.setText("d." + columnName); }
		case "LOCATIONS" 
			-> { table.setText(tableName + " l"); column.setText("l." + columnName); }
		} // switch
		
		switch(columnName) {
		case "EMPLOYEE_ID", "SALARY", "COMMISION_PCT", "MANAGER_ID", "DEPARTMENT_ID"
			, "LOCATION_ID"
			-> {
				comboOperator.setItems(operator1); 
				
				value.textProperty().addListener((observable, oldValue, newValue) -> {
					if(newValue.matches(".*\\D.*")) {
						log.trace("{} 은 적절하지 못한 값 입니다. {} 으로 되돌립니다.", newValue, oldValue);
						value.setText(oldValue);
					}// if
				}); // Lambda
			}
		case "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE_NUMBER", "DEPARTMENT_NAME"
			, "STREET_ADDRESS", "POSTAL_CODE", "CITY", "STATE_PROVINCE", "CONTRY_ID" 
			-> comboOperator.setItems(operator2);
		case "HIRE_DATE" 
			-> { 
				comboOperator.setItems(operator1);  
				date.setVisible(true); 
			}
		} // enhanced switch

		comboOperator.getSelectionModel().selectFirst();
	} // initData
	
} // end class

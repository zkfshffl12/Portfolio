package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class SearchController implements Initializable{
	@FXML private Button search;
	@FXML private Button add;
	@FXML private ComboBox<String> comboTable;
	@FXML private ComboBox<String> comboColumn;
	@FXML private VBox vbox;
	@FXML private Button home;
	@FXML private Button back;
	
	ObservableList<String> table = FXCollections.observableArrayList("EMPLOYEES", "DEPARTMENTS", "LOCATIONS");
	
	ObservableList<String> employees = FXCollections.observableArrayList("EMPLOYEE_ID", "FIRST_NAME", 
																		"LAST_NAME", "EMAIL", "PHONE_NUMBER", 
																		"HIRE_DATE", "JOB_ID", "SALARY", 
																		"COMMISSION_PCT", "MANAGER_ID", 
																		"DEPARTMENT_ID");
	
	ObservableList<String> departments = FXCollections.observableArrayList(	"DEPARTMENT_ID", "DEPARTMENT_NAME", 
																			"MANAGER_ID", "LOCATION_ID");
	
	ObservableList<String> locations = FXCollections.observableArrayList("LOCATION_ID", "STREET_ADDRESS", 
																		"POSTAL_CODE", "CITY", 
																		"STATE_PROVINCE", "COUNTRY_ID");
	
	// 검색에 사용할 controller 객체들을 가진 List
	List<ConditionController> controllerList = new ArrayList<>();

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		log.trace("initialize({}, {}) invoked.", url, rb);
		
		comboTable.setItems(table);
		comboColumn.setItems(employees);
		comboTable.getSelectionModel().selectFirst();
		comboColumn.getSelectionModel().selectFirst();
	} // initialize
	
	
	@FXML
	public void search(ActionEvent e) throws SQLException, IOException {
		log.trace("search({}) invoked.", e);
		
		
		if(vbox.getChildren().size() == 0) { 
			alertPage("어떤 조건도 추가 되지 않았습니다.", "'A' 버튼을 눌러 조건을 추가하세요.");

			return; 
		} // if 
		
		// SELECT_FROM
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        Set<String> set = new HashSet<>();
        
        for(ConditionController cc : controllerList) {
			String table = cc.getTable();
			
			set.add(table);
		} // enhanced for
        
        for(String str : set) { 
        	sql.append(str).append(", ");
        } // for
        
        if(!set.contains("DEPARTMENTS d")) {
        	if(set.contains("EMPLOYEES e") & set.contains("LOCATIONS l")) {
        		sql.setLength(sql.length()-13);
    			sql.append("DEPARTMENTS d, LOCATIONS l, ");
    		} // if
        } // if
        
        sql.setLength(sql.length()-2);

        // WHERE
        StringBuilder where = new StringBuilder(" WHERE (");
        boolean firstTry = true;
        
		for(ConditionController cc : controllerList) {
			String andOr = cc.getComboAndOr();
			String column = cc.getColumn();
			String operator = cc.getComboOperator();
			String value = cc.getValue();

			if(!value.isEmpty()) {
				
				if(firstTry == false) {
					where.append(" ").append(andOr).append(" ");
				}
				
				switch (operator) {
				case "=", "!=", ">", ">=", "<", "<=" 
					-> where.append(column).append(" ").append(operator).append(" ").append(value); 
				case "IN"
					-> where.append(column).append(" ").append(operator).append(" ('").append(value).append("')");
				case "LK"
					-> where.append(column).append(" ").append("LIKE '%").append(value).append("%'"); 
				} // switch
				
				firstTry = false;
			} // if (!value.equals(""))
		} // enhanced for
		
		// JOIN
		StringBuilder join = new StringBuilder();
		
		if(firstTry == true) {
			where.setLength(where.length()-1);
			
			if(set.size() == 1) {
				where.setLength(where.length()-6);
				sql.append(where);
			} else {
				if(set.contains("EMPLOYEES e") & set.contains("LOCATIONS l")) {
					join.append("(e.DEPARTMENT_ID = d.DEPARTMENT_ID) AND (d.LOCATION_ID = l.LOCATION_ID)");
				} else if(set.contains("EMPLOYEES e") & set.contains("DEPARTMENTS d")) {
					join.append("(e.DEPARTMENT_ID = d.DEPARTMENT_ID)");
				} else if(set.contains("DEPARTMENTS d") & set.contains("LOCATIONS l")) {
					join.append("(d.LOCATION_ID = l.LOCATION_ID)");
				} // if else
				
				sql.append(where).append(join);
			} // if(set.size() == 1) else 

		} else {
			where.append(")");
			
			if(set.size() == 1) {
				sql.append(where);
			} else {
				if(set.contains("EMPLOYEES e") & set.contains("LOCATIONS l")) {
					join.append(" AND (e.DEPARTMENT_ID = d.DEPARTMENT_ID) AND (d.LOCATION_ID = l.LOCATION_ID)");
				} else if(set.contains("EMPLOYEES e") & set.contains("DEPARTMENTS d")) {
					join.append(" AND (e.DEPARTMENT_ID = d.DEPARTMENT_ID)");
				} else if(set.contains("DEPARTMENTS d") & set.contains("LOCATIONS l")) {
					join.append(" AND (d.LOCATION_ID = l.LOCATION_ID)");
				} // if else
				
				sql.append(where).append(join);
			} // if(set.size() == 1) else 
		} // if else
	
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		loadPage("SearchResult", primaryStage, sql.toString());
	} // search 
	
	@FXML
	public void add(ActionEvent e) throws IOException {
		log.trace("add({}) invoked.", e);
		
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Condition.fxml"));
	        AnchorPane condition = loader.load();
	        
	        ConditionController controller = loader.getController();
	        controller.setParentVBox(vbox);
			controller.setParentList(controllerList);
			
			if(controllerList.isEmpty()) { 
				controller.setVisible(); 
			} // if
			
	        String tableName = comboTable.getSelectionModel().getSelectedItem();
	        String columnName = comboColumn.getSelectionModel().getSelectedItem();
	        
	        controller.initData(tableName, columnName);
	        
	        controllerList.add(controller);
			vbox.getChildren().add(condition);
		} catch (IOException ex) {
			throw new IOException(ex);
		}  // try catch
	} // add
	
	@FXML
	public void table(ActionEvent e) {
		log.trace("table({}) invoked.", e);
		
		String tableName = comboTable.getSelectionModel().getSelectedItem();
		
		switch (tableName) {
			case "EMPLOYEES" 
				-> comboColumn.setItems(employees);
			case "DEPARTMENTS" 
				-> comboColumn.setItems(departments);
			case "LOCATIONS" 
				-> comboColumn.setItems(locations);
		} // switch

		comboColumn.getSelectionModel().selectFirst();
	} // table 
	
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
	
	
	private void alertPage(String text1, String text2) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("경고");
        alert.setHeaderText(text1);
        alert.setContentText(text2);
        
        // 기본적으로 ok 버튼을 가지지만, 아래코드 처럼 명시적으로 지정 가능
//        alert.getButtonTypes().setAll(ButtonType.OK);
        
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setAlwaysOnTop(true);

        alert.showAndWait();
	} // alertPage
	
	private void loadPage(String page, Stage primaryStage) throws IOException {
		try {    
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(page + ".fxml"));
			Parent root = loader.<AnchorPane>load();
			primaryStage.setScene(new Scene(root));
		} catch (IOException e) {  
			throw new IOException(e);
		} // try catch
	} // loadPage
	
	private void loadPage(String page, Stage primaryStage, String sql) 
			throws SQLException, IOException {
		try {    
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(page + ".fxml"));
			Parent root = loader.<AnchorPane>load();
			
			SearchResultController controller = loader.getController();
			controller.setSql(sql);
			controller.setPreScene(primaryStage.getScene());
			
			primaryStage.setScene(new Scene(root));
		} catch (Exception e) { 
			throw new SQLException(e);
		} // try catch
	} // loadPage
	
} // end class

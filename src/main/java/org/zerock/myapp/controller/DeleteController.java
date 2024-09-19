package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeleteController implements Initializable {
	static String driver = "oracle.jdbc.OracleDriver";
	static final String jdbcUrl2 = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";
	static String dbUser = "hr";
	static String dbPass = "oracle";
	
	@FXML private Button home;
	@FXML private Button back;
	@FXML private Button delete;
	
	@FXML private ComboBox<String> choose;
	
	@FXML private AnchorPane pane;
	
	ObservableList<String> table = FXCollections.observableArrayList("Employees","Departments");
	
	D_employeesController emc;
	D_departmentsController dec;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.choose.setItems(table);
		this.choose.getSelectionModel().selectFirst();
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Employees.fxml"));
		try {
			AnchorPane EPane = loader.load(); 
			this.pane.getChildren().add(EPane);
			D_employeesController SPane = loader.getController();
			this.emc = SPane;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} //try-catch
		
	}//initialize

	public FXMLLoader changeScene(ComboBox<String> combo) {
		String item = combo.getSelectionModel().getSelectedItem();
		log.info(item);
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource(item + ".fxml"));
		return loader;
	}//changeScene
	
	public void home(ActionEvent e) throws IOException {
		log.info("home");
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
	} // home
	
	public void pickTable(ActionEvent e) {
		
		try {
			FXMLLoader loader = changeScene(this.choose);
			AnchorPane Pane = loader.load(); 
			log.info(loader);
			
			if(choose.getSelectionModel().getSelectedItem().equals("Employees")) {
				log.info("Employees");
				this.emc = loader.getController();
				
			}else {
				log.info("Departments");
				this.dec = loader.getController();
				
			}//if-else
			
			
			this.pane.getChildren().clear();
			this.pane.getChildren().add(Pane);
			
		} catch (IOException ee) {
			ee.printStackTrace();
		} //try-catch
		
		
	}//pickTable
	
	public void delete(ActionEvent e) throws SQLException{
		String sql = null; 
		
		
		if(this.choose.getSelectionModel().getSelectedItem().equals("Employees")) {
		
			sql = "DELETE FROM employees WHERE ";
			
	        
	        this.emc.getLabel();
	        this.emc.getTextField();
	        this.emc.getcombo();
	        
	        
	        
	        for(int i = 0; i < emc.labelArray.length; i++) {
	        	if(emc.textArray[i] == "") {
	        		log.info(emc.labelArray[i]);
	        		continue;
	        	}else {
	        		switch(emc.comboArray[i]) {
	        		 case "=","!=",">=",">","<=","<" -> {
	        			 sql += emc.labelArray[i] + " " + emc.comboArray[i] + " " + emc.textArray[i] + " AND ";
	        		 }//case1
	        		 case "IN" -> {
	        			 sql += emc.labelArray[i] + " = '" + emc.textArray[i] + "'" + " AND "; 
	        		 }//case2
	        		 case "LIKE" -> {
	        			 sql += emc.labelArray[i] + " LIKE " + " '%" + emc.textArray[i] + "%'" + " AND ";
	        		 }//case3
	        		}
	        		
	        		
	        		
	        	}//if-else
	        
	        }//for
        
	        
	        
		}else {
			
			sql = "DELETE FROM departments WHERE ";
			
			this.dec.getLabel();
			this.dec.getTextField();
			this.dec.getcombo();
			
			for(int i = 0; i < dec.labelArray.length; i++) {
				if(dec.textArray[i] == "") {
					log.info(dec.labelArray[i]);
					continue;
				}else {
					switch(dec.comboArray[i]) {
	        		 case "=","!=",">=",">","<=","<" -> {
	        			 sql += dec.labelArray[i] + " " + dec.comboArray[i] + " " + dec.textArray[i] + " AND ";
	        		 }//case1
	        		 case "IN" -> {
	        			 sql += dec.labelArray[i] + " = '" + dec.textArray[i] + "'" + " AND "; 
	        		 }//case2
	        		 case "LIKE" -> {
	        			 sql += dec.labelArray[i] + " LIKE " + " '%" + dec.textArray[i] + "%'" + " AND ";
	        		 }//case3
					
					}//switch
					
				}//if-else
				
			}//for
			
		}//if-else
        
        
        if(sql.equals("DELETE FROM employees WHERE ") | sql.equals("DELETE FROM departments WHERE ") ) {
        	return;
        }else {
        	sql = sql.substring(0, sql.length() - 5 );
        	log.info(sql);
        }//if-else2
        
        @Cleanup Connection conn = DriverManager.getConnection(jdbcUrl2, dbUser, dbPass);
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
	
        pstmt.executeUpdate();
	
	}//delete
	
}//end class

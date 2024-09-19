package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class SearchResultController implements Initializable{
	@FXML private TextArea textarea;
	@FXML private VBox vbox;
	@FXML private TableView<List<String>> tableview;
	@FXML private Button backtothesearch;
	@FXML private Button home;
	@FXML private Button back;
	
	// 이전 검색조건을 담은 화면을 저장
	Scene preScene;
	
	static String driver = "oracle.jdbc.OracleDriver";	
	static final String jdbcUrl2 = "jdbc:oracle:thin:@DESKTOP-QC1CNTE/XEPDB1";	
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";					
	static String dbUser = "hr";	
	static String dbPass = "oracle";	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		log.trace("initialize({}, {}) invoked.", url, rb);
	} // initialize
	
	
	@FXML
	public void backtothesearch(ActionEvent e) throws IOException {
		log.trace("backtothesearch({}) invoked.", e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		primaryStage.setScene(preScene);
	} // back_tothesearch 
	
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
		loadPage("Search", primaryStage);
	} // back 
	
	public void setPreScene(Scene preScene) {
		this.preScene = preScene;
	} // setPreScene
	
	public void setSql(String sql) throws SQLException {
		textarea.setText(sql);
		
		try {
			@Cleanup Connection conn = DriverManager.getConnection(jdbcUrl2, dbUser, dbPass);
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			@Cleanup ResultSet rs = pstmt.executeQuery();
			
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        
	        for (int i = 1; i <= columnCount; i++) {
                final int columnIndex = i - 1;
                TableColumn<List<String>, String> column = new TableColumn<>(rsmd.getColumnName(i));
                column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(columnIndex)));
                tableview.getColumns().add(column);
            }
	        
	        ObservableList<List<String>> data = FXCollections.observableArrayList();
	        
	        while (rs.next()) {
	            List<String> row = new ArrayList<>();
	            for (int i = 1; i <= columnCount; i++) {
	                row.add(rs.getString(i));
	            } // for
	            data.add(row);
	        } // while
	        
	        tableview.setItems(data);
		} catch(SQLException e) {
			throw new SQLException(e);
		} // try catch
		
	} // setSql
	
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

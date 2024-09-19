package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2

public class dptController implements Initializable {
	@FXML private TextField dpt_IdTF, dpt_NameTF, manager_IdTF, location_IdTF;
	@FXML private Label label_IdTF, label_NameTF, label_manager_IdTF, label_location_IdTF;
	
	// Oracle 데이터베이스 연결 정보
	private final String driver = "oracle.jdbc.OracleDriver";
    private final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";
    private final String user = "HR";
    private final String pass = "oracle";

    private Connection conn;	// 데이터베이스 연결 객체
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {           
            Class.forName(driver);	// JDBC 드라이버 로드
           
            conn = DriverManager.getConnection(url, user, pass);	 // 데이터베이스에 연결
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Failed to connect to the database: {}", e.getMessage());	 // 데이터베이스 연결 실패 시 에러 로그 출력
        } // try-catch
    } // initialize

		
	@FXML public void btn3_Method(ActionEvent e) {
		log.trace("수정하기");
		String sql = "UPDATE DEPARTMENTS SET "; 
		sql += label_NameTF.getText()+"='"+dpt_NameTF.getText()+"',"; 
		sql += label_manager_IdTF.getText()+"="+manager_IdTF.getText()+",";
		sql += label_location_IdTF.getText()+"="+location_IdTF.getText()+" ";
		sql += "WHERE "+ label_IdTF.getText()+"="+dpt_IdTF.getText();
		
		 try (PreparedStatement statement = conn.prepareStatement(sql)) {        
	            int rowsAffected = statement.executeUpdate();	// SQL 실행 및 영향 받은 행 수 확인
	            
	            log.info("Rows affected: {}", rowsAffected);
	        } catch (SQLException ex) {	        	
	            log.error("SQL Error: {}", ex.getMessage());	// SQL 실행 오류 시 에러 로그 출력
	        } // try-catch
		 		
	    log.info("실행된 SQL: {}", sql);	// 실행된 SQL 쿼리 로그 출력
		
	} // btn3
	
	@FXML public void home(ActionEvent e) {
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		try {    
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
			Parent root = loader.<AnchorPane>load();
			primaryStage.setScene(new Scene(root));
		} catch (IOException ee) {            
			ee.printStackTrace();
		}
	} // home

	
} // end class

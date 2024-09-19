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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InfoController implements Initializable {
    
    @FXML private Button cbtn1; // 뒤로가기 
    @FXML private Button cbtn2;	// 홈
    @FXML private Text tile;	// 기본정보
    @FXML private Text text1;	// EMPLOYEE_ID
    @FXML private Text text2;	// FIRST_NAME
    @FXML private Text text3;	// LAST_NAME
    @FXML private Text text4;	// EMAIL
    @FXML private Text text5;	// PHONE_NUMBER
    @FXML private Text text6;	// HIRE_DATE
    @FXML private Text text7;	// JOB_ID
    @FXML private Text text8;	// SALARY
    @FXML private Text text9;	// COMMISSION_PCT
    @FXML private Text text10;	// MANAGER_ID
    @FXML private Text text11;	// DEPARTMENT_ID
    @FXML private TextField label1;
    @FXML private TextField label2;
    @FXML private TextField label3;
    @FXML private TextField label4;
    @FXML private TextField label5;
    @FXML private TextField label6;
    @FXML private TextField label7;
    @FXML private TextField label8;
    @FXML private TextField label9;
    @FXML private TextField label10;
    @FXML private TextField label11; 
    @FXML private ComboBox<String> comboList;	// 직원 선택 콤보 박스
    
    String list;
    
    // Oracle 데이터베이스 연결 정보
 	private final String driver = "oracle.jdbc.OracleDriver";
	static final String jdbcUrl2 = "jdbc:oracle:thin:@DESKTOP-QC1CNTE/XEPDB1";	
    private final String user = "HR";
    private final String pass = "oracle";
     
    private Connection conn;
     
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		try {       
            Class.forName(driver);	
           
            conn = DriverManager.getConnection(jdbcUrl2, user, pass);	
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Failed to connect to the database: {}", e.getMessage());	
        } // try-catch
		
    } // initialize
    
    @FXML
	public void cbtn1Method(ActionEvent e) throws IOException {
    	log.trace("Back button cliked");
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Main.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        primaryStage.setScene(new Scene(root));
    } // cbtn1Method 뒤로 가기
    
	@FXML
	public void cbtn2Method(ActionEvent e) throws IOException {
    	log.trace("Home button cliked");
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
    	Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	
    	primaryStage.setScene(new Scene(root));
    } // cbtn2Method 홈으로 가기
    
    public void setlist(String value) {
    	this.list = value;
    	log.info(this.list);
    	
    	label1.setText(list);
    } // setlist
    
    @FXML
    public void handleEnterPressed(ActionEvent e) {
        log.trace("Enter pressed, updating employee information");
        
         	String sql = "UPDATE EMPLOYEES SET ";
            sql += text2.getText() + " = '" + label2.getText() + "', ";
            sql += text3.getText() + " = '" + label3.getText() + "', ";
            sql += text4.getText() + " = '" + label4.getText() + "', ";
            sql += text5.getText() + " = '" + label5.getText() + "', ";
            sql += text6.getText() + " = TO_DATE('" + label6.getText() + "', 'YYYY-MM-DD'), ";
            sql += text7.getText() + " = '" + label7.getText() + "', ";
            sql += text8.getText() + " = " + label8.getText() + ", ";
            sql += text9.getText() + " = " + label9.getText() + ", ";
            sql += text10.getText() + " = " + label10.getText() + ", ";
            sql += text11.getText() + " = " + label11.getText() + " ";
            sql += "WHERE " + text1.getText() + " = " + this.list;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	 int rowsAffected = pstmt.executeUpdate();
            	
                log.info("Rows affected: {}", rowsAffected);
            } catch (SQLException ex) {
                log.info("SQL Error: {}", ex.getMessage());
            }

        log.info("Executed SQL: {}", sql);
    } // handleEnterPressed
} // end class

package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
@Log4j2


public class minbconroller implements Initializable {

	//버튼
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private Button btn3;
	@FXML private Button btn4;
	//라벨
	@FXML private Label Lab1;
	@FXML private Label Lab2;
	@FXML private Label Lab3;
	@FXML private Label Lab4;
	@FXML private Label Lab5;
	@FXML private Label Lab6;
	@FXML private Label Lab7;
	@FXML private Label Lab8;
	@FXML private Label Lab9;
	@FXML private Label Lab10;
	@FXML private Label Lab11;
	//텍스트	
	@FXML private TextField tef1;
	@FXML private TextField tef2;
	@FXML private TextField tef3;
	@FXML private TextField tef4;
	@FXML private TextField tef5;
	@FXML private TextField tef6;
	@FXML private TextField tef7;
	@FXML private TextField tef8;
	@FXML private TextField tef9;
	@FXML private TextField tef10;
	@FXML private TextField tef11;
	
	String sql = "";
	
	static String driver = "oracle.jdbc.OracleDriver";	
	static final String jdbcUrl2 = "jdbc:oracle:thin:@localhost:1521/XEPDB1";	
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";					
	static String dbUser = "hr";	
	static String dbPass = "oracle";	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.trace("initialize(loc, res) invoked.");
	} 
	
	public void btn11(ActionEvent e) {
		log.info("e ({}) invoked",e);
		claearTex();
	}
	
	public void btn33(ActionEvent e) throws IOException {
		log.info("e ({}) invoked",e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
	}
	
	public void btn44(ActionEvent e) throws IOException {
		log.info("e ({}) invoked",e);
		
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
		Parent root = loader.<AnchorPane>load();
		primaryStage.setScene(new Scene(root));
	}
	
	public void btn22(ActionEvent e) {
		log.info("e ({}) invoked",e);
		String sql="INSERT INTO employees(";
		
		
		for(String List:Label()) {
			log.info(List);
			sql+=List+", ";
		}
		sql=sql.substring(0,sql.length()-2)+") VALUES(";
		for(String List:TextField()) {
			log.info(List);
			
			String txtString=null;
			if (List.equals("")) {
				txtString="NULL";
			}else {
				txtString=List;
			}
			sql+=txtString+", ";
				
		}
		sql=sql.substring(0,sql.length()-2)+")";
		log.info(sql);
		
		
		try {
			@Cleanup Connection conn = DriverManager.getConnection(jdbcUrl2, dbUser, dbPass);
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	//지우기
	public void claearTex(){
		tef1.clear();
		tef2.clear();
		tef3.clear();
		tef4.clear();
		tef5.clear();
		tef6.clear();
		tef7.clear();
		tef8.clear();
		tef9.clear();
		tef10.clear();
		tef11.clear();
	}
	
	public  List<String> Label(){
		List<String> list=new ArrayList<>();
		
		list.add(Lab1.getText());
		list.add(Lab2.getText());
		list.add(Lab3.getText());
		list.add(Lab4.getText());
		list.add(Lab5.getText());
		list.add(Lab6.getText());
		list.add(Lab7.getText());
		list.add(Lab8.getText());
		list.add(Lab9.getText());
		list.add(Lab10.getText());
		list.add(Lab11.getText());
		
		return list;
	}
	
	//텍스트 필드
	public List<String> TextField() {
		List<String> list=new ArrayList<>();
		
		list.add(tef1.getText()); 									// 사원번호
		list.add("'" + tef2.getText() + "'"); 						// 성
		list.add("'" + tef3.getText() + "'"); 						// 이름
		list.add("'" + tef4.getText() + "'"); 						// 이메일
		list.add("'" + tef5.getText() + "'"); 						// 전화번호
		list.add("TO_DATE('" + tef6.getText() + "', 'yyyy-mm-dd')"); // 입사일
		list.add("'" + tef7.getText() + "'"); 						// 업무아이디
		list.add(tef8.getText()); 									// 급여
		list.add(tef9.getText()); 									// 수수료
		list.add(tef10.getText()); 									// 직속상사의 사원번호
		list.add(tef11.getText()); 									// 소속 부서번호
		
		return list;
	}

}

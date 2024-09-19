package org.zerock.myapp.test;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor
public class regController implements Initializable {
	
	
	@FXML private TextField textField;
	@FXML private Button btnInsert;
	
	static String driver = "oracle.jdbc.OracleDriver";	
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";
	static String dbUser = "hr";
	static String dbPass = "oracle";
	
	String sql = "INSERT INTO departments (DEPARTMENT_ID, DEPARTMENT_NAME) VALUES(280,'인사처')";
	
	public regController() {
		log.trace("Default Controller invoked.");
		
		log.info("\t1-1. textField: {}", this.textField);
		log.info("\t1-2. btnConfirm: {}", this.btnInsert);
	} // Default Controller
	
	
	public void initialize(URL location, ResourceBundle resources) {
		log.trace("initialize({}, {}) invoked.", location, resources);
		
		
		log.info("\t2-1. textField: {}", this.textField);
		log.info("\t2-2. btnConfirm: {}", this.btnInsert);

	} // initialize

	public void btn1(ActionEvent e) throws SQLException {
		
//		 데이터베이스 연결
		Connection conn = DriverManager.getConnection(jdbcURL1, dbUser, dbPass);
////		 저장
//		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		
		
		// CONFIRMATION은 어떤조취를 취할때 확인요청을 하는 열거값
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("등록 확인"); 
		alert.setHeaderText("등록하겠습니까?"); 
		alert.setContentText("선택:"); 
		
		// 버튼옵션 추가
		ButtonType buttonTypeYes  = new ButtonType("예");
		ButtonType ButtonTypeno = new ButtonType("아니오");
		
		// 예, 아니오 버튼을 alert에 추가
		alert.getButtonTypes().setAll(buttonTypeYes, ButtonTypeno);
		
		// 사용자 선택을 기다림
		var response = alert.showAndWait();
		
		if(response.get() == buttonTypeYes) {
			
			log.info("'예'를 선택했습니다.");
			// 예 로직
		} else {
			
			log.info("'아니오'를 선택했습니다.");
			// 아니오 로직
		} // if-else
	} // btn1
	
	
	
	public void handleOnAction(ActionEvent e) {
		log.trace("handleOnAction({}) invoked.", e);
		
		// 텍스트 필드 컨트롤에 사용자가 입력한 문자열얻어서 출력
		CharSequence str = this.textField.getCharacters();
		log.info("\t+ str: {}", str);
		
		
		try {
			// 데이터베이스 연결, 쿼리 실행
			Connection conn = DriverManager.getConnection(jdbcURL1, dbUser, dbPass);
			Statement stmt = conn.prepareStatement("INSERT INTO 테이블명(컬럼명) VALUES");
		} catch(Exception ex) {
			
		} // try-catch
		
		
		
		// JavaFX 프로그램 종료
		Platform.exit();
	} // handleOnAction
	
	
	
} // end class

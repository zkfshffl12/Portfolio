package org.zerock.myapp.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class InsertController implements Initializable {
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private Button btn3;
	@FXML private Label label1;
	
	// 데이터베이스 연동을 위한 필수요소 4가지
	static String driver = "oracle.jdbc.OracleDriver";	
//	static final String jdbcUrl2 = "jdbc:oracle:thin:@localhost:1521/XEPDB1";	
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";					
	static String dbUser = "hr";	// 접속할 계정명
	static String dbPass = "oracle";	// 계정의 암호
	
	// 임의로 설정한 SQL 쿼리문 사원 테이블의 모든 정보를 가져오라
	// 이 SQL 쿼리문을 어떻게 만드느냐에 따라 데이터 추가든 삭제든 수정이든 검색이든 모두 할수 있습니다.
	String sql = "SELECT * FROM EMPLOYEES ";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 시작과 동시에 label1 에 sql을 입력해 둬라!
		label1.setText(sql);
	} // initialize
	
	
	public void btn1Method(ActionEvent e) {
		log.trace("라벨에 조건 추가");
		
		// SQL 조건문 추가 예시1 (급여가 10000원인 사원)
//		sql = sql + "WHERE SALARY = 10000";
		
		// SQL 조건문 추가 예시2 (사원번호가 150인 사원)
		sql = sql + "WHERE EMPLOYEE_ID = 150";
		
		label1.setText(sql);
	} // btn1Method
	
	public void btn2Method(ActionEvent e) {
		log.trace("라벨 초기화");
		
		sql = "SELECT * FROM EMPLOYEES ";
		label1.setText(sql);
	} // btn2Method
	
	public void btn3Method(ActionEvent e) throws SQLException {
		log.trace("데이터베이스 연동");
		
		// 데이터 베이스 연동(검색을 위해 ResultSet 객체 까지 만들었으나, 등록수정삭제에서는 ResultSet 객체는 필요없습니다)
		Connection conn = DriverManager.getConnection(jdbcURL1, dbUser, dbPass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// 시각적으로 콘솔창에 보여드리기 위해 이렇게 한거지 이런거 굳이 할 필요없음
		while(rs.next()) {
			int employeeId = rs.getInt("employee_id"); 
			String firstName = rs.getString("FIRST_NAME");
			String lastName = rs.getString("LAST_NAME");
			String email = rs.getString("EMAIL");
			String phoneNumber = rs.getString("PHONE_NUMBER");
			Date hireDate = rs.getDate("HIRE_DATE");
			String jobId = rs.getString("JOB_ID");
			double salary = rs.getDouble("SALARY"); 
			double commissionPct = rs.getDouble("COMMISSION_PCT"); 
			int managerId = rs.getInt("MANAGER_ID"); 
			int departmentId = rs.getInt("DEPARTMENT_ID"); 
			
			String employee = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s".formatted(
					employeeId, firstName, lastName,
					email, phoneNumber, hireDate, jobId,
					salary, commissionPct, managerId, departmentId)
					);
			
			log.info(employee);
		} // while
		
	} // btn3Method
	
} // end class

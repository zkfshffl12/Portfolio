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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor
public class regController implements Initializable {
	
	@FXML private Button btnInsert;			// 등록버튼
	@FXML private Button btnCancle;			// 등록버튼
	@FXML private TextField EmployeeNumber;	// 사원번호
	@FXML private TextField FirstName;		// 사원 성
	@FXML private TextField LastName;		// 사원 이름
	@FXML private TextField Email;			// 사원 이메일
	@FXML private TextField PhoneNumber;	// 사원 연락처
	@FXML private DatePicker HireDate;		// 고용일 
	@FXML private TextField JobId;			// 직무 ID
	@FXML private TextField Salary;			// 급여
	@FXML private TextField CommissionPct;	// 수수료비율
	@FXML private TextField ManagerId;		// 관리자 ID
	@FXML private TextField DepartmentId;	// 부서 ID

	
	static String driver = "oracle.jdbc.OracleDriver";	
	static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";
	static final String jdbcUrl2 = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	static String dbUser = "hr";
	static String dbPass = "oracle";
	
	String sql;
	
	public regController() {
		log.trace("Default Controller invoked.");
		log.info("\t1-2. btnConfirm: {}", this.btnInsert);
	} // Default Controller
	
	public void initialize(URL location, ResourceBundle resources) {
		log.trace("initialize({}, {}) invoked.", location, resources);
		log.info("\t2-2. btnConfirm: {}", this.btnInsert);
	} // initialize

	public void handleCancleAction(ActionEvent e) throws IOException {
		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		try {    
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Mainscene.fxml"));
			Parent root = loader.<AnchorPane>load();
			primaryStage.setScene(new Scene(root));
		} catch (IOException e1) {  
			throw new IOException(e1);
		} // try catch
	} // handleCancleAction
	
	@FXML
	private void handleInsertAcion() throws SQLException {
		
		String employeeNumber = EmployeeNumber.getText();
		log.info("EmployeeNumber: {}", employeeNumber);
		
		String firstName = "'" +FirstName.getText() + "'";
		log.info("FirstName: {}", firstName);
		
		String lastName = "'" +LastName.getText() + "'";
		log.info("LastName: {}", lastName);
		
		String email = "'" +Email.getText() + "'";
		log.info("Email: {}", email);
		
		String phoneNumber = "'" +PhoneNumber.getText() + "'";
		log.info("PhoneNumber: {}", phoneNumber);
		
		String hireDate = "to_date('"  + HireDate.getValue() + "', 'yyyy-mm-dd')";
		// to_date('', 'yyyy-mm-dd')
		log.info("HireDate: {}", hireDate);
		
		String jobId = "'" +JobId.getText()+ "'";
		log.info("JobId: {}", jobId);
		
		String salary = Salary.getText();
		log.info("Salary: {}", salary);
		
		String commissionPct = CommissionPct.getText();
		log.info("CommissionPct: {}", commissionPct);
		
		String managerId = ManagerId.getText();
		log.info("ManagerId: {}", managerId);
		
		String departmentId = DepartmentId.getText();
		log.info("DepartmentId: {}", departmentId);
		 
		sql = "INSERT INTO employees VALUES"
				+ "(" + employeeNumber + "," + firstName + "," + lastName + "," 
				+ email + "," + phoneNumber + "," + hireDate + "," + jobId + "," 
				+ salary + "," + commissionPct + "," 
				+managerId+ "," + departmentId + ")";
		
//		데이터베이스 연결
		Connection conn = DriverManager.getConnection(jdbcUrl2, dbUser, dbPass);
//		저장
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
			try {
			
			log.info("등록: {}", sql);
				
			pstmt.executeUpdate();// 정보등록
			
			Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
			successAlert.setTitle("등록 성공");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("등록이 완료되었습니다.");
	        successAlert.showAndWait();	
	        
		} catch(SQLException ex) {
			log.error("등록 오류: {}", ex.getMessage());
			
			Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
	        cancelAlert.setTitle("등록 실패");
	        cancelAlert.setHeaderText(null);
	        cancelAlert.setContentText("등록이 실패되었습니다.");
	        cancelAlert.showAndWait();
		}	// 자원해제
           pstmt.close();
           conn.close();
	} // try-catch
			
} // end class

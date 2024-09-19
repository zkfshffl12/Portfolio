package org.zerock.myapp.test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MainController implements Initializable {
    
    @FXML private Button btn1, btn2, btn3;		// <, 홈, >
    @FXML private Text text1, text2; 			// 직원 정보, 사원 이름, 기본 정보
    @FXML private Label label;
    @FXML private ImageView imageview;
    @FXML private Rectangle image;

    // 데이터베이스 연동을 위한 정보
    static String driver = "oracle.jdbc.OracleDriver";
    static final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/BUSAN";
    static String dbUser = "hr";
    static String dbPass = "oracle"; 

    String sql = "SELECT * FROM employees";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	loadEmployeeInfo();
    } // initialize

    // 직원 정보 로드 메소드
    private void loadEmployeeInfo() {
    	
    } // loadEmployeeInfo
} // end class

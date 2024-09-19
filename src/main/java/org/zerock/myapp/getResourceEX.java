package org.zerock.myapp;

import java.net.URL;

public class getResourceEX {
	
	URL url = this.getClass().getResource("TestScene.fxml");
	// this.getClass(): 이것은 현재 객체의 클래스를 나타내는 Class 객체를 반환합니다. 
	// this 는 현재 실행 중인 메서드가 속한 객체를 나타내므로, this.getClass()는 현재 메서드를 호출하는 객체의 클래스 정보를 얻습니다.
	
	public static void main(String... args) {
		getResourceEX gr = new getResourceEX();
		App ttt = new App();
		
		URL url1 = gr.getClass().getResource("TestScene.fxml");
		URL url2 = ttt.getClass().getResource("TestScene.fxml");
		System.out.println(url1); // URL = 주소 = 절대경로!!
		System.out.println(url2); // gr 이든 ttt 던 그저 getClass() 메소드를 사용하기 위한 발사대일 뿐이다.
		// 출력 : file:/C:/app/workspaces/eclipse/jse/jdbc/target/classes/org/zerock/myapp/test/TestScene.fxml
	}
	
	
}

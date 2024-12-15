package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MysqlEx2 {

	public static void main(String[] args) {
		// 데이터베이스에서 특정 값을 조회하는 코드를 작성
		
		//1. 데이터베이스와 자바를 연결할 때 에러가 발생할 수 있기 때문에 try~ catch를 작성한다.
		try {
			
			// 2. 데이터베이스와 자바와 연결하기
			// - 정보저장
			String url = "jdbc:mysql://localhost:3306/testdb";
			String user = "root";
			String password = "0000";
			
			// - 실제 연결하는 함수 호출
			Connection connect = DriverManager.getConnection(url, user, password);
			
			// - sql문장을 작성해야 함
			//# 전체 조회를 하는게 아니라 선택 조회를 하는 경우 sql에서
			// use testdb;
			// select * from member where id = 'qwer'; 하기
			// 여기서 'qwer'을 ?로 바꾸면 이 데이터는 변경이 된다는 의미를 뜻한다.
		    String sql = "select * from member where id = ?";
		    // 키보드로 입력받아서 id를 sql로 전송한다
		    Scanner sc = new Scanner(System.in);
		    System.out.print("검색할 아이디: ");
		    String idinput = sc.nextLine();
		    
		    // - sql문을 실행하기 위해서 sql 명령문이 들어있는객체를 생성해야 된다.
		    PreparedStatement pst = connect.prepareStatement(sql);
		    // 입력받은 id 변수를 pst 변수를 이용해서 데이터를 저장
		    // ? 에 데이터 저장하기 
		    pst.setString(1, idinput);
		    
		    
		    // - pst가 sql 실행하는 함수를 가지고 있음 
		    ResultSet result = pst.executeQuery();
		    
		    
		    // - 결과를 한 행씩 뽑아서 각각의 데이터를 출력하기
		    while(result.next()) {
				// 한행을 꺼내왔고 그 안에서 각각의 열에 접근하려면 열의 이름을 작성해야 함
				String id = result.getString("id");
				String pw = result.getString("ipw");
				
				System.out.println(id);
				System.out.println(pw);
				System.out.println();
			}
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

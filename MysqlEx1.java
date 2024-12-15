package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlEx1 {

	public static void main(String[] args) {
		// 메모리에 영구적으로 저장을 할 예정 
		// 데이터 베이스라는 mysql 프로그램이 따로 있고 java라는 프로그램이 따로 있음
		// 이 둘이 데이터를 주고 받고 함 이때 java는 print를 하겠다면 println이라는 것을 사용하여 출력하나
		// mysql에서 데이터 출력을 할때는 select문을 이용함 (각각의 호출 언어가 다름)
		// 두 언어가 호환이 안됨 (언어를 보내야 하는데 두 언어가 호환이 안됨) -> 연결하기 위해서 중간에 connecter을 이용함 
		// connecter가 sql언어로 바꾸어 보내주고, 이들이 조회한 언어를 java의 언어로 바꾸어 sql로 보내주어야 함 
		// 커넥터 설치도 해야 함(프로젝트마다 커넥터가 있으면 사용가능함)
		
		// 커넥터
		// - mysql 서버(데이터를 제공하는 컴퓨터)랑 테이터를 주고 받으려면 통로(port, 기본적으로 윈도우가 설정해준다, 3306)
		// - 프로그램이 연결이 되었는지 확인하기 위해서는 이를 먼저 확인해야 한다.
		// 그러기 위해서는 데이터 베이스랑 자바 이클립스와 먼저 연결을 해야한다.
		// 연결을 할 때에는 세가지 데이터가 필요하다.
		
		// jdbc = java database connecter 자바와 데이터베이스를 연결할 때 사용하는 코드
		// mysql 내가 사용하는 데이터베이스 언어
		// localhost 내 컴퓨터 안에 저장소
		// 3306 데이터베이스와 자바랑 데이터를 주고 받을 때의 통로
		// testdb 데이터베이스 프로젝트 이름 
		
		// url 변수는 데이터베이스 연결을 위한 주소이다.
		String url = "jdbc:mysql://localhost:3306/testdb";
		
		// 데이터베이스는 나 말고 다른 사람은 접근 못하게 
		// 로그인 하는 정보도 같이 줘야 한다.
		// root는 최고 관리자!
		String user = "root";
		String password = "0000";
		
		// getConnection()
		// 데이터베이스랑 자바랑 연결을 할 수 있도록 실행하는 함수
		
		// 연결을 할 때 정확한 데이터가 안들어오면 에러가 발생될 수 있다.
		// 예외처리 try{예외나 에러가 발생할 수 있는 코드를 넣어준다.}catch{try에서 발생한 에러나 예외를 잡아서 처리한다.}
		// - 비정상적으로 프로그램이 종료되지 않게 처리하는 방법
		
		// Unknown database 'testdb'
		// 자바에서 데이터베이스를 연결하려고 하는데 testdb라는 저장소가(프로젝트) 없다는 뜻
		// my sql에서의 주석은 #과 --이다
		// my sql내부에서 하는 법
		// # 데이터베이스 생성(프로젝트 생성)
		// # 실행 ctrl +enter (위에 메뉴에 번개표시)
		// create database testdb;
		//# 데이터베이스에서 데이터를 추가하는 방법
		//# 1.데이터베이스를 선택!
		//use database;
		//# 굵게 표시되어있다면 선택되었다는 의미 
		//# 2. 데이터베이스르 저장할 틀(class)
		//# 데이터 베이스를 표 형식으로 저장하는 것을 보고 우리는 그걸 테이블이라고 한다.
		//# 데이터 베이스에서 자료형
		//# varchar 문자열을 저장하는 타입
		//# (숫자) 100개의 공간을 만든다.
		// create table member(
		//  id	varchar(100),
		//  pw  varchar(100)
		//)
		//# 3. 데이터 추가
		//# insert into 테이블명  values(값들 입력)
		// insert into member(테이블명 작성) values("qwer","qwer")
		// SELECT * FROM testdb member;
		
		try {//만약에 try 중괄호 안에 에러가 발생하면 내가 이렇게 처리할께
		Connection connect = DriverManager.getConnection(url, user, password);
		System.out.println(connect);
		System.out.println("연결 되었습니다.");
		
		// 데이터베이스에서 데이터를 조회할 때는 select 사용
		// *데이터 전부 가져오기!
		// SELECT * FROM testdb member;
		// 조회순서
		// 1. 조회할 sql 명령문 작성
		String query = "SELECT * FROM member";
		
		// 2. sql 명령문을 보내기 위해서는 sql에서 사용하는 함수들을 먼저 생성해준다.
		// PreparedStatement 타입이 sql 명령문을 실행할 수 있는 함수들을 가지고 있다.
		PreparedStatement pst = connect.prepareStatement(query);
		
		// 3. sql 명령문이 실행 될 수 있도록 함수를 호출한다.
		// sql문을 실행해라.
		// pst가 가지고 있는 query 변수의 명령문을 실행하여 표 형식으로 데이터를 가져온다. 
		// 표형식으로 가져오면 한 행씩row(한 줄씩) 데이터를 꺼낼 수 있다.
		// 각각의 행에 접근할 수 있도록 열column의 이름을 작성한다.
		
		// sql에서 결과를 자바로 보낼 때 자동으로 객체로 저장시켜주는 타입을 ResultSet이라고 함 
		ResultSet result = pst.executeQuery();
		
		// result set이라는 객체타입으로 저장을 해주는 주솟값이 날라옴 
		System.out.println(result);
		
		// 반복을 이용해서 표형식에서 한줄씩(row)한 행씩 꺼내온다
		// next함수를 이용해서 꺼내올 한줄, 한행이 있으면 true를 반환하도록 반복문 작성
		// 만일 꺼내올 한줄이 없으면 false를 반환
		
		// sql에서 데이터를 가지고 올 때 자바 객체로 자동으로 생성될 수 있도록 하는 타입 
		// sql -> java
		// java -> sql
		while(result.next()) {
			// 한행을 꺼내왔고 그 안에서 각각의 열에 접근하려면 열의 이름을 작성해야 함
			String id = result.getString("id");
			String pw = result.getString("pw");
			
			System.out.println(id);
			System.out.println(pw);
			System.out.println();
		}
		
		
		
		}catch(Exception e) {//에러가 발생하면 catch 중괄호에서 처리하는 코드를 작성해주면 됨
			System.out.println("연결 정보가 잘 못 되었습니다.");
			// 어디서 잘못된 건지 에러코드 확인하는 법
			e.printStackTrace();
		}
		
		
		

	}

}


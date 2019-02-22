package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/new_schema", "root", "123456");
			
		} catch (Exception e) {
			MainController.callAlert("���� ���� : DB ���ῡ �����Ͽ����ϴ�.\n���˹ٶ� : " + e.getMessage());
			e.printStackTrace();             
			return null;
		}
		return con;
	}
}

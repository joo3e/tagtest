package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Product;
import Model.User;
import Model.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DATA {
	
	public static ArrayList<User> dbArrList1 = new ArrayList<>();
	public static ArrayList<User> dbArrList4 = new ArrayList<>();
	public static ArrayList<Product> dbArrList2 = new ArrayList<>();
	public static ArrayList<String> dbArrList3 = new ArrayList<>();
	public static ArrayList<UserInfo> dbArrList5 = new ArrayList<>();
	public static int c;
	
	public static int insertUserData(User user) {
		StringBuffer insertUser = new StringBuffer();
		insertUser.append("insert into usertbl ");
		insertUser.append("(userID,name,birthYear,addr,mobile1,mobile2,height,mdate) ");
		insertUser.append("values ");
		insertUser.append("(?,?,?,?,?,?,?,?) ");
		Connection con = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUser.toString());
			psmt.setString(1, user.getUserid());
			psmt.setString(2, user.getName());
			psmt.setString(3, user.getBirthYear());
			psmt.setString(4, user.getAddr());
			psmt.setString(5, user.getMobile1());
			psmt.setString(6, user.getMobile2());
			psmt.setString(7, user.getHeight());
			psmt.setString(8, user.getMdate());
		
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ���� ���˹ٶ�");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			//�ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}

		}

		return count;
	}

	public static int insertbuyTblData(Product product) {
		StringBuffer insertUser = new StringBuffer();
		insertUser.append("insert into buyTbl ");
		insertUser.append("(num,userID,prodName,groupName,price,amount) ");
		insertUser.append("values ");
		insertUser.append("(?,?,?,?,?,?) ");
		Connection con = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUser.toString());
			psmt.setString(1, null);
			psmt.setString(2, product.getUserID());
			psmt.setString(3, product.getProdName());
			psmt.setString(4, product.getGroupName());
			psmt.setString(5, product.getPrice());
			psmt.setString(6, product.getAmount());
		
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� ������ ���� ���˹ٶ�");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}
		}
		return count;
	}

	public static ArrayList<User> getuserTotalData() {
		String selectuser = "select * from usertbl";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				dbArrList1.add(user);
			}

		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}

		}
		return dbArrList1;
	}

	public static ArrayList<Product> getproductTotalData() {
		String selectbuyTbl = "select * from buyTbl";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectbuyTbl);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				dbArrList2.add(product);
			}

		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}

		}
		return dbArrList2;
	}
	
	public static int updateUserData(User user) {
		int count = 0;
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update usertbl set ");
		updateStudent.append("name=?,birthYear=?,addr=?,mobile1=?,mobile2=?,height=?,mdate=? where userID=? ");

		
		Connection con = null;
		
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());

		
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getBirthYear());
			psmt.setString(3, user.getAddr());
			psmt.setString(4, user.getMobile1());
			psmt.setString(5, user.getMobile2());
			psmt.setString(6, user.getHeight());
			psmt.setString(7, user.getMdate());
			psmt.setString(8, user.getUserid());
			
			
			count = psmt.executeUpdate();

			if (count == 0) {
				MainController.callAlert("UPDATE ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("UPDATE ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
			
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : UPDATE psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return count;
	}
	public static int updatebuyData(Product product) {
		int count = 0;
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update buytbl set ");
		updateStudent.append("userID=?,prodName=?,groupName=?,price=?,amount=? where num=? ");
		
		
		Connection con = null;
		
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());
			
			
			psmt.setString(1, product.getUserID());
			psmt.setString(2, product.getProdName());
			psmt.setString(3, product.getGroupName());
			psmt.setString(4, product.getPrice());
			psmt.setString(5, product.getAmount());
			psmt.setString(6, product.getNum().toString());
			
			
			
			count = psmt.executeUpdate();
			
			if (count == 0) {
				MainController.callAlert("UPDATE ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("UPDATE ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : UPDATE psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
				
			}
		}
		return count;
	}
	public static ArrayList<String> getuserid() {
		String selectuser = "select userID from usertbl ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				
				String string = rs.getString(1);
				dbArrList3.add(string);
				
			}

		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}

		}
		return dbArrList3;
	}
	public static int deleteuserData(String userid) {
		String deleteuser = "delete from usertbl where userID = ? ";

		// 3.2 DELETE DataBase Connection
		Connection con = null;
		// 3.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		//3.4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		int count = 0;
		
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteuser);
			//ù��° ����ǥ �ڸ� -> no ��ġ �����ִ� �۾� 
			psmt.setString(1, userid);
	
			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
			count = psmt.executeUpdate();

			if (count == 0) {
				MainController.callAlert("DELETE ������ ���� ���� : DELETE ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
			}
		}
		
		return count;
	}
	public static int deletebuyData(String num) {
		String deleteuser = "delete from buyTbl where num = ? ";
		
		// 3.2 DELETE DataBase Connection
		Connection con = null;
		// 3.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		//3.4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		int count = 0;
		
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteuser);
			//ù��° ����ǥ �ڸ� -> no ��ġ �����ִ� �۾� 
			psmt.setString(1, num);
			
			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
			count = psmt.executeUpdate();
			
			if (count == 0) {
				MainController.callAlert("DELETE ������ ���� ���� : DELETE ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
			}
		}
		
		return count;
	}
	public static ArrayList<User> getselecteddate(String a, String b) {
		
		 
		String getuser="select * from usertbl where mdate between ? and ? ";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		//2.4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(getuser);
			psmt.setString(1, a);
			psmt.setString(2, b);
	
			// 2.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();

			if (rs == null) {
				MainController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while(rs.next()) {
				User user =  new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				dbArrList4.add(user);
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return dbArrList4;
	}
	public static  ArrayList<UserInfo> getstabledata(String a) {
		String selectuser = "SELECT name, concat(mobile1,mobile2), prodName, amount, price*amount FROM buytbl INNER JOIN usertbl ON buytbl.UserID = usertbl.UserID where buytbl.UserID = ? ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			psmt.setString(1, a);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				UserInfo userinfo = new UserInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				dbArrList5.add(userinfo);
			}

		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}

		}
		return dbArrList5;
	}
	public static int getselectedheight(int a, int b) {
		
		 
		String getuser="select count(*) from usertbl where birthYear between ? and ? and height between ? and ? ";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		//2.4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(getuser);
			psmt.setString(1, String.valueOf(1950+a*10));
			psmt.setString(2, String.valueOf(1959+a*10));
			psmt.setString(3, String.valueOf(160+b*10));
			psmt.setString(4, String.valueOf(169+b*10));
			
	
			// 2.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();

			if (rs == null) {
				MainController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return 0;
			}
			while(rs.next()) {
			 c = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return c;
	}
}


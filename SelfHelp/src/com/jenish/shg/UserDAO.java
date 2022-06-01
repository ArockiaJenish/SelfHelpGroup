package com.jenish.shg;

import java.sql.*;

public class UserDAO {
	static Connection con = null;
	
static {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","selfhelpgroup","shgdata");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end static block
	
	
	public static Connection getCon() {
		return con;
	}
}

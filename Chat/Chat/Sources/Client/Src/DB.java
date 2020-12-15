package Chat.Sources.Client.Src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.awt.Component;
import java.io.PrintWriter;
import java.lang.*;
/* JDBC */
public class DB {
	public static Hashtable<Integer, String> menu_set =new Hashtable<Integer, String>();
	public static int menu_cnt=0;
	
	boolean DB(int a_time, int m_time, int rest) {
		/*Variable*/
	    int cnt=0;
	    int food_time;
	     
	    /*Connection to DB(MYSQL)*/
		Connection con = null;
		try
		{
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    String url="jdbc:mysql://localhost:3306/abob?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
		    String user = "root", passwd = "";
		    con = DriverManager.getConnection(url, user, passwd);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/* Get Data(menu) which edible time more than 15 min. */
		try {
			String psql = "select str_name, str_id, name, cost, cook_time from store natural join menu where ?-900>=(cook_time+?) and str_id=?";
			
			pstmt= con.prepareStatement(psql);
			pstmt.setInt(1, a_time*60); 
			pstmt.setInt(2, m_time*60); 
			pstmt.setInt(3, rest); 
			rs = pstmt.executeQuery();

     		
			while(rs.next()) {
				String str_name = rs.getString(1);
				if(rs.wasNull()) str_name = "null";
				String str_id = rs.getString(2);
				if(rs.wasNull()) str_id = "null";
				String name = rs.getString(3);
				if(rs.wasNull()) name = "null";
				String cost  = rs.getString(4);
				if(rs.wasNull()) cost = "null";
				String cook_time = rs.getString(5);
				if(rs.wasNull()) cook_time = "null";
				

				food_time = a_time - (m_time + Integer.parseInt(cook_time)/60);
				
				/* Save data to GUI.menu_list array */
				GUI.menu_list[menu_cnt][0] = Integer.toString(menu_cnt+1);
				GUI.menu_list[menu_cnt][1] = name;
				GUI.menu_list[menu_cnt][2] = str_name;
				GUI.menu_list[menu_cnt][3] = cost;
				GUI.menu_list[menu_cnt][4] = Integer.toString(food_time);
				GUI.menu_list[menu_cnt][5] = str_id;			
				cnt++;
				menu_cnt += 1;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			if(con != null && !con.isClosed()) con.close();
			if(rs != null && !rs.isClosed()) rs.close();			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		/*If the number of data(instance) which get from database more than 1, then return true.*/
		if(cnt>=1)return true;
		else return false;
	}
}
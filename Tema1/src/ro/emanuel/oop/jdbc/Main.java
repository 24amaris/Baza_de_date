package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
	
	public static void main (String[] args) throws SQLException {
		
		Properties connectionProps = new Properties();
		connectionProps.put("user","root");
		connectionProps.put("password","");
	
		//open connection
		Connection conn= DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Tema1", 
				connectionProps);
	
		//open statement
		Statement stmt=conn.createStatement();
		
		
		//insert
		String iType = "Platinum ring";
		int iCode = 9784;
		int iPrice  = 364;
		
		String sqlInsert= "insert into Bijuterii(type, code, price)values (? ,?, ?)";
		PreparedStatement ps=conn.prepareStatement(sqlInsert);
		
		ps.setString(1, iType);
		ps.setInt(2, iCode);
		ps.setInt(3, iPrice);
		
		int rowsAff= ps.executeUpdate();
		System.out.println(rowsAff);
		
		
		//UPDATE
		String update="UPDATE Bijuterii SET price = ? WHERE code = ? ";
		PreparedStatement psu= conn.prepareStatement(update);
		psu.setInt(1, iPrice);
		psu.setInt(2, iCode);
		psu.executeUpdate();
		
		//DELETE
		String deleteValue="DELETE FROM Bijuterii WHERE price =?";
		PreparedStatement ps3 = conn.prepareStatement(deleteValue);
		ps3.setInt(1, 364);
		int result=ps3.executeUpdate();
		System.out.println(result);
		
		
		//get result
		ResultSet rs =stmt.executeQuery("select * from Bijuterii");
				
		//map results
		while (rs.next()) {
			String bijuteriiType =rs. getString("type");
			String bijuteriiCode =rs. getString("code");
			String bijuteriiPrice =rs. getString("price");
					
			System.out.println( "|" + bijuteriiType + "|" + bijuteriiCode + "|" + bijuteriiPrice);
					
			}
		conn.close();		
	}
}

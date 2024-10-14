package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main1 {
	
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
		String iModel = "Shelf";
		String iMaterial = "metal";
		int iPrice  = 300;
		
		String sqlInsert= "insert into Mobilier(model, material, price)values (? ,?, ?)";
		PreparedStatement ps=conn.prepareStatement(sqlInsert);
		
		ps.setString(1, iModel);
		ps.setString(2, iMaterial);
		ps.setInt(3, iPrice);
		
		int rowsAff= ps.executeUpdate();
		System.out.println(rowsAff);
		
		
		//UPDATE
		String update="UPDATE Mobilier SET price = ? WHERE material = ? ";
		PreparedStatement psu= conn.prepareStatement(update);
		psu.setInt(1, 200);
		psu.setString(2, "metal");
		psu.executeUpdate();
		
		//DELETE
		String deleteValue="DELETE FROM Mobilier WHERE price =?";
		PreparedStatement ps3 = conn.prepareStatement(deleteValue);
		ps3.setInt(1, 200);
		int result=ps3.executeUpdate();
		System.out.println(result);
		
		
		//get result
		ResultSet rs =stmt.executeQuery("select * from Mobilier");
				
		//map results
		while (rs.next()) {
			String mobilierModel =rs. getString("model");
			String mobilierMaterial =rs. getString("material");
			String mobilierPrice =rs. getString("price");
					
			System.out.println( "|" + mobilierModel + "|" + mobilierMaterial + "|" + mobilierPrice);
					
			}
		conn.close();		
	}
}

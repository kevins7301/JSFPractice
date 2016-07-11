package at.jsfpractice.jsf22.testDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.component.datalist.DataList;

public class testDAO 
{
	public static boolean validate(String accuntID, String passWord) 
	{
		Connection con = null;
		PreparedStatement ps = null;

		try 
		{
			con = DataBaseConnect.getConnection();
			ps = con.prepareStatement("Select ACCOUNTID, PASSWORD from LOGINUSER where ACCOUNTID = ? and PASSWORD = ?");
			ps.setString(1, accuntID);
			ps.setString(2, passWord);

			System.err.println("validate===");
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				// result found, means valid inputs
				return true;
			}
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} 
		
		return false;
	}
	
	public static void test()
	{
		System.err.println("test===");
		Connection con = null;
		PreparedStatement ps = null;
		
		try 
		{
			con = DataBaseConnect.getConnection();
			System.err.println("con=isClosed=="+con.isClosed());
			
			ps = con.prepareStatement("SELECT * FROM PERSON");
			ResultSet rs = ps.executeQuery();
			
			ps = con.prepareStatement("INSERT INTO PERSON VALUES(4, 'person4', 'person4@gamil.com');");
			int count = ps.executeUpdate();
			
			ps = con.prepareStatement("SELECT * FROM PERSON");
			rs = ps.executeQuery();
			while (rs.next()) 
			{
//				HashMap dataMap = new HashMap();
//				dataMap.put("ID", rs.getInt("ID"));
//				dataMap.put("IMAGE", rs.getString("IMAGE"));
				// result found, means valid inputs
				
				System.err.println("==="+rs.getString("NAME"));
			}
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
		} 
	}
	
	public static ArrayList getDataMap(String[] key, String sqlStr)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList dataList = new ArrayList();
		
		try 
		{
			con = DataBaseConnect.getConnection();
			ps = con.prepareStatement(sqlStr);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				HashMap dataMap = new HashMap();
				
				System.err.println("==NAME=="+rs.getBigDecimal("NAME"));
				for (String keyStr : key)
				{
					
					System.err.println("==keyStr=="+keyStr);
					if (rs.getBigDecimal(keyStr) != null)
					{
						dataMap.put(keyStr, rs.getBigDecimal(keyStr));
					}
					else 
					{
						dataMap.put(keyStr, rs.getString(keyStr));
					}
				}
				// result found, means valid inputs
				
				dataList.add(dataMap);
			}
			
			return dataList;
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
			return null;
		} 
		
	}
	
	public static boolean insertData(String sqlStr)
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		try 
		{
			con = DataBaseConnect.getConnection();
			
			ps = con.prepareStatement(sqlStr);
			int count = ps.executeUpdate();
			
			
			System.err.println("==count="+count);
//			while (rs.next()) 
//			{
//				System.err.println("==="+rs.getString("NAME"));
//			}
			
			return true;
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} 
		
		
		
		
	}
	
	
	
	public static ArrayList getPersonData()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList dataList = new ArrayList();
		
		try 
		{
			con = DataBaseConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM PERSON");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				HashMap dataMap = new HashMap();
				dataMap.put("ID", rs.getInt("ID"));
				dataMap.put("NAME", rs.getString("NAME"));
				dataMap.put("EMAIL", rs.getString("EMAIL"));
				// result found, means valid inputs
				
				dataList.add(dataMap);
			}
			
			return dataList;
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
			return null;
		} 
	}
	
	
    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
    	
//    	Base64.en
    	
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		testDAO test = new testDAO();
		test.test();
		// test.disConnect();
	}
}

package at.jsfpractice.jsf22.testDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
//		finally 
//		{
//			DataBaseConnect.close(con);
//		}
		
		return false;
	}
	
//	public static ArrayList getImageData()
//	{
//		
//		
//		
//	}
	
	public static void upload(ArrayList<String> dataList/*ArrayList<FileInputStream> dataFinLsit, ArrayList dataLengthList*/)
	{
		Connection con = null;
		PreparedStatement ps = null;
		
//		for (int i = 0; i < dataList.size(); i++)
//		{
//		for (String inputfile : dataList)
//		{
			
			
//			FileInputStream fin = (FileInputStream) dataFinLsit.get(i);
//			long datalength = (Long) dataLengthList.get(i);
			
			
			
        		// Reading a Image file from file system
            
                // upload database
//                finList.add(imageInFile);
//                finLengthList.add(inputfile.length());
			File file = null;
			FileInputStream imageInFile = null;
			try 
			{
				file = new File("D:\\image\\01.jpg");
				imageInFile = new FileInputStream(file);
				
				con = DataBaseConnect.getConnection();
				con.setAutoCommit(false);
				PreparedStatement stmt = con.prepareStatement("INSERT INTO IMAGE(ID, NAME, IMAGE) VALUES('?', '?', '?')");
//				System.err.println("stmt=="+stmt);
//				stmt.setBinaryStream(1, imageInFile, file.length());
				stmt.setInt(1, 1);
				stmt.setString(2, "01.jpg");
				stmt.setBlob(3, imageInFile);
				
//				stmt.executeUpdate(); 
//				stmt.clearParameters(); 
				stmt.executeQuery();
	            con.commit();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally 
			{
	            if (imageInFile != null) 
	            {
	            	try 
	            	{
	            		imageInFile.close();
						
//						if (con != null && !con.isClosed()) 
//						{
//						    con.close();
//						}
					} 
	            	catch (IOException e) 
	            	{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//	            	catch (SQLException e) 
//	            	{
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	            }
	        }
//		}
	}
	
	public static void test()
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		try 
		{
			con = DataBaseConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM IMAGE");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				HashMap dataMap = new HashMap();
				dataMap.put("ID", rs.getInt("ID"));
				dataMap.put("IMAGE", rs.getString("IMAGE"));
				// result found, means valid inputs
				
				System.err.println("==="+rs.getString("IMAGE"));
			}
		} 
		catch (SQLException ex) 
		{
			System.out.println("Login error -->" + ex.getMessage());
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
	
	
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		testDAO test = new testDAO();

		// test.disConnect();
	}
}

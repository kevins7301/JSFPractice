package at.jsfpractice.jsf22.testDB;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataBaseConnect 
{
	private static DataSource ds;
	
	private static String driver = "org.h2.Driver";
	private static String url1 = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:scripts/schema.sql'";
	private static String url2 = "jdbc:h2:mem:test";
	private static String userid = "sa";
	private static String passwd = "sa";
	private static Connection con;
	
	public static void initDataSource() 
	{
//		BasicDataSource basicDS = null;
//		
//		if (ds == null)
//		{
//			basicDS = new BasicDataSource();
//			
//			basicDS.setDriverClassName("org.h2.Driver");
//			basicDS.setUrl("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:scripts/schema.sql'"/*;LOCK_MODE=1;MVCC=TRUE;DB_CLOSE_DELAY=-1;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=3;TRACE_LEVEL_FIle=4"*/);
//			basicDS.setUsername("sa");
//			basicDS.setPassword("sa");
//			ds = basicDS;
//		} 
		
//		Connection con = null;

		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url1, userid, passwd);

		} 
		catch (ClassNotFoundException e) 
		{
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} 
		catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
//		finally 
//		{
//			if (con != null) 
//			{
//				try
//				{
//					con.close();
//				} 
//				catch (Exception e) 
//				{
//					e.printStackTrace(System.err);
//				}
//			}
//		}
	}
	
	
	
	public static Connection getConnection()
	{
//		Connection con = null;
//		
//		try
//		{
//			if  (ds == null)
//			{
//				initDataSource();
//			}
			
//			Connection con = ds.getConnection();
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url2, userid, passwd);
//			
//			System.err.println("getConnection==con222="+con.isClosed());
//			
//			
//			return con;
//			
//		}
//		catch (Exception e)
//		{
//			System.err.println("conException==="+e);
//			return null;
//		}
		
		if (con == null)
		{
			initDataSource();
		}
		
		return con;
	}
	
	public static void close(Connection con)
	{
		try
		{
			con.close();
			System.err.println("conclose===");
		}
		catch (Exception e)
		{
		
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		DataBaseConnect test = new DataBaseConnect();

		Connection con = test.getConnection();
		
		
//		test.close(con);
	}
}
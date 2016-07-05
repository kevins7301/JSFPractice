package at.jsfpractice.jsf22.testDB;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataBaseConnect 
{
	private static DataSource ds;
	
	public static void initDataSource() 
	{
		BasicDataSource basicDS = null;
		if (ds == null)
		{
			basicDS = new BasicDataSource();
			
			basicDS.setDriverClassName("org.h2.Driver");
			basicDS.setUrl("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:scripts/schema.sql'"/*;LOCK_MODE=1;MVCC=TRUE;DB_CLOSE_DELAY=-1;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=3;TRACE_LEVEL_FIle=4"*/);
			basicDS.setUsername("sa");
			basicDS.setPassword("sa");
			ds = basicDS;
		} 
	}
	
	
	
	public static Connection getConnection()
	{
		try
		{
			initDataSource();
			Connection con = ds.getConnection();

			return con;
			
		}
		catch (Exception e)
		{
			System.err.println("conException==="+e);
			return null;
		}
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
		test.close(con);
	}
}
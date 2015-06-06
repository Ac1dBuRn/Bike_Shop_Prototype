package database.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil
{
	public static Connection getDatabaseConnection()
	{
		Connection conn = null;
		
		try
		{
			Class.forName( "org.sqlite.JDBC" );
		
			conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "\\BicycleShopDB.db3");
			conn.setAutoCommit( false );
		}
		catch(Exception ex)
		{
			throw new RuntimeException( ex );
		}
		
		return conn;
	}
}

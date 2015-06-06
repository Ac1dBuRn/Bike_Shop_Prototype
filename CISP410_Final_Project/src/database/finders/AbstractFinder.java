package database.finders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.util.DatabaseUtil;

public abstract class AbstractFinder
{
	public ResultSet findEntity( String query ) throws SQLException
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{	
			stmt = conn.createStatement();
			rs = stmt.executeQuery( query );
		}
		catch ( SQLException ex )
		{
			ex.printStackTrace();
			
			conn.rollback();
		}
		finally
		{
			if ( stmt != null )
			{
				stmt.close();
			}
		}
		
		return rs;
	}
}

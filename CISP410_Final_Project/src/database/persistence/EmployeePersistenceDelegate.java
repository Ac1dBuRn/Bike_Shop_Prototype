package database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import database.util.DatabaseUtil;
import entities.Employee;

public class EmployeePersistenceDelegate
{
	public void persistEmployee( Employee emp )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( emp.employeeId != null )
			{
				stmt = createUpdateEmployeeStatement( emp, conn );
			}
			else
			{
				stmt = createInsertEmployeeStatement( emp, conn );
				stmt.execute();
			}
			
			conn.commit();
		}
		catch ( SQLException ex )
		{
			ex.printStackTrace();
			try { conn.rollback(); } 
			catch ( SQLException exx ){ exx.printStackTrace(); }
		}
		finally
		{
			try
			{
				if ( stmt != null ) { stmt.close(); }
				conn.close();
			}
			catch( SQLException ex ) { throw new RuntimeException( ex ); }
		}
	}

	private PreparedStatement createInsertEmployeeStatement( Employee emp, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into employee "
				+ "(first_name, last_name, ssn, hire_date, termination_date) "
				+ " values(?, ?, ?, ?, ?)" );
		
		stmt.setString( 1, emp.firstName );
		stmt.setString( 2, emp.lastName );
		stmt.setString( 3, emp.ssn );
		stmt.setDate( 4, new java.sql.Date( emp.hireDate.getTime() ) );
		stmt.setDate( 5, emp.terminationDate == null ? null : new java.sql.Date( emp.terminationDate.getTime() ) );
		
		return stmt;
	}

	private PreparedStatement createUpdateEmployeeStatement( Employee emp, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "update employee "
				+ "set first_name = ?, last_name = ?, ssn = ?, hire_date = ?, termination_date = ? "
				+ "where employee_id = ?" );
		
		stmt.setString( 1, emp.firstName );
		stmt.setString( 2, emp.lastName );
		stmt.setString( 3, emp.ssn );
		stmt.setDate( 4, new java.sql.Date( emp.hireDate.getTime() ) );
		stmt.setDate( 5, emp.terminationDate == null ? null : new java.sql.Date( emp.terminationDate.getTime() ) );
		stmt.setLong( 6, emp.employeeId );
		
		return stmt;
	}
}

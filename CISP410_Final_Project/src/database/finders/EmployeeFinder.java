package database.finders;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Employee;

public class EmployeeFinder extends AbstractFinder
{
	public Employee findByOid( Long oid )
	{
		Employee employee = null;
		
		try
		{
			ResultSet rs = findEntity( 
					"select first_name, last_name, ssn, hire_date, termination_date "
					+ " from employee where employee_id = " + oid );
			
			if( rs.next() )
			{
				employee = new Employee();
				employee.employeeId = oid;
				employee.hireDate = rs.getDate( "hire_date" );
				employee.firstName = rs.getString( "first_name" );
				employee.lastName = rs.getString( "last_name" );
				employee.ssn = rs.getString( "ssn" );
				employee.terminationDate = rs.getDate( "termination_date" );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return employee;
	}
}

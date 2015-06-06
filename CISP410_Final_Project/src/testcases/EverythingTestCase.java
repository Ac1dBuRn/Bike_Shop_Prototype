package testcases;

import java.util.Date;

import database.persistence.EmployeePersistenceDelegate;
import entities.Employee;

public class EverythingTestCase
{
	public static void main(String[] args)
	{
		// create all persist all.
		// insert/find dates with: mm/dd/yyyy
		// setString instead of setDate try it....
		
		Employee emp = new Employee();
		emp.firstName = "ryan";
		emp.lastName = "sova";
		emp.ssn = "1234567489";
		emp.hireDate = new Date();
		emp.terminationDate = new Date();
		
		new EmployeePersistenceDelegate().persistEmployee( emp );
	}
}

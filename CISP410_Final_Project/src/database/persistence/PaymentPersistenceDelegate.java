package database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.util.DatabaseUtil;
import entities.Payment;

public class PaymentPersistenceDelegate
{
	/**
	 * Persists the Payment entity
	 * 
	 * @param payment
	 */
	public void persistPayment( Payment payment )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( payment.paymentId != null )
			{
				stmt = createUpdatePaymentStatement( payment, conn );
			}
			else
			{
				stmt = createInsertPaymentStatement( payment, conn );
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

	private PreparedStatement createInsertPaymentStatement( Payment payment, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into payment "
				+ "(payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date) "
				+ " values(?, ?, ?,)" );
		
		stmt.setString( 1, payment.paymentStatus );
		stmt.setDate( 2, new java.sql.Date( payment.paymentDate.getTime() ) );
		stmt.setDouble( 3, payment.taxPercentage );
		stmt.setDouble( 4, payment.cashPaymentAmt );
		stmt.setString( 5, payment.ccName );
		stmt.setString( 6, payment.ccNum );
		stmt.setString( 7, payment.ccType );
		stmt.setDate( 8, payment.ccExpirationDate == null ? null : new java.sql.Date( payment.ccExpirationDate.getTime() ) );
		
		return stmt;
	}

	private PreparedStatement createUpdatePaymentStatement( Payment payment, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into payment "
				+ "set payment_status = ?, payment_date = ?, tax_percentage = ?"
				+ ", cash_payment_amt = ?, cc_name = ?, cc_num = ?, cc_type = ?, cc_expiration_date = ? "
				+ "where payment_id = ?" );
		
		stmt.setString( 1, payment.paymentStatus );
		stmt.setDate( 2, new java.sql.Date( payment.paymentDate.getTime() ) );
		stmt.setDouble( 3, payment.taxPercentage );
		stmt.setDouble( 4, payment.cashPaymentAmt );
		stmt.setString( 5, payment.ccName );
		stmt.setString( 6, payment.ccNum );
		stmt.setString( 7, payment.ccType );
		stmt.setDate( 8, payment.ccExpirationDate == null ? null : new java.sql.Date( payment.ccExpirationDate.getTime() ) );
		stmt.setLong( 9, payment.paymentId );
		
		return stmt;
	}
}

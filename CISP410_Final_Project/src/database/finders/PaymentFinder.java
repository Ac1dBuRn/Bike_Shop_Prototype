package database.finders;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Payment;

public class PaymentFinder extends AbstractFinder
{
	public Payment findByOid( Long oid )
	{
		Payment payment = null;
		
		try
		{
			ResultSet rs = findEntity( 
					"select payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date "
					+ " from payment where payment_id = " + oid );
			
			if( rs.next() )
			{
				payment = new Payment();
				payment.cashPaymentAmt = rs.getDouble( "cash_payment_amt" );
				payment.ccExpirationDate = rs.getDate( "cc_expiration_date" );
				payment.ccName = rs.getString( "cc_name" );
				payment.ccNum = rs.getString( "cc_num" );
				payment.ccType = rs.getString( "cc_type" );
				payment.paymentDate = rs.getDate( "payment_date" );
				payment.paymentId = oid;
				payment.paymentStatus = rs.getString( "payment_status" );
				payment.taxPercentage = rs.getDouble( "tax_percentage" );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return payment;
	}
}

package database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.util.DatabaseUtil;
import entities.Order;
import entities.OrderLine;

public class OrderPersistenceDelegate
{
	/**
	 * Persists the Order with associated Payment and OrderLines
	 * 
	 * @param order
	 */
	public void persistOrder( Order order )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( order.payment != null )
			{
				new PaymentPersistenceDelegate().persistPayment( order.payment );
			}
			
			for( OrderLine ol : order.orderLines )
			{
				persistOrderLine( ol );
			}
			
			if( order.orderId != null )
			{
				stmt = createUpdateOrderStatement( order, conn );
			}
			else
			{
				stmt = createInsertOrderStatement( order, conn );
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
	
	private PreparedStatement createInsertOrderStatement( Order order, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into order "
				+ "(employee_id, payment_id) "
				+ " values(?, ?, ?)" );
		
		stmt.setLong( 1, order.employee == null ? null : order.employee.employeeId );
		stmt.setLong( 2, order.payment == null ? null : order.payment.paymentId );
		
		return stmt;
	}

	private PreparedStatement createUpdateOrderStatement( Order order, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "update order "
				+ " set employee_id = ?, payment_id = ? "
				+ " where order_id = ?" );
		
		stmt.setLong( 1, order.employee == null ? null : order.employee.employeeId );
		stmt.setLong( 2, order.payment == null ? null : order.payment.paymentId );
		stmt.setLong( 3, order.orderId );
		
		return stmt;
	}

	/**
	 * Persists the OrderLine entity
	 * 
	 * @param ol
	 */
	public void persistOrderLine( OrderLine ol )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( ol.orderLineId != null )
			{
				stmt = createUpdateOrderLineStatement( ol, conn );
			}
			else
			{
				stmt = createInsertOrderLineStatement( ol, conn );
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

	private PreparedStatement createUpdateOrderLineStatement( OrderLine ol, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "update order_line "
				+ "set order_id = ?, quantity = ?, product_id = ?, cost_per_product = ? "
				+ "where order_line_id = ?" );
		
		stmt.setLong( 1, ol.order.orderId );
		stmt.setInt( 2, ol.quantity );
		stmt.setLong( 3, ol.product.productId );
		stmt.setDouble( 4, ol.costPerProduct );
		stmt.setLong( 5, ol.orderLineId );
		
		return stmt;
	}

	private PreparedStatement createInsertOrderLineStatement( OrderLine ol, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into order_line "
				+ "(order_id, quantity, product_id, cost_per_product) "
				+ "values(?, ?, ?, ?)" );
		
		stmt.setLong( 1, ol.order.orderId );
		stmt.setInt( 2, ol.quantity );
		stmt.setLong( 3, ol.product.productId );
		stmt.setDouble( 4,  ol.costPerProduct );
		
		return stmt;
	}
}

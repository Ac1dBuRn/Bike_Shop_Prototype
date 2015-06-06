package database.finders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.OrderLine;

public class OrderFinder extends AbstractFinder
{
	public Order findByOid( Long oid )
	{
		Order order = null;
		
		try
		{
			ResultSet rs = findEntity( 
					"select order_id, employee_id, payment_id "
					+ " from order where order_id = " + oid );
			
			if( rs.next() )
			{
				order = new Order();
				order.employee = new EmployeeFinder().findByOid( rs.getLong( "employee_id" ) );
				order.orderId = rs.getLong( "order_id" );
				order.payment = new PaymentFinder().findByOid( rs.getLong( "payment_id" ) );
				order.orderLines.addAll( findOrderLinesByOrder( order ) );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return order;
	}

	public List<OrderLine> findOrderLinesByOrder( Order order )
	{
		List<OrderLine> orders = new ArrayList<OrderLine>();
		
		try
		{
			ResultSet rs = findEntity( 
					"select order_line_id, quantity, product_id, cost_per_product "
					+ " from order_line where order_id = " + order.orderId );
			
			while( rs.next() )
			{
				OrderLine ol = new OrderLine();
				ol.order = order;
				ol.orderLineId = rs.getLong( "order_line_id" );
				ol.product = new ProductFinder().findByOid( rs.getLong( "product_id" ) );
				ol.quantity = rs.getInt( "quantity" );
				ol.costPerProduct = rs.getDouble( "cost_per_product" );
				
				orders.add( ol );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return orders;
	}
}

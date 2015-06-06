package database.finders;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Product;
import entities.ProductCategory;

public class ProductFinder extends AbstractFinder
{
	public Product findByOid( Long oid )
	{
		Product product = null;
		
		try
		{
			ResultSet rs = findEntity( 
					"select name, price, weight, description, product_category_id "
					+ " from product where product_id = " + oid );
			
			if( rs.next() )
			{
				product = new Product();
				product.description = rs.getString( "description" );
				product.name = rs.getString( "name" );
				product.price = rs.getDouble( "price" );
				product.productId = oid;
				product.weight = rs.getDouble( "weight" );
				product.category = findProductCategoryByProduct( product );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return product;
	}

	public ProductCategory findProductCategoryByProduct( Product product )
	{
		ProductCategory pc = null;
		
		try
		{
			ResultSet rs = findEntity( 
					"select product_category_id, name, description "
					+ " from product_category where product_id = " + product.productId );
			
			if( rs.next() )
			{
				pc = new ProductCategory();
				pc.description = rs.getString( "description" );
				pc.name = rs.getString( "name" );
				pc.productCategoryId = rs.getLong( "product_category_id" );
			}
		}
		catch( SQLException ex )
		{
			throw new RuntimeException( ex );
		}
		
		return pc;
	}
}

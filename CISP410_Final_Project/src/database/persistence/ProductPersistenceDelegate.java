package database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.util.DatabaseUtil;
import entities.Product;
import entities.ProductCategory;

public class ProductPersistenceDelegate
{
	/**
	 * Persists the Product entity
	 * Implies the ProductCategory will already be persisted
	 * 
	 * @param product
	 */
	public void persistProduct( Product product )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( product.productId != null )
			{
				stmt = createUpdateProductStatement( product, conn );
			}
			else
			{
				stmt = createInsertProductStatement( product, conn );
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
	
	private PreparedStatement createInsertProductStatement( Product product, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into product "
				+ "(name, price, weight, description, product_category_id) "
				+ " values(?, ?, ?, ?, ?)" );
		
		stmt.setString( 1, product.name );
		stmt.setDouble( 2, product.price );
		stmt.setDouble( 3, product.weight );
		stmt.setString( 4, product.description );
		stmt.setLong( 5, product.category.productCategoryId );
		
		return stmt;
	}

	private PreparedStatement createUpdateProductStatement( Product product, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "update product "
				+ "set name = ?, price = ?, weight = ?, description = ?, product_category_id = ? "
				+ "where product_id = ?" );
		
		stmt.setString( 1, product.name );
		stmt.setDouble( 2, product.price );
		stmt.setDouble( 3, product.weight );
		stmt.setString( 4, product.description );
		stmt.setLong( 5, product.category.productCategoryId );
		stmt.setLong( 6, product.productId );
		
		return stmt;
	}

	/**
	 * Persists the ProductCategory entity
	 * 
	 * @param cat
	 */
	public void persistProductCategory( ProductCategory cat )
	{
		Connection conn = DatabaseUtil.getDatabaseConnection();
		PreparedStatement stmt = null;
		
		try
		{
			if( cat.productCategoryId != null )
			{
				stmt = createUpdateProductCategoryStatement( cat, conn );
			}
			else
			{
				stmt = createInsertProductCategoryStatement( cat, conn );
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

	private PreparedStatement createInsertProductCategoryStatement( ProductCategory cat, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into product_category "
				+ "(name, description) "
				+ " values(?, ?)" );
		
		stmt.setString( 1, cat.name );
		stmt.setString( 2, cat.description );
		
		return stmt;
	}

	private PreparedStatement createUpdateProductCategoryStatement( ProductCategory cat, Connection conn ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement( "insert into product_category "
				+ "(product_category_id, name, description) "
				+ " values(?, ?, ?)" );
		
		stmt.setString( 1, cat.name );
		stmt.setString( 2, cat.description );
		stmt.setLong( 3, cat.productCategoryId );
		
		return stmt;
	}
}

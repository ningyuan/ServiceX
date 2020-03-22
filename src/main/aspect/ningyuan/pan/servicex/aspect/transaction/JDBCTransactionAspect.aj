/**
 * 
 */
package ningyuan.pan.servicex.aspect.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;


/**
 * @author ningyuan
 *
 */
public aspect JDBCTransactionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCTransactionAspect.class);
	
	private DataSourceManager<Connection> dataSourceManager;
	
	pointcut eHandler() : handler(Throwable+);
	
	pointcut inServiceMethods() : withincode(public * ningyuan.pan.servicex.impl.*.*(..));
	
	pointcut exeServiceMethods() : execution(public * ningyuan.pan.servicex.impl.*.*(..));
	
	pointcut notInJunitClasses() : !within(ningyuan.pan.servicex.impl.Test*);
	
	/*
	 * Start transaction
	 */
	before() : exeServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Start transaction");
		
		dataSourceManager = (JDBCDataSourceManager)ServiceXUtil.getInstance().getGelobalObject("JDBCDSM");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.initAndGetThreadLocalConnection();
		
			if(con != null) {
				try {
					con.setAutoCommit(false);
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Commit transaction
	 */
	after() : exeServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Commit transaction");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.initAndGetThreadLocalConnection();
		
			if(con != null) {
				try {
					con.commit();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					dataSourceManager.removeAndCloseThreadLocalConnection();
				}	
			}
		}
	}
	
	/*
	 * Rollback transaction
	 */
	before() : eHandler() && inServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Rollback transaction");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				try {
					con.rollback();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					dataSourceManager.removeAndCloseThreadLocalConnection();
				}
			}
		}
	}
}

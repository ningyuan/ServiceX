/**
 * 
 */
package ningyuan.pan.servicex.aspect.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;


/**
 * @author ningyuan
 *
 */
public aspect JDBCTransactionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCTransactionAspect.class);
	
	private DataSourceManager<Connection> dataSourceManager;
	
	pointcut exceptionHandler() : handler(Throwable+);
	
	pointcut inServiceMethods() : withincode(public * ningyuan.pan.servicex.impl.*.*(..));
	
	pointcut exeServiceMethods() : execution(public * ningyuan.pan.servicex.impl.*.*(..));
	
	pointcut notInJunitClasses() : !within(ningyuan.pan.servicex.impl.Test*);
	
	
	//Start transaction
	before() : exeServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Start transaction");
		
		dataSourceManager = (JDBCDataSourceManager)ServiceXUtil.getInstance().getGelobalObject("JDBCDSM");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.initAndGetThreadLocalConnection();
		
			if(con != null) {
				try {
					con.setAutoCommit(false);
					
					LOGGER.debug("Set auto commit");
				} 
				catch (SQLException e) {
					LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
				}
			}
			else {
				LOGGER.debug("No thread local connection");
			}
		}
		else {
			LOGGER.debug("No data source manager set in context");
		}
	}
	
	
	//Commit transaction
	after() : exeServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Commit transaction");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.getThreadLocalConnection();
		
			if(con != null) {
				try {
					con.commit();
					LOGGER.debug("Commit");
				} 
				catch (SQLException e) {
					LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
				}
				finally {
					dataSourceManager.removeAndCloseThreadLocalConnection();
				}	
			}
			else {
				LOGGER.debug("No thread local connection");
			}
		}
		else {
			LOGGER.debug("No data source manager set in context");
		}
	}
	
	//Rollback transaction
	before() : exceptionHandler() && inServiceMethods() && notInJunitClasses() {
		LOGGER.debug("Rollback transaction");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.getThreadLocalConnection();
			
			if(con != null) {
				try {
					con.rollback();
					
					LOGGER.debug("Rollback");
				} 
				catch (SQLException e) {
					LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
				}
				finally {
					dataSourceManager.removeAndCloseThreadLocalConnection();
				}
			}
			else {
				LOGGER.debug("No thread local connection");
			}
		}
		else {
			LOGGER.debug("No data source manager set in context");
		}
	}
}

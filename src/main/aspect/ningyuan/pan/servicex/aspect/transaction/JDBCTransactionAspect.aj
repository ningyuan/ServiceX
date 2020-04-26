/**
 * 
 */
package ningyuan.pan.servicex.aspect.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;


/**
 * Transaction aspect for weaving transaction codes in services methods with data source implemented 
 * with JDBC. Nested service call is also handed.
 * 
 * @author ningyuan
 *
 */
public aspect JDBCTransactionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCTransactionAspect.class);
	
	private final DataSourceManager<Connection> dataSourceManager = 
			(JDBCDataSourceManager)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JDBC_DATA_SOURCE_MANAGER);
	
	pointcut exceptionHandler() : handler(Throwable+);
	
	/*
	 * in all public methods in interfaces with a name ending with Service in service
	 * packages and RS service packages
	 */
	pointcut inServiceMethods() : withincode(public * ningyuan.pan.servicex.*Service.*(..))
								  ||
								  withincode(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..));
	
	pointcut notInCflowBelowOfServicesMethods() : !cflowbelow(execution(public * ningyuan.pan.servicex.*Service.*(..)));
	
	pointcut notInCflowOfRSServiceMethods() : !cflow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..)));
	
	pointcut notInCflowBelowOfRSServiceMethods() : !cflowbelow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..)));
	
	/*
	 * execution of all public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in RS service packages
	 */
	pointcut exeRSServiceMethods() : execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..))
	                                 &&
	                                 notInCflowBelowOfRSServiceMethods();
	
	/*
	 * execution of all public methods in interfaces in service packages and not in the 
	 * control flow of public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in service packages
	 */
	pointcut exeServiceMethods() : execution(public * ningyuan.pan.servicex.*Service.*(..))
								   &&
								   notInCflowOfRSServiceMethods()
								   &&
								   notInCflowBelowOfServicesMethods();
		
	pointcut notInJunitClasses() : !within(ningyuan.pan.servicex.impl.Test*)
									&&
								   !within(ningyuan.pan.servicex.webservice.rs.impl.Test*);

	
	/*
	//Start transaction
	before() : (exeServiceMethods() || exeRSServiceMethods()) && notInJunitClasses() {
		LOGGER.debug("startTransaction()");
		
		if(dataSourceManager != null) {
			Connection con = dataSourceManager.getOrInitThreadLocalConnection();
		
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
	after() : (exeServiceMethods() || exeRSServiceMethods()) && notInJunitClasses() {
		LOGGER.debug("commitTransaction()");
		
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
					// remove the thread local connection to prevent
					// memory leak when thread poll is used
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
		LOGGER.debug("rollbackTransaction()");
		
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
					 // remove the thread local connection so the commit operation afterwards 
					 // will not be executed.
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
	*/
}

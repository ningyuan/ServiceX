/**
 * 
 */
package ningyuan.pan.servicex.aspect.transaction;


import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;

/**
 * @author ningyuan
 *
 */
@Aspect
public class MybatisTransactionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MybatisTransactionAspect.class);
	
	private DataSourceManager<SqlSession> dataSourceManager;
	
	@Pointcut("handler(Throwable+)")
	private void exceptionHandler() {};
	
	/*
	 * in all public methods in interfaces with a name containing Service in service
	 * packages and RS service packages
	 */
	@Pointcut("withincode(public * ningyuan.pan.servicex.*Service*.*(..))"
			+ " || "
			+ "withincode(public * ningyuan.pan.servicex.webservice.rs.*Service*.*(..))")
	private void inServiceMethods() {};
	
	@Pointcut("!cflowbelow(execution(public * ningyuan.pan.servicex.*Service*.*(..)))")
	private void notInCflowBelowOfServicesMethods() {};
	
	@Pointcut("!cflow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service*.*(..)))")
	private void notInCflowOfRSServiceMethods() {};

	@Pointcut("!cflowbelow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service*.*(..)))")
	private void notInCflowBelowOfRSServiceMethods() {};
	
	/*
	 * execution of all public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in RS service packages
	 */
	@Pointcut("execution(public * ningyuan.pan.servicex.webservice.rs.*Service*.*(..))"
			+ " && "
			+ "notInCflowBelowOfRSServiceMethods()")
	private void exeRSServiceMethods() {};
		
	/*
	 * execution of all public methods in interfaces in service packages and not in the 
	 * control flow of public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in service packages
	 */
	@Pointcut("execution(public * ningyuan.pan.servicex.*Service*.*(..))"
			+ " && "
			+ "notInCflowOfRSServiceMethods()"
			+ " && "
			+ "notInCflowBelowOfServicesMethods()")
	private void exeServiceMethods() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.impl.Test*)"
			+ " && "
			+ "!within(ningyuan.pan.servicex.webservice.rs.impl.Test*)")
	private void notInJunitClasses() {};
	
	
	/*
	@Before("(exeServiceMethods() || exeRSServiceMethods()) && notInJunitClasses()")
	public void startTransaction() {
		LOGGER.debug("Start transaction");
		
		dataSourceManager = (MybatisDataSourceManager)ServiceXUtil.getInstance().getGelobalObject("MybatisDSM");
		
		if(dataSourceManager != null) {
			// connection is set auto commit false and transaction isolation level
			SqlSession con = dataSourceManager.initAndGetThreadLocalConnection();
		
			if(con != null) {
				LOGGER.debug("Set auto commit");
			}
			else {
				LOGGER.debug("No thread local connection");
			}
		}
		else {
			LOGGER.debug("No data source manager set in context");
		}
	}
	
	@After("(exeServiceMethods() || exeRSServiceMethods()) && notInJunitClasses()")
	public void commitTransaction() {
		LOGGER.debug("Commit transaction");
		
		if(dataSourceManager != null) {
			SqlSession con = dataSourceManager.getThreadLocalConnection();
		
			if(con != null) {
				try {
					con.commit();
					LOGGER.debug("Commit");
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
	
	@Before("exceptionHandler() && inServiceMethods() && notInJunitClasses()")
	public void rollbackTransaction() {
		LOGGER.debug("Rollback transaction");
		
		if(dataSourceManager != null) {
			SqlSession con = dataSourceManager.getThreadLocalConnection();
			
			if(con != null) {
				try {
					con.rollback();
					
					LOGGER.debug("Rollback");
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
	}*/
}

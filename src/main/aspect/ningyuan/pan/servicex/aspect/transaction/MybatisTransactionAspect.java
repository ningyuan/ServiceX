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

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;

/**
 * Transaction aspect for weaving transaction codes in services methods with data source implemented 
 * with Mybatis. Nested service call is also handed.
 * 
 * @author ningyuan
 *
 */
@Aspect
public class MybatisTransactionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MybatisTransactionAspect.class);
	
	private final DataSourceManager<SqlSession> dataSourceManager = 
			(MybatisDataSourceManager)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
	
	
	@Pointcut("handler(Throwable+)")
	private void exceptionHandler() {};
	
	/*
	 * in all public methods in interfaces with a name ending with Service in service
	 * packages and RS service packages
	 */
	@Pointcut("withincode(public * ningyuan.pan.servicex.*Service.*(..))"
			+ " || "
			+ "withincode(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..))")
	private void inServiceMethods() {};
	
	@Pointcut("!cflowbelow(execution(public * ningyuan.pan.servicex.*Service.*(..)))")
	private void notInCflowBelowOfServicesMethods() {};
	
	@Pointcut("!cflow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..)))")
	private void notInCflowOfRSServiceMethods() {};

	@Pointcut("!cflowbelow(execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..)))")
	private void notInCflowBelowOfRSServiceMethods() {};
	
	/*
	 * execution of all public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in RS service packages
	 */
	@Pointcut("execution(public * ningyuan.pan.servicex.webservice.rs.*Service.*(..))"
			+ " && "
			+ "notInCflowBelowOfRSServiceMethods()")
	private void exeRSServiceMethods() {};
		
	/*
	 * execution of all public methods in interfaces in service packages and not in the 
	 * control flow of public methods in interfaces in RS service packages and not in the
	 * control flow below of public methods in interfaces in service packages
	 */
	@Pointcut("execution(public * ningyuan.pan.servicex.*Service.*(..))"
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
		LOGGER.debug("startTransaction()");
		
		if(dataSourceManager != null) {
			// connection is set auto commit false and transaction isolation level
			SqlSession con = dataSourceManager.getOrInitThreadLocalConnection();
		
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
		LOGGER.debug("commitTransaction()");
		
		if(dataSourceManager != null) {
			SqlSession con = dataSourceManager.getThreadLocalConnection();
		
			if(con != null) {
				try {
					con.commit();
					LOGGER.debug("Commit");
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
	
	@Before("exceptionHandler() && inServiceMethods() && notInJunitClasses()")
	public void rollbackTransaction() {
		LOGGER.debug("rollbackTransaction()");
		
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

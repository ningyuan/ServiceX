/**
 * 
 */
package ningyuan.pan.servicex.aspect.transaction;


import javax.transaction.UserTransaction;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atomikos.icatch.jta.UserTransactionImp;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public aspect AtomikosJTATransactionAspect {
private static final Logger LOGGER = LoggerFactory.getLogger(AtomikosJTATransactionAspect.class);
	
	private UserTransaction userTransaction;
	
	private final DataSourceManager XADataSourceManager = 
			(DataSourceManager)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.XA_DATA_SOURCE_MANAGER);
	
	private final DataSourceManager JMSXADataSourceManager = 
			(DataSourceManager)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_XA_DATA_SOURCE_MANAGER);
	
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
		
	pointcut notInCflowOfJunitMethods(): !cflow(execution(* ningyuan.pan.servicex.impl.Test*.*(..)))
										&&
										!cflow(execution(* ningyuan.pan.servicex.webservice.rs.impl.Test*.*(..)));
	
	
	//Start transaction
	before() : (exeServiceMethods() || exeRSServiceMethods()) && notInCflowOfJunitMethods() {
		LOGGER.debug("startTransaction()");
		
		userTransaction = new UserTransactionImp();
		try {
			userTransaction.begin();
			
			if(XADataSourceManager != null) {
				XADataSourceManager.getOrInitThreadLocalConnection();
			}
			
			if(JMSXADataSourceManager != null) {
				JMSXADataSourceManager.getOrInitThreadLocalConnection();
			}
		}
		catch (Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
	}
	
	
	//Commit transaction
	after() : (exeServiceMethods() || exeRSServiceMethods()) && notInCflowOfJunitMethods() {
		LOGGER.debug("commitTransaction()");
		
		try {
			if(userTransaction != null) {
				userTransaction.commit();
			}
		}
		catch (Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		finally {
			if(XADataSourceManager != null) {
				XADataSourceManager.removeAndCloseThreadLocalConnection();
			}
			
			if(JMSXADataSourceManager != null) {
				JMSXADataSourceManager.removeAndCloseThreadLocalConnection();
			}
			
			userTransaction = null;
		}
	}
	
	//Rollback transaction
	before() : exceptionHandler() && inServiceMethods() && notInCflowOfJunitMethods() {
		LOGGER.debug("rollbackTransaction()");
		
		try {
			userTransaction.rollback();
		}
		catch (Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		finally {
			if(XADataSourceManager != null) {
				XADataSourceManager.removeAndCloseThreadLocalConnection();
			}
			
			if(JMSXADataSourceManager != null) {
				JMSXADataSourceManager.removeAndCloseThreadLocalConnection();
			}
			
			// prevent committing after rollback
			userTransaction = null;
		}
	}
}

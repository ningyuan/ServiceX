/**
 * 
 */
package ningyuan.pan.servicex.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.util.exception.ExceptionUtils;

/**
 * @author ningyuan
 *
 */
@Aspect
public class DebugAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DebugAspect.class);
	
	
	// Log method entering information
	@Pointcut("execution(* ningyuan.pan.servicex.impl.*Impl.*(..))"
			+ " || "
			+ "execution(* ningyuan.pan.servicex.webservice.rs.impl.*Impl.*(..))")
	private void exeAllMethods() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.impl.Test*)"
			+ " && "
			+ "!within(ningyuan.pan.servicex.webservice.rs.impl.Test*)")
	private void notInJunitClasses() {};
	
	@Before("exeAllMethods() && notInJunitClasses()")
	public void logMethod(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.debug(object.getClass().getName()+"."+s.getName()+"()");
	}

	
	// Log exception information
	@Pointcut("handler(Throwable+) && args(e)")
	private void exceptionHandler(Throwable e) {};
	
	@Pointcut("withincode(* ningyuan.pan.servicex.impl.*Impl.*(..))"
			+ " || "
			+ "withincode(* ningyuan.pan.servicex.webservice.rs.impl.*Impl.*(..))")
	private void inAllMethods() {}
	
	@Before("exceptionHandler(e) && inAllMethods() && notInJunitClasses()")
	public void logException(JoinPoint joinPoint, Throwable e) {
		LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
	}
}

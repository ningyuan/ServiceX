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
	
	/*
	 * Log method entering information
	 * 
	 */
	@Pointcut("execution(* ningyuan.pan.servicex.impl.*.*(..))")
	private void exeAllMethods() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.impl.Test*)")
	private void notInJunitClass() {};
	
	@Before("exeAllMethods() && notInJunitClass()")
	public void logMethod(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.debug(object.getClass().getName()+"."+s.getName()+"()");
	}
	
	/*
	 * Log aspect entering information (not work)
	 * 
	 */
	@Pointcut("execution(* ningyuan.pan.servicex.aspect.transaction.*.*(..))")
	private void exeAllTAspectMethods() {};
	
	@Pointcut("within(ningyuan.pan.servicex.aspect.*)")
	private void inTAspectClass() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.aspect.Test*)")
	private void notInAspectJunitClass() {};
	
	@Before("inTAspectClass()")
	public void logAspectMethod(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.debug(object.getClass().getName()+"."+s.getName()+"()");
	}
	
	/*
	 * Log exception information
	 * 
	 */
	@Pointcut("handler(Throwable+) && args(e)")
	private void exceptionHandler(Throwable e) {};
	
	@Pointcut("withincode(* ningyuan.pan.servicex.impl.*.*(..))")
	private void inAllMethods() {}
	
	@Before("exceptionHandler(e) && inAllMethods() && notInJunitClass()")
	public void logException(JoinPoint joinPoint, Throwable e) {
		LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
	}
}

/**
 * 
 */
package ningyuan.pan.servicex.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.util.exception.ExceptionUtils;


/**
 * @author ningyuan
 *
 */
@Aspect
public class TraceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraceAspect.class);
	
	/*
	// Log method entering information
	@Pointcut("execution(* ningyuan.pan.servicex.impl.*.*(..))"
			+ " || "
			+ "execution(* ningyuan.pan.servicex.webservice.rs.impl.*.*(..))")
	private void exeAllMethods() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.impl.Test*)"
			+ " && "
			+ "!within(ningyuan.pan.servicex.webservice.rs.impl.Test*)")
	private void notInJunitClassese() {};
	
	
	@Before("exeAllMethods() && notInJunitClassese()")
	public void logMethod(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.trace(object.getClass().getName()+"."+s.getName()+"()");
	}
	
	// Log aspect entering information (not woven in advice methods)
	@Pointcut("execution(* ningyuan.pan.servicex.aspect.transaction.*.*(..))")
	private void exeAllTAspectMethods() {};
	
	@Pointcut("within(ningyuan.pan.servicex.aspect.transaction.*)")
	private void inTAspectClass() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.aspect.Test*)")
	private void notInAspectJunitClass() {};
	
	@Before("exeAllTAspectMethods()")  
	public void logAspectMethod(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.trace(object.getClass().getName()+"."+s.getName()+"()");
	}
	
	// Log exception information
	@Pointcut("handler(Throwable+) && args(e)")
	private void exceptionHandler(Throwable e) {};
	
	@Pointcut("withincode(* ningyuan.pan.servicex.impl.*.*(..))"
			+ " || "
			+ "withincode(* ningyuan.pan.servicex.webservice.rs.impl.*.*(..))")
	private void inAllMethods() {}
	
	@Before("exceptionHandler(e) && inAllMethods() && notInJunitClassese()")
	public void logException(JoinPoint joinPoint, Throwable e) {
		LOGGER.trace(ExceptionUtils.printStackTraceToString(e));
	}*/
	
}

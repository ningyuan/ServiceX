/**
 * 
 */
package ningyuan.pan.servicex.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ningyuan
 *
 */
@Aspect
public class TraceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraceAspect.class);
	
	@Pointcut("execution(* ningyuan.pan.servicex.impl.*.*(..))")
	private void exeAllMethods() {};
	
	@Pointcut("!within(ningyuan.pan.servicex.impl.Test*)")
	private void notInJunit() {};
	
	@Before("exeAllMethods() && notInJunit()")
	public void eamBefore(JoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.getThis();
		Signature s = joinPoint.getSignature();
		
		LOGGER.trace(object.getClass().getName()+"."+s.getName()+"()");
	}
}

/**
 * 
 */
package ningyuan.pan.servicex.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


/**
 * @author ningyuan
 *
 */
@Aspect
public class AspectX {
	
	@Pointcut("execution(* ningyuan.pan.servicex.impl.ServiceXImpl.getName(..))")
	private void getName() {};
	
	@Before("getName()")
	public void showBefore(JoinPoint joinPoint) throws Throwable {
		System.out.println("AspectX showBefore()");
	}
	
	@Around("getName()")
	public String showAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("AspectX showAround()");
		Object ret = joinPoint.proceed();
		System.out.println("AspectX showAround()");
		
		return (String)ret;
	}
	
	@After("getName()")
	public void showAfter(JoinPoint joinPoint) throws Throwable {
		System.out.println("AspectX showAfter()");
	}
}

/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author ningyuan
 *
 */
public class MybatisUtil {
	
	private static SqlSessionFactory SESSION_FACTORY;
	
	static {
		try {  
			SESSION_FACTORY = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("conf/mybatis-config.xml"));
		} 
		catch (IOException e) {  
			e.printStackTrace(); 
		}  
	}
	
	public static SqlSession getSession() {
		if(SESSION_FACTORY != null) {
			return SESSION_FACTORY.openSession();
		}
		else {
			return null;
		}
	}
}

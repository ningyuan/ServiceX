/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ningyuan.pan.servicex.persistence.dao.UserDAO;

/**
 * @author ningyuan
 *
 */
public class DAOMybatisUtil {
	
	private static SqlSessionFactory SESSION_FACTORY;
	
	static {
		try {  
			SESSION_FACTORY = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("conf/mybatis-config.xml"));
		} 
		catch (IOException e) {  
			e.printStackTrace(); 
		}  
	}
	
	public static UserDAO getDAO() {
		UserDAO ret = null;
		
		SqlSession session = SESSION_FACTORY.openSession();
		
		ret = session.getMapper(UserDAO.class);
		
		return ret;
	}
}
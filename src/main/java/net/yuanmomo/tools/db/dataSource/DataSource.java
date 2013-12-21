/**
 * Project Name : Tools
 * File Name    : DataSource.java
 * Package Name : net.yuanmomo.tools.db.dataSource
 * Created on   : 2013-12-20上午11:52:20
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.db.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * ClassName : DataSource 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-20 上午11:52:20 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public interface DataSource {
	
	/**
	 * init: 初始化连接池. <br/>
	 * 
	 *
	 * @author Hongbin Yuan
	 * @param config
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public boolean init(Map<String,String> params) throws Exception;
	
	/**
	 * destory: 销毁连接池. <br/>
	 * 
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public boolean destory() throws Exception;
	
	/**
	 * refresh: 刷新连接池. <br/>
	 * 
	 *
	 * @author Hongbin Yuan
	 * @param config
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public boolean refresh(Map<String,String> params) throws Exception;
	
	/**
	 * getConnection: 取得连接. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	public Connection getConnection() throws SQLException;
	
	/**
	 * returnConnection: 返回连接. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param con
	 * @return
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	public Connection returnConnection(Connection con) throws SQLException;
}

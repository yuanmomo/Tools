/**
 * Project Name : Tools
 * File Name    : BoneCPDatasource.java
 * Package Name : net.yuanmomo.tools.db.bonecp
 * Created on   : 2013-12-20上午11:50:05
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.db.dataSource.bonecp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.yuanmomo.tools.db.dataSource.DataSource;
import net.yuanmomo.tools.db.dataSource.ParamMap;
import net.yuanmomo.tools.util.properties.PropertiesUtil;
import net.yuanmomo.tools.util.string.StringUtil;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * ClassName : BoneCPDatasource Function : TODO ADD FUNCTION. Reason : TODO ADD
 * REASON. Date : 2013-12-20 上午11:50:05
 * 
 * @author : Hongbin Yuan
 * @version
 * @since JDK 1.6
 * @see
 */
public class BoneCPDataSource implements DataSource {
	private static BoneCP connectionPool = null;
	private static BoneCPConfig config = null;
	
	/**
	 * init: 初始化BoneCP数据源. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param params
	 * @return
	 * @throws Exception
	 * @see net.yuanmomo.tools.db.dataSource.DataSource#init(java.util.Map)
	 * @since JDK 1.6
	 */
	@Override
	public boolean init(Map<String,String> params) throws Exception {
		// 判断params是否为null，如果为空，则加载默认的propertis文件
		if(params == null){
			params = PropertiesUtil.propertiesToMap(ParamMap.BONE_CP_PROPERTIES_FILE_LOCATION);
		}
		try {
			Class.forName(params.get(ParamMap.DRIVER));
		} catch (ClassNotFoundException e) {
			throw e;
		}
		try {
			this.updateConfig(params);
			connectionPool = new BoneCP(config); // setup the connection pool
		} catch (SQLException e) {
			throw e;
		} 
		return false;
	}

	/**
	 * destory: 销毁BoneCP数据源. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws Exception
	 * @see net.yuanmomo.tools.db.dataSource.DataSource#destory()
	 * @since JDK 1.6
	 */
	@Override
	public boolean destory() throws Exception {
		if(connectionPool != null){
			try {
				connectionPool.shutdown();
			} catch (Exception e) {
				throw e;
			}
		}
		return true;
	}

	/**
	 * refresh: 更新BoneCP数据源. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param params
	 * @return
	 * @throws Exception
	 * @see net.yuanmomo.tools.db.dataSource.DataSource#refresh(java.util.Map)
	 * @since JDK 1.6
	 */
	@Override
	public boolean refresh(Map<String, String> params) throws Exception {
		this.init(params);
		return true;
	}
	
	
	/**
	 * updateConfig: 设置配置文件值. <br/>
	 * TODO .<br/>
	 * TODO .<br/>
	 *
	 * @author Hongbin Yuan
	 * @param params
	 * @since JDK 1.6
	 */
	private void updateConfig(Map<String, String> params){
		if(config == null){
			config = new BoneCPConfig();
		}
		config.setJdbcUrl(params.get(ParamMap.URL));
		config.setUsername(params.get(ParamMap.USER_NAME));
		config.setPassword(params.get(ParamMap.PASSWORD));
		
		// BoneCP 配置参数
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_PARTITIONCOUNT))){
			config.setPartitionCount(Integer.parseInt(params.get(ParamMap.BONECP_PARTITIONCOUNT)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_MAX_CONNECTIONS_PER_PARTITION))){
			config.setMaxConnectionsPerPartition(Integer.parseInt(params.get(ParamMap.BONECP_MAX_CONNECTIONS_PER_PARTITION)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_MIN_CONNECTIONS_PER_PARTITION))){
			config.setMinConnectionsPerPartition(Integer.parseInt(params.get(ParamMap.BONECP_MIN_CONNECTIONS_PER_PARTITION)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_ACQUIRE_INCREMENT))){
			config.setAcquireIncrement(Integer.parseInt(params.get(ParamMap.BONECP_ACQUIRE_INCREMENT)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_QUERY_EXECUTE_TIME_LIMIT_IN_MS))){
			config.setQueryExecuteTimeLimitInMs(Integer.parseInt(params.get(ParamMap.BONECP_QUERY_EXECUTE_TIME_LIMIT_IN_MS)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_STATEMENTS_CACHE_SIZE))){
			config.setStatementsCacheSize(Integer.parseInt(params.get(ParamMap.BONECP_STATEMENTS_CACHE_SIZE)));
		}
		// Mysql 8小时超时问题
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_CONNECTION_TEST_STATEMENT))){
			config.setConnectionTestStatement(params.get(ParamMap.BONECP_CONNECTION_TEST_STATEMENT));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_IDLE_CONNECTION_TEST_PERIOD_IN_SECONDS))){
			config.setIdleConnectionTestPeriodInSeconds(Integer.parseInt(params.get(ParamMap.BONECP_IDLE_CONNECTION_TEST_PERIOD_IN_SECONDS)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_IDLE_MAX_AGE_IN_MINUTES))){
			config.setIdleMaxAgeInMinutes(Integer.parseInt(params.get(ParamMap.BONECP_IDLE_MAX_AGE_IN_MINUTES)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_MAX_CONNECTION_AGE_IN_SECONDS))){
			config.setMaxConnectionAgeInSeconds(Integer.parseInt(params.get(ParamMap.BONECP_MAX_CONNECTION_AGE_IN_SECONDS)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_CONNECTION_TIMEOUT_IN_MS))){
			config.setConnectionTimeoutInMs(Integer.parseInt(params.get(ParamMap.BONECP_CONNECTION_TIMEOUT_IN_MS)));
		}
		// 日志监控
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_CLOSE_CONNECTION_WATCH))){
			config.setCloseConnectionWatch(Boolean.parseBoolean(params.get(ParamMap.BONECP_CLOSE_CONNECTION_WATCH)));
		}
		if(StringUtil.isNotBlank(params.get(ParamMap.BONECP_LOG_STATEMENTS_ENABLED))){
			config.setLogStatementsEnabled(Boolean.parseBoolean(params.get(ParamMap.BONECP_LOG_STATEMENTS_ENABLED)));
		}
	}
	
	
	/**
	 * getConnection: 从数据源取得一个连接. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	public Connection getConnection() throws SQLException{
		if(connectionPool  != null){
			try {
				return connectionPool.getConnection();
			} catch (SQLException e) {
				throw e;
			}
		}
		return null;
	}
	/**
	 * getConnection: 从数据源取得一个连接. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	public Connection returnConnection(Connection con) throws SQLException{
		try {
			if(con != null && !con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}
}

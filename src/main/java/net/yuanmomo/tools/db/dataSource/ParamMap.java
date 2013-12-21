/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.db.dataSource.bonecp
 * Created on   : 2013-12-20下午2:22:23
 * File Name    : ParamMap.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.db.dataSource;
/**
 * ClassName : ParamMap 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-20 下午2:22:23 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ParamMap {
	
	// commmon configurations
	public static String DRIVER = "driver";
	public static String URL = "url";
	public static String USER_NAME = "username";
	public static String PASSWORD = "password";
	
	/**************************************************************************************************/
	/**************************** BoneCP configuration start ******************************************/
	
	public static String BONE_CP_PROPERTIES_FILE_LOCATION="src/main/resources/net/yuanmomo/tools/db/BoneCP.properties";
	
	public static String BONECP_PARTITIONCOUNT = "partitionCount";
	public static String BONECP_MAX_CONNECTIONS_PER_PARTITION ="maxConnectionsPerPartition";
	public static String BONECP_MIN_CONNECTIONS_PER_PARTITION ="minConnectionsPerPartition";
	public static String BONECP_ACQUIRE_INCREMENT ="acquireIncrement";
	public static String BONECP_QUERY_EXECUTE_TIME_LIMIT_IN_MS ="queryExecuteTimeLimitInMs";
	public static String BONECP_STATEMENTS_CACHE_SIZE ="statementsCacheSize";
	
	// Mysql 8小时超时问题
	public static String BONECP_CONNECTION_TEST_STATEMENT ="connectionTestStatement";
	public static String BONECP_IDLE_CONNECTION_TEST_PERIOD_IN_SECONDS ="idleConnectionTestPeriodInSeconds";
	public static String BONECP_IDLE_MAX_AGE_IN_MINUTES ="idleMaxAgeInMinutes";
	public static String BONECP_MAX_CONNECTION_AGE_IN_SECONDS ="maxConnectionAgeInSeconds";
	public static String BONECP_CONNECTION_TIMEOUT_IN_MS ="connectionTimeoutInMs";
	
	// 日志监控
	public static String BONECP_CLOSE_CONNECTION_WATCH ="closeConnectionWatch";
	public static String BONECP_LOG_STATEMENTS_ENABLED ="logStatementsEnabled";
	
	/**************************** BoneCP configuration end ******************************************/
	/**************************************************************************************************/
}

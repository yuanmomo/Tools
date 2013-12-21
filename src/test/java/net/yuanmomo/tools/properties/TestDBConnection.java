/** Copyright (c) 2013 MoMo, yuanhb@fusionskye.com All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.properties
 * Created on   : Jun 27, 20132:23:17 PM
 * File Name    : TestDBConnection.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.properties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.yuanmomo.tools.db.dataSource.DataSource;
import net.yuanmomo.tools.db.dataSource.bonecp.BoneCPDataSource;

import org.junit.Test;


/**
 * ClassName : TestDBConnection 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 27, 2013 2:23:17 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TestDBConnection {
	@Test
	public void testConnection(){
		DataSource ds = new BoneCPDataSource();
		Connection con = null ;
		try {
			Map<String,String> params = PropertiesUtil.propertiesToMap("src/main/resources/net/yuanmomo/tools/db/BoneCP.properties");
			ds.init(params);
			con = ds.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(con==null?"null":con);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ds.returnConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

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
 * Package Name : net.yuanmomo.tools.db
 * Created on   : Jul 4, 20132:17:29 PM
 * File Name    : JDBCExcutor.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import net.yuanmomo.tools.util.string.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : JDBCExcutor 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jul 4, 2013 2:17:29 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class JDBCExcutor<T> {
	private static Logger logger=LoggerFactory.getLogger(JDBCExcutor.class);
	
	public static <T> List<T> fetch(Class<T> claz,Connection con,String sql,Object[] params) throws Exception{
		PreparedStatement psmt=null;
		ResultSet rs = null;
		try {
			//判断sql中有几个?号，设置相应的值
			int questionMarkCount=StringUtil.charCount(sql, '?');
			//sql中含有?，需要设置值，但是参数却为null，或者参数的个数不等于?的个数, 则报错
			if((questionMarkCount >0 &&( params==null || questionMarkCount != params.length))){
				throw new Exception("Parameters count not match to the SQL. The sql : "+sql);
			}
			logger.info("Start the execute PreparedStatement："+sql);
			psmt=con.prepareStatement(sql);
			//判断sql中有几个?号，设置相应的值，后续完成
			for(int i=0;i<questionMarkCount;i++){
				Object paramValue=params[i];
				if ( paramValue !=null && paramValue instanceof Date){
					psmt.setDate(i, new java.sql.Date(((Date)paramValue).getTime()));
				}else{
					psmt.setString(i,String.valueOf( paramValue));
				}
			}
			rs = psmt.executeQuery();
			return ResultSetToBean.fetch(claz, rs);
		} catch (Exception e) {
			throw e;
		}finally{
			rs.close();
			psmt.close();
		}
	}
	public static <T> List<T> fetch(Class<T> claz,Connection con,String sql) throws Exception{
		PreparedStatement psmt=null;
		ResultSet rs = null;
		try {
			logger.info("Start the execute PreparedStatement："+sql);
			psmt=con.prepareStatement(sql);
			rs = psmt.executeQuery();
			return ResultSetToBean.fetch(claz, rs);
		} catch (Exception e) {
			throw e;
		}finally{
			if(rs!=null){
				rs.close();
				psmt.close();
			}
		}
	}
}

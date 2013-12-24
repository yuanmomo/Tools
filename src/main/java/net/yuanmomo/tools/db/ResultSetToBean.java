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
 * Created on   : Jun 20, 20134:43:21 PM
 * File Name    : ResultSetToBean.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.yuanmomo.tools.util.properties.PropertiesUtil;
import net.yuanmomo.tools.util.time.DateFormat;
import net.yuanmomo.tools.util.time.TimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : ResultSetToBean 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 20, 2013 4:43:21 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ResultSetToBean<T>{
	
	private static Logger logger=LoggerFactory.getLogger(ResultSetToBean.class);
	
	public static <T> List<T> fetch(Class<T> claz,ResultSet rs) throws Exception{
		List<T> result=null;
		ResultSetMetaData  rsMeta=rs.getMetaData();
		logger.info("The record in this ResultSet has "+ rsMeta.getColumnCount() +" columns.");
		Map<String,String> record=new HashMap<String, String>();
		result=new ArrayList<T>();
		while(rs.next()){
			int size=rsMeta.getColumnCount();
			for(int i=1;i<=size;i++){
				String columnName=rsMeta.getColumnName(i);
				int columnType=rsMeta.getColumnType(i);
				String columnNameValue=null;
				switch(columnType){
					case Types.TIMESTAMP :
						Timestamp time=rs.getTimestamp(i);
						columnNameValue=TimeUtil.dateToString(new Date(time.getTime()),DateFormat.YYYY_MM_DD_HH_MM_SS);
						break;
					default : columnNameValue= rs.getString(i);
				}
				record.put(columnName==null?columnName : columnName.toLowerCase(),columnNameValue);
			}
			result.add(PropertiesUtil.mapToBean(claz, record));
		}
		logger.info("This ResultSet has "+ result.size() +" records");
		return result;
	}
}

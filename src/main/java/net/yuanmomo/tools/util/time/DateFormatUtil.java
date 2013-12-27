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
 * Package Name : net.yuanmomo.tools.time
 * Created on   : Jun 24, 20131:49:45 PM
 * File Name    : DateFormat.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.util.time;

import java.util.HashMap;
import java.util.Map;

import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : DateFormat 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 24, 2013 1:49:45 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class DateFormatUtil {

	private static  Map<String, DateFormat> formatMap;
	static{
		formatMap = new HashMap<String, DateFormat>();
		formatMap.put(Format.YYYY_MM_DD_HH_MM_SS,new DateFormat(Format.YYYY_MM_DD_HH_MM_SS));
		formatMap.put(Format.YYYY_MM_DD_HH_MM,new DateFormat(Format.YYYY_MM_DD_HH_MM));
		formatMap.put(Format.YYYY_MM_DD,new DateFormat(Format.YYYY_MM_DD));
		formatMap.put(Format.YYYY_MM,new DateFormat(Format.YYYY_MM));
	}
	
	/**
	 * addFormat: 添加一个日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param format
	 * @since JDK 1.6
	 */
	public static void addFormat(String format){
		if(StringUtil.isBlank(format)){
			return;
		}
		if(CollectionUtil.isNull(formatMap)){
			formatMap = new HashMap<String, DateFormat>();
		}else{
			if(formatMap.containsKey(format)){
				return;
			}
		}
		formatMap.put(format, new DateFormat(format));
	}
	
	/**
	 * getSimpleDateFormat: 取得一个日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param format
	 * @return
	 * @since JDK 1.6
	 */
	public static DateFormat getDateFormat(String format){
		if(StringUtil.isBlank(format)){
			return formatMap.get(Format.YYYY_MM_DD_HH_MM_SS);
		}
		if(!formatMap.containsKey(format)){
			addFormat(format);
		}
		return formatMap.get(format);
	}
	
	/**
	 * getSimpleDateFormat: 取得默认日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static DateFormat getDefaultDateFormat(){
		return getDateFormat();
	}
	
	/**
	 * getSimpleDateFormat: 取得一个日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static DateFormat getDateFormat(){
		return getDateFormat(null);
	}
	
	public class Format{
		public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

		public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
		
		public static final String YYYY_MM_DD = "yyyy-MM-dd";

		public static final String YYYY_MM = "yyyy-MM";
	}
}

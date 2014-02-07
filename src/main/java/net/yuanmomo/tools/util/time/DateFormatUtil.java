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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : DateFormatUtil 
 * Function  : TODO ADD 存放使用的DateFormat的表，用于添加或者取得一个DateFormat对象. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 24, 2013 1:49:45 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class DateFormatUtil {
	/**
	 * formatMap:TODO 存放DateFormat的map，键为日期格式的字符串形势，值为一个DateFormat对象.
	 * @since JDK 1.6
	 */
	private static Map<String, DateFormat> formatMap;
	private static List<String> supported = null;
	
	static{
		// 初始化自带的DateFormat
		formatMap = new HashMap<String, DateFormat>();
		supported = new ArrayList<String>();
		// 反射取得Format类所有的静态变量，转换为相应的DateFormat，并放入map中
		Field[] fields = Format.class.getDeclaredFields();
		if(fields != null && fields.length>0){
			for(Field f : fields){
				boolean isStatic = Modifier.isStatic(f.getModifiers());
		        if(isStatic) {
		            try {
						String format = f.get(null).toString();
						formatMap.put(format,new DateFormat(format));
						
						supported.add(format);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
		        }
			}
		}
	}
	
	/**
	 * addFormat: 添加一个日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param format	一个日期格式字符串，如果为空，则不做任何操作。<br />
	 * 					如果该日期格式字符串已经存在该map表中，也不做任何操作。<br />
	 * 					如果不存在，则添加一个新的DateFormat对象到该map表。<br />
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
		supported.add(format);
	}
	
	/**
	 * getSimpleDateFormat: 取得一个日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param format	一个日期格式字符串，如果为空，则取出默认的日期格式转换器-"yyyy-MM-dd HH:mm:ss"。<br />
	 * 					如果该日期格式字符串不存在该map表中，则添加一个新的DateFormat对象到该map表，并返回该对象。<br />
	 * 					如果存在，则取出DateFormat对象并返回。<br />
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
	 * getSimpleDateFormat: 取得默认日期格式转换器-"yyyy-MM-dd HH:mm:ss". <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static DateFormat getDefaultDateFormat(){
		return getDateFormat();
	}
	
	/**
	 * getSimpleDateFormat: 取得默认日期格式转换器-"yyyy-MM-dd HH:mm:ss". <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static DateFormat getDateFormat(){
		return getDateFormat(null);
	}
	
	/**
	 * getSupportedDateFormat:取得系统支持的日期格式转换器. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static List<String> getSupportedDateFormat(){
		return supported;
	}
	
	public class Format{
		public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
		
		public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

		public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
		
		public static final String YYYY_MM_DD = "yyyy-MM-dd";
		
		public static final String YYYYMMDD = "yyyyMMdd";
		
		public static final String YYYY_MM = "yyyy-MM";
		
		public static final String MM_DD_HH_MM = "MM-dd HH:mm";
		
		public static final String MM_DD= "MM-dd";
	}
}

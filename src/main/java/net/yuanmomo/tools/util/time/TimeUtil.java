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
 * Created on   : Jun 24, 20131:47:23 PM
 * File Name    : TimeUtil.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : TimeUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 24, 2013 1:47:23 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TimeUtil {
	/**
	 * dateToString: 将指定的Date对象转换为字符串. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @param format
	 * @return
	 * @since JDK 1.6
	 */
	public static String dateToString(Date date,String format){
		Date time = date;
		if(time == null){
			time = new Date();
		}
		if(StringUtil.isBlank(format)){
			format = DateFormat.YYYY_MM_DD_HH_MM_SS;
		}
		return  new SimpleDateFormat(format).format(time);
	}
	/**
	 * strToDate: 将指定的字符串转换为Date对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 * @since JDK 1.6
	 */
	public static Date strToDate(String str,String format) throws ParseException{
		if(str==null){
			return new Date();
		}
		if(StringUtil.isBlank(format)){
			format=DateFormat.YYYY_MM_DD_HH_MM_SS;
		}
		return new SimpleDateFormat(format).parse(str);
	}
}

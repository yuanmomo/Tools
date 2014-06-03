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
import java.util.Calendar;
import java.util.Date;

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
public class DateUtil {
	public static final long millisecondsOfOneDay = 24 * 3600 * 1000;
	
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
		DateFormat dateFormat = DateFormatUtil.getDateFormat(format);
		return dateToString(date,dateFormat);
	}
	
	/**
	 * dateToString: 将指定的Date对象转换为字符串. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @param format
	 * @return
	 * @since JDK 1.6
	 */
	public static String dateToString(Date date,DateFormat format){
		if(date == null){
			return null;
		}
		return format.getDateFormat().format(date);
	}
	
	/**
	 * dateToString: 将指定的Date对象转换为字符串. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @param format
	 * @return
	 * @since JDK 1.6
	 */
	public static String dateToString(Date date){
		DateFormat dateFormat = DateFormatUtil.getDefaultDateFormat();
		return  dateToString(date,dateFormat);
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
		DateFormat dateFormat = DateFormatUtil.getDateFormat(format);
		return strToDate(str,dateFormat);
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
	public static Date strToDate(String str,DateFormat format) throws ParseException{
		if(str==null){
			return new Date();
		}
		return format.getDateFormat().parse(str);
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
	public static Date strToDate(String str) throws ParseException{
		DateFormat dateFormat = DateFormatUtil.getDefaultDateFormat();
		return strToDate(str,dateFormat);
	}
	
	/**
	 * getCurrentDate: 得到当前日期. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @since JDK 1.6
	 */
	public static Date getCurrentDate(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * getDate: 得到当前指定日期的年月日. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static Date getDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * getDate: 得到当前指定日期的年月日. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static Date getDate(){
		return getCurrentDate();
	}
	
	/**
	 * addDays: 对指定的日期加上相应的天数. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date		指定的日期
	 * @param days		加上相应的天数，如果为负数则相减<br/>
	 * @return
	 * @since JDK 1.6
	 */
	public static Date addDays(Date date, int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}
	/**
	 * addDays: 对指定的日期加上相应的天数. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param days		在当前的时间上加上相应的天数，如果为负数则相减<br/>
	 * @return
	 * @since JDK 1.6
	 */
	public static Date addDays(int days){
		return addDays(new Date(),days);
	}
	
	
	/**
	 * getCalendar: 返回当前date对象对应的calendar对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static Calendar getCalendar(Date date){
		if(date != null){
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			return c;
		}
		return null;
	}
	/**
	 * 判断指定时间是否当天
	 * @param time
	 * @return
	 */
	public static boolean isCurrentDay(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		Calendar current=Calendar.getInstance();
		return current.get(Calendar.YEAR)==cal.get(Calendar.YEAR)&&current.get(Calendar.MONTH)==cal.get(Calendar.MONTH)&&current.get(Calendar.DAY_OF_MONTH)==cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 判断当前是否在指定时间之后，按自然日计算
	 * @param time
	 * @return
	 */
	public static boolean currentAfterDay(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Calendar current=Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);
		return current.after(cal);
	}
	
	/**
	 * 判断当前是否在指定时间之后1天，按自然日计算
	 * @param time
	 * @return
	 */
	public static boolean currentAfterDay1(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Calendar current=Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);
		return current.equals(cal);
	}
	
	public static boolean isSameDay(Date date1,Date date2){
		if(date1 == null && date2 == null ){
			return true;
		}
		if(date1 == null || date2 == null ){
			return false;
		}
		
		Calendar cal1 = getCalendar(getDate(date1));
		Calendar cal2 = getCalendar(getDate(date2));
		if(cal1 == null && cal2 == null){
			return true;
		}
		if(cal1 != null && cal1.compareTo(cal2) == 0){
			return true;
		}
		return false;
	}
	
	public static long getMicrosecondsOfDays(int days){
		return millisecondsOfOneDay * days;
	}
	
	public static long getRestMicrosecondsOfDay(Date date){
		Date currentDate = getDate(date);
		return millisecondsOfOneDay - (date.getTime() - currentDate.getTime());
	}
	public static long getRestMicrosecondsOfDay(){
		return getRestMicrosecondsOfDay(new Date());
	}
	
}

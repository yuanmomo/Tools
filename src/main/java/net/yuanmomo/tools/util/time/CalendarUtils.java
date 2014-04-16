package net.yuanmomo.tools.util.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author 袁鸿彬
 *
 * 2014-2-13
 */
public class CalendarUtils {

	/**
	 * 日期转为字符串，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param cal
	 *            日期
	 * @return
	 */
	public static String calendarToString_yyyyMMdd(Calendar cal) {
		if (cal == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	/**
	 * 日期转为字符串，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param cal
	 *            日期
	 * @return
	 */
	public static String calendarToString_yyyyMMdd_HHmmss(Calendar cal) {
		if (cal == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		return date;
	}

	/**
	 * 日期转为字符串，格式为自定义
	 * 
	 * @param cal
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 */
	public static String calendarToString_yyyyMMdd_HHmmss(Calendar cal,
			String format) {
		if (cal == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	/**
	 * 格林威治时间转为calendar
	 * @param time 格林威治时间
	 * @return
	 */
	public static Calendar convertCalendar(Long time) {
		if (time == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal;
	}
	
	/**
	 * 日期数字字符串 转 Calendar
	 * @param value
	 * @return
	 */
	public static Calendar convertCalendar(String value) {
		if (value == null)
			return null;
		try {
			Long time = Long.parseLong(value);
			return convertCalendar(time);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * String 转Calendar
	 * 
	 * @param sTime
	 * @param format
	 *            格式
	 * @return
	 */
	public static Calendar calendarFromString(String sTime, String format) {
		if (format == null)
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date = new java.util.Date();
		try {
			date = sdf.parse(sTime);
		} catch (java.text.ParseException e) {
		}
		Calendar cRt = Calendar.getInstance();
		cRt.setTime(date);
		return cRt;
	}
	
	/**
	 * 判断当前时间是否处于start和end时间之间
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean between(Date start, Date end){
		Date current=new Date();
		return current.after(start)&&current.before(end);
	}
	
	/**
	 * 判断当前时间是否处于start和end时间之间
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean between(Calendar start, Calendar end){
		Calendar current=Calendar.getInstance();
		return current.after(start)&&current.before(end);
	}
	
	/**
	 * 判断指定时间是否当天
	 * @param time
	 * @return
	 */
	public static boolean isCurrentDay(long time){
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(time);
		Calendar current=Calendar.getInstance();
		return current.get(Calendar.YEAR)==cal.get(Calendar.YEAR)&&current.get(Calendar.MONTH)==cal.get(Calendar.MONTH)&&current.get(Calendar.DAY_OF_MONTH)==cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 判断当前是否在指定时间之后，按自然日计算
	 * @param time
	 * @return
	 */
	public static boolean currentAfterDay(long time){
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(time);
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
	public static boolean currentAfterDay1(long time){
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(time);
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
	
	/**
	 * 判断两个指定时间是否同一天
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean isSameDay(Calendar c1,Calendar c2){
		return c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)&&c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)&&c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH);
	}
	
	public static void main(String[] args) {
		Calendar s=Calendar.getInstance();
		s.set(Calendar.YEAR, 2013);
		Calendar e=Calendar.getInstance();
		e.set(Calendar.YEAR, 2015);
		System.out.println(between(s.getTime(),e.getTime()));
	}
}

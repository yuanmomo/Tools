/**
 * Project Name : Tools
 * File Name    : Bean.java
 * Package Name : net.yuanmomo.tools.plugin.spring.springmvc.json.bean
 * Created on   : 2014-1-6上午11:41:42
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.plugin.spring.springmvc.bean;

import java.util.Calendar;
import java.util.Date;

import net.yuanmomo.tools.util.time.DateFormatUtil.Format;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ClassName : Bean 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-1-6 上午11:41:42 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class Bean {
	private int id;
	private String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING, 
    		pattern=Format.YYYY_MM_DD_HH_MM_SS_SSS,timezone="GMT+8")
	private Date birthday;
	
	private Date date2;
	private Calendar cal;
	
	/**
	 * date2.
	 *
	 * @return  the date2
	 * @since   JDK 1.6
	 */
	public Date getDate2() {
		return date2;
	}
	/**
	 * date2.
	 *
	 * @param   date2    the date2 to set
	 * @since   JDK 1.6
	 */
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	/**
	 * cal.
	 *
	 * @return  the cal
	 * @since   JDK 1.6
	 */
	public Calendar getCal() {
		return cal;
	}
	/**
	 * cal.
	 *
	 * @param   cal    the cal to set
	 * @since   JDK 1.6
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
	}
	/**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.6
	 */
	public int getId() {
		return id;
	}
	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.6
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * name.
	 *
	 * @return  the name
	 * @since   JDK 1.6
	 */
	public String getName() {
		return name;
	}
	/**
	 * name.
	 *
	 * @param   name    the name to set
	 * @since   JDK 1.6
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * birthday.
	 *
	 * @return  the birthday
	 * @since   JDK 1.6
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * birthday.
	 *
	 * @param   birthday    the birthday to set
	 * @since   JDK 1.6
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}

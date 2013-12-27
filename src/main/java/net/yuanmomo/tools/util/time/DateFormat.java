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

import java.text.SimpleDateFormat;


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
public class DateFormat {

	private final String format;
	private final int formatLength;
	private SimpleDateFormat dateFormat;
	
	public DateFormat(String format){
		this.format = format;
		this.formatLength = format.length();
		this.dateFormat = new SimpleDateFormat(format);
	}

	/**
	 * dateFormat.
	 *
	 * @return  the dateFormat
	 * @since   JDK 1.6
	 */
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * dateFormat.
	 *
	 * @param   dateFormat    the dateFormat to set
	 * @since   JDK 1.6
	 */
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * format.
	 *
	 * @return  the format
	 * @since   JDK 1.6
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * formatLength.
	 *
	 * @return  the formatLength
	 * @since   JDK 1.6
	 */
	public int getFormatLength() {
		return formatLength;
	}
}

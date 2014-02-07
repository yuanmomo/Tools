/**
 * Project Name : Tools
 * File Name    : TestJackson2.java
 * Package Name : net.yuanmomo.tools.plugin.spring.springmvc.json
 * Created on   : 2014-1-6上午11:41:21
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.json.plugin.spring.springmvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.yuanmomo.tools.plugin.spring.springmvc.bean.Bean;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * ClassName : TestJackson2 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-1-6 上午11:41:21 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TestJackson2 {
	@Test
	public void test() {
		CustomObjectMapper mapper = new CustomObjectMapper();
        
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		
		Bean b = new Bean();
		b.setId(10);
		b.setName("yuanmomo");
		b.setBirthday(new Date());
		b.setDate2(new Date());
		b.setCal(Calendar.getInstance());
		
		try {
			System.out.println(mapper.writeValueAsString(b));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

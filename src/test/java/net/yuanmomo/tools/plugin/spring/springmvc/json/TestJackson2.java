/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.plugin.spring.springmvc.json
 * Created on   : 2014-1-6上午11:41:21
 * File Name    : TestJackson2.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */
/**
 * Project Name : Tools
 * File Name    : TestJackson2.java
 * Package Name : net.yuanmomo.tools.plugin.spring.springmvc.json
 * Created on   : 2014-1-6上午11:41:21
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.plugin.spring.springmvc.json;

import java.util.ArrayList;
import java.util.List;

import net.yuanmomo.tools.plugin.spring.springmvc.json.bean.Bean;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

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
		DefaultSerializerProvider sp = new DefaultSerializerProvider.Impl();
		sp.setNullValueSerializer(new NullSerializer());
		// And then configure mapper to use it
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializerProvider(sp);
		Bean b = new Bean();
		b.setId(10);
		b.setName("yuanmomo");
		b.setBirthday(null);

		List<Bean> list = new ArrayList<Bean>();
		list.add(b);
		try {
			System.out.println(mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

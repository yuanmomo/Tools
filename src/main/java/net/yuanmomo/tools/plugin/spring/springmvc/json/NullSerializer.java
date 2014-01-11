/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.plugin.spring.springmvc.json
 * Created on   : 2014-1-6下午3:09:46
 * File Name    : NullSerializer.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.plugin.spring.springmvc.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * ClassName : NullSerializer 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-1-6 下午3:09:46 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class NullSerializer extends JsonSerializer<Object> {
	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// any JSON value you want...
		if(value instanceof Number){
			jgen.writeString("0");
		}
		jgen.writeString("");
	}
}

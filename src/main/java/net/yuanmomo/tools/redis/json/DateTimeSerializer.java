
/**
 * Project Name : Tools
 * File Name    : DateTimeSerializer.java
 * Package Name : net.yuanmomo.tools.redis.json
 * Created on   : 2014-4-10下午10:40:22
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.redis.json;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * ClassName : DateTimeSerializer 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-10 下午10:40:22 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class DateTimeSerializer  implements JsonSerializer<Date> {

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.getTime());
	}
}


/**
 * Project Name : Tools
 * File Name    : DateTimeDeserializer.java
 * Package Name : net.yuanmomo.tools.redis.json
 * Created on   : 2014-4-10下午10:44:32
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.redis.json;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * ClassName : DateTimeDeserializer 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-10 下午10:44:32 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class DateTimeDeserializer implements JsonDeserializer<Date>  {
	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		  return new Date(json.getAsJsonPrimitive().getAsLong()); 
	}
}

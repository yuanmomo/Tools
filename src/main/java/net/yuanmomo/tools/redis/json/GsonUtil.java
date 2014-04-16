
/**
 * Project Name : Tools
 * File Name    : GsonUtil.java
 * Package Name : net.yuanmomo.tools.redis.json
 * Created on   : 2014-4-10下午10:35:14
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.redis.json;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName : GsonUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-10 下午10:35:14 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class GsonUtil {
	private static GsonBuilder redisGsonBuilder = new GsonBuilder();
	private static Gson gson = null;
	
	static{
		gson = redisGsonBuilder.setDateFormat(DateFormat.LONG).create(); 
	}
	/**
	 * 将对象转换成json格式
	 * 
	 * @param ts
	 * @return
	 */
	public static String toJson(Object ts) {
	    String jsonStr = null;
	    if (gson != null) {
	        jsonStr = gson.toJson(ts);
	    }
	    return jsonStr;
	}

	/**
	 * 将json格式转换成list对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<?> toList(String jsonStr) {
	    List<?> objList = null;
	    if (gson != null) {
	        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
	        }.getType();
	        objList = gson.fromJson(jsonStr, type);
	    }
	    return objList;
	}

	/**
	 * 将json格式转换成list对象，并准确指定类型
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static List<?> toList(String jsonStr, java.lang.reflect.Type type) {
	    List<?> objList = null;
	    if (gson != null) {
	        objList = gson.fromJson(jsonStr, type);
	    }
	    return objList;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> toMap(String jsonStr) {
	    Map<?, ?> objMap = null;
	    if (gson != null) {
	        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
	        }.getType();
	        objMap = gson.fromJson(jsonStr, type);
	    }
	    return objMap;
	}

	/**
	 * 获得JSON对象
	 * 
	 * @param jsonString
	 * @param cls
	 * @return Object
	 */
	public static <T> T fromJson(String jsonString, Class<T> cls) {
	    T t = null;
	    try {
	        t = gson.fromJson(jsonString, cls);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return t;
	}
}

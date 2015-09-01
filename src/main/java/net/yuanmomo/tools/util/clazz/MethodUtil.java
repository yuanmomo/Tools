
/**
 * Project Name : Tools
 * File Name    : MethodUtil.java
 * Package Name : net.yuanmomo.tools.util.clazz
 * Created on   : 2014-4-29下午1:24:54
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.clazz;

import java.util.regex.Pattern;

import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : MethodUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-29 下午1:24:54 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class MethodUtil {
	
	/**
	 *  对于属性 rId, 这个时候的setter= setrId, getter = getrId, 也就是说当属性的第一个字母小写, 第二个字母大写,
	 *  这个时候,setter和getter不能将首字母大写
	 */
	private static final String filterGetterRegex = "^[a-z][A-Z].*";
	
	/**
	 * . <br/>
	 *
	 * @author Hongbin Yuan
	 * @param propName
	 * @return
	 */
	public static String getGetter(String propName){
		StringBuilder sb = new StringBuilder("get");
		if(!Pattern.matches(filterGetterRegex, propName)){
			return sb.append(StringUtil.upperFirstChar(propName)).toString();
		}
		return sb.append(StringUtil.upperFirstChar(propName)).toString();
	}
	/**
	 * . <br/>
	 *
	 * @author Hongbin Yuan
	 * @param propName
	 * @return
	 */
	public static String getSetter(String propName){
		StringBuilder sb = new StringBuilder("set");
		if(!Pattern.matches(filterGetterRegex, propName)){
			return sb.append(StringUtil.upperFirstChar(propName)).toString();
		}
		return sb.append(StringUtil.upperFirstChar(propName)).toString();
	}
}

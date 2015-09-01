
/**
 * Project Name : Tools
 * File Name    : ClassUtil.java
 * Package Name : net.yuanmomo.tools.util.clazz
 * Created on   : 2014-3-14下午3:22:35
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.clazz;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Set;

import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : ClassUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-14 下午3:22:35 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ClassUtil {
	
	/**
	 * getClasses: 取得包下所有的class文件. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param packageName
	 * @return
	 * @since JDK 1.6
	 */
	public static Set<Class<? extends Class<?>>> getClasses(String packageName){
		if(StringUtil.isBlank(packageName)){
			throw new NullPointerException("package is null");
		}
		ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();
	    resolverUtil.find(new ResolverUtil.IsA(Object.class), packageName);
	    return resolverUtil.getClasses();
	}
	
	/**
	 * getClasses: 取得包下所有的class文件. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param packageName
	 * @return
	 * @since JDK 1.6
	 */
	public static Map<String, Class<? extends Class<?>>> getClassesMap(String packageName){
		if(StringUtil.isBlank(packageName)){
			throw new NullPointerException("package is null");
		}
		ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();
	    resolverUtil.find(new ResolverUtil.IsA(Object.class), packageName);
	    return resolverUtil.getClassesMap();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T>  getGnericClass (Object obj){
		return (Class<T>) ((ParameterizedType) obj.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[2];
	}
}

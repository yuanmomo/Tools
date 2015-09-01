
/**
 * Project Name : Tools
 * File Name    : TestClassUtil.java
 * Package Name : net.yuanmomo.tools.util.clazz
 * Created on   : 2014-3-14下午4:22:35
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.clazz;

import org.junit.Test;

/**
 * ClassName : TestClassUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-14 下午4:22:35 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TestClassUtil {
	@Test
	public void test(){
		ClassUtil.getClasses("net.yuanmomo.tools.json.plugin.spring.springmvc");
	}
}

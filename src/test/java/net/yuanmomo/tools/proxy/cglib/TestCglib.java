
/**
 * Project Name : Tools
 * File Name    : TestCglib.java
 * Package Name : net.yuanmomo.tools.proxy.cdlib
 * Created on   : 2014-2-28下午3:07:57
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.cglib;

import org.junit.Test;

/**
 * ClassName : TestCglib 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 下午3:07:57 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TestCglib {
	@Test
	public void test() {
		Chinese chin = Chinese.getAuthInstance();
		System.out.println(chin.sayHello("孙悟空"));
		chin.eat("西瓜");
		System.out.println(chin.getClass());
	}
}


/**
 * Project Name : Test
 * File Name    : HelloImpl.java
 * Package Name : net.yuanmomo.proxy.inter
 * Created on   : 2014-2-28上午11:16:36
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.jdk.impl;

import net.yuanmomo.tools.proxy.jdk.inter.Hello;

/**
 * ClassName : HelloImpl 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 上午11:16:36 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class HelloImpl implements Hello {

	/**
	 * sayHello:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @see net.yuanmomo.proxy.inter.Hello#sayHello()
	 */
	@Override
	public void sayHello() {
		System.out.println("Hello World!!");
	}

	/**
	 * sayHello:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param name
	 * @see net.yuanmomo.proxy.inter.Hello#sayHello(java.lang.String)
	 */
	@Override
	public void sayHello(String name) {
		System.out.println("Hello World!!" + name);
	}
}

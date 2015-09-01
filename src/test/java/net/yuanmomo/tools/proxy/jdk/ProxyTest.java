
/**
 * Project Name : Tools
 * File Name    : test.java
 * Package Name : net.yuanmomo.tools.proxy
 * Created on   : 2014-2-28下午1:53:13
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.jdk;

import net.yuanmomo.tools.proxy.jdk.impl.HelloImpl;
import net.yuanmomo.tools.proxy.jdk.inter.Hello;

import org.junit.Test;

/**
 * ClassName : test 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 下午1:53:13 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
/**
 * 动态代理测试类
 * @author zyb
 * @since 2012-8-9
 *
 */
public class ProxyTest {

	@Test
	public void testProxy() throws Throwable {
		// 实例化目标对象
		Hello hello = new HelloImpl();
		
		// 实例化InvocationHandler
		HelloInvocationHandler invocationHandler = new HelloInvocationHandler(hello);
		
		// 根据目标对象生成代理对象
		Hello proxy = (Hello) invocationHandler.getProxyInstance();
		
		// 调用代理对象的方法
		proxy.sayHello();
		proxy.sayHello("Yuan momo");
	}
	
	@Test
	public void testGenerateProxyClass() {
		ProxyGeneratorUtils.writeProxyClassToHardDisk("E:/$Proxy11.class");
	}
}

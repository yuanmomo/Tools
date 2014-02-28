
/**
 * Project Name : Test
 * File Name    : HelloInvocationHandler.java
 * Package Name : net.yuanmomo.proxy
 * Created on   : 2014-2-28上午11:17:39
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.yuanmomo.tools.proxy.inter.Hello;

/**
 * ClassName : HelloInvocationHandler 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 上午11:17:39 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class HelloInvocationHandler implements InvocationHandler {
	private Hello obj = null;
	/**
	 * Creates a new instance of HelloInvocationHandler.
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param obj
	 */
	public HelloInvocationHandler(Hello obj) {
		this.obj = obj;
	}
	/**
	 * invoke:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("I will say somthing.....");
		method.invoke(obj,args);
		System.out.println("Said done!!");
		return null;
	}
	
	public Hello getProxyInstance(){
		return (Hello)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				this.obj.getClass().getInterfaces(), this);
	}
}

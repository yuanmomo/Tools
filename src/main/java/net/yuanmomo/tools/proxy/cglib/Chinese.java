
/**
 * Project Name : Tools
 * File Name    : Chinese.java
 * Package Name : net.yuanmomo.tools.proxy.cglib
 * Created on   : 2014-2-28下午3:06:27
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.yuanmomo.tools.proxy.jdk.annotion.ParamCount;

/**
 * ClassName : Chinese 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 下午3:06:27 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class Chinese {
	// 实现 Person 接口的 sayHello() 方法
	@ParamCount(count = 1)
	public String sayHello(String name) {
		System.out.println(" sayHello");
		// 返回简单的字符串
		return name;
	}
	@ParamCount(count = 2)
	// 定义一个 eat() 方法
	public void eat(String food) {
		System.out.println("我正在吃 :" + food);
	}

	public static Chinese getAuthInstance() {
		Enhancer en = new Enhancer();
		// 设置要代理的目标类
		en.setSuperclass(Chinese.class);
		// 设置要代理的拦截器
		en.setCallback(new AroundAdvice());
		// 生成代理类的实例
		return (Chinese) en.create();
	}
}

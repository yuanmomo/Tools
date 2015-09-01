
/**
 * Project Name : Tools
 * File Name    : TEest.java
 * Package Name : net.yuanmomo.tools.proxy.cglib
 * Created on   : 2014-2-28下午2:58:50
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.yuanmomo.tools.proxy.jdk.annotion.ParamCount;

/**
 * ClassName : AroundAdvice 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-28 下午2:58:50 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class AroundAdvice  implements MethodInterceptor {

	/**
	 * intercept:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @return
	 * @throws Throwable
	 * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			net.sf.cglib.proxy.MethodProxy proxy) throws Throwable {
		ParamCount paramCount = method.getAnnotation(ParamCount.class);
		if(paramCount.count() <=1){
			System.out.println("执行目标方法之前，模拟开始事务 ...");
			// 执行目标方法，并保存目标方法执行后的返回值
			Object rvt = proxy.invokeSuper(obj, new String[]{"Yuanmomo"});
			System.out.println("执行目标方法之后，模拟结束事务 ...");
			return rvt;
		}else{
			return proxy.invokeSuper(obj, args);
		}
	}
}

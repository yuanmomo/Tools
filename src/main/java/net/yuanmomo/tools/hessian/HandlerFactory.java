
/**
 * Project Name : Tools
 * File Name    : HandlerFactory.java
 * Package Name : net.yuanmomo.tools.hessian
 * Created on   : 2014-5-16上午11:58:17
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.hessian;
/**
 * ClassName : HandlerFactory 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-5-16 上午11:58:17 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class HandlerFactory {
	private static HessianInvokeHandler handler = null;
	
	private HandlerFactory(){}
	
	public static HessianInvokeHandler getHessianInvokeHandler(){
		return handler;
	}
	public static void setHessianInvokeHandler(HessianInvokeHandler h){
		handler = h;
	}
}

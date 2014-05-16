
/**
 * Project Name : Tools
 * File Name    : HessianInterceptor.java
 * Package Name : net.yuanmomo.tools.hessian
 * Created on   : 2014-5-16上午11:10:03
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.hessian;
/**
 * ClassName : HessianInterceptor 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-5-16 上午11:10:03 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public interface HessianInvokeHandler {
	
	public long getStartCallTimestamp();
	public void log(Object result,Object service,String methodName,long startTimestamp,Object []values);
}

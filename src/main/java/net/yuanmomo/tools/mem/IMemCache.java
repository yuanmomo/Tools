
/**
 * Project Name : Tools
 * File Name    : IMemCache.java
 * Package Name : net.yuanmomo.tools.mem
 * Created on   : 2014-4-27下午11:16:12
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.mem;
/**
 * ClassName : IMemCache 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-27 下午11:16:12 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public interface IMemCache<T> {
	/**
	 * 刷新所有记录。
	 * @param e
	 */
	public boolean refresh();
	
	/**
	 *  刷新指定记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param obj
	 * @return
	 */
	public boolean refresh(T obj);
	
	/**
	 *  从数据库加载数据到内存. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 */
	public boolean load();
}

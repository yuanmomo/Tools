
/**
 * Project Name : Tools
 * File Name    : AbstractMemCache.java
 * Package Name : net.yuanmomo.tools.mem
 * Created on   : 2014-4-27下午11:50:07
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.mem;
/**
 * ClassName : AbstractMemCache 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-27 下午11:50:07 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public abstract class AbstractMemCache<T> implements IMemCache<T> {

	/**
	 *  数据是否已经加载.
	 */
	protected boolean isLoaded = false;
	
	@Override
	public boolean refresh() {
		return false;
	}

	@Override
	public boolean refresh(T obj) {
		return false;
	}

	@Override
	public boolean load() {
		return false;
	}

}

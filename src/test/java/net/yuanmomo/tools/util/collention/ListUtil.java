/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.util.collention
 * Created on   : 2013-12-24下午11:48:08
 * File Name    : ListUtil.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */
/**
 * Project Name : Tools
 * File Name    : ListUtil.java
 * Package Name : net.yuanmomo.tools.util.collention
 * Created on   : 2013-12-24下午11:48:08
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.collention;

import java.util.List;

/**
 * ClassName : ListUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-24 下午11:48:08 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ListUtil {
	/**
	 * isNull: 判断制定的集合为空. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isNull(List<?> list){
		if(list == null || list.size() == 0){
			return true;
		}
		return false;
	}
	/**
	 * isNotNull:  判断制定的集合不为空. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isNotNull(List<?> list){
		return !isNull(list);
	}
}

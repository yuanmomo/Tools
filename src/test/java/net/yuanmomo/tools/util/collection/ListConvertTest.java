/**
 * Project Name : Tools
 * File Name    : ListConvertTest.java
 * Package Name : net.yuanmomo.tools.util.collection
 * Created on   : 2013-12-30下午4:40:49
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.collection;

import java.util.ArrayList;
import java.util.List;

import net.yuanmomo.tools.util.collention.CollectionUtil;

import org.junit.Test;

/**
 * ClassName : ListConvertTest 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-30 下午4:40:49 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ListConvertTest {
	@Test
	public void testConvert() {
		List<String> intStrList = new ArrayList<String>();
		intStrList.add("1");
		intStrList.add("2");
		intStrList.add("3");
		intStrList.add("4");
		intStrList.add("5");
		intStrList.add("6");
		List<Integer> intList = CollectionUtil.convert(intStrList,Integer.class);
		for(Integer i: intList){
			System.out.println(i);
		}
	}

}

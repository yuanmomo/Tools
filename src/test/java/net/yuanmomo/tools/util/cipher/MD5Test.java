
/**
 * Project Name : Tools
 * File Name    : MD5Test.java
 * Package Name : net.yuanmomo.tools.util.cipher
 * Created on   : 2014-5-26下午2:06:46
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.cipher;

import net.yuanmomo.tools.util.string.StringUtil;

import org.junit.Test;

/**
 * ClassName : MD5Test 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-5-26 下午2:06:46 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class MD5Test {
	@Test
	public void test() throws Exception{
		System.out.println(MD5.getMD5(StringUtil.getRandomString(10)));
		System.out.println(MD5.getMD5(StringUtil.getRandomString(10)));
		System.out.println(MD5.getMD5(StringUtil.getRandomString(10)));
		System.out.println(MD5.getMD5(StringUtil.getRandomString(10)));
		System.out.println(MD5.getMD5(StringUtil.getRandomString(10)));
	}
}

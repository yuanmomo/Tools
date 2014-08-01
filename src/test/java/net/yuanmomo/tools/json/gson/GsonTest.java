
/**
 * Project Name : Tools
 * File Name    : GsonTest.java
 * Package Name : net.yuanmomo.tools.json.gson
 * Created on   : 2014-7-31下午9:12:25
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.json.gson;

import com.google.gson.Gson;

/**
 * ClassName : GsonTest 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-7-31 下午9:12:25 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class GsonTest {
	public static void main(String[] args) {
		A a = new A("1"+"\n" + "2");
		String str = new Gson().toJson(a);
		System.out.println(str);
		
		A b = new Gson().fromJson(str, A.class);
		System.out.println(b);
	}
}


class A{
	private String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	public A(String a) {
		super();
		this.a = a;
	}
}
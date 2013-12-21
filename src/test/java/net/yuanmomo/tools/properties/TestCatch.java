/** Copyright (c) 2013 MoMo, yuanhb@fusionskye.com All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.properties
 * Created on   : Jun 20, 20132:34:47 PM
 * File Name    : TestCatch.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.properties;
/**
 * ClassName : TestCatch 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 20, 2013 2:34:47 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class TestCatch {
	public static void main(String[] args) {
		try {
			System.out.println(fun()==null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static A fun() throws Exception{
		A a=new A();
		try {
			a.print();
			System.out.println("In try...a==null??"+(a==null));
			System.out.println(1/0);
		} catch (Exception e) {
			System.out.println("In catch...a=null");
			a=null;
			System.out.println("In catch...a==null??"+(a==null));
			throw e;
		}
		System.out.println("In fun...a==null??"+a==null);
		return a;
	}
}

class A{
	public void print(){
		System.out.println("A.print()......");
	}
}
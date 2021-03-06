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
 * Created on   : Jun 24, 20135:37:48 PM
 * File Name    : TestMD5.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.util.common;

import java.util.Random;

import net.yuanmomo.tools.util.cipher.MD5;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : TestMD5 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 24, 2013 5:37:48 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class MD5Test {
	private static Logger logger = LoggerFactory.getLogger(MD5Test.class);
	
	@Test
	public void testMD5() throws Exception{
		logger.debug("Start to test MD5Test.testMD5()....................");
		int totalRunTime=10;//一共运行多少次
		//生成的字符串最少的长度
		int strMinLength=32;
		//生成的字符串最大的长度
		int strMaxLength=64;
		//当前字符串的长度
		int currentStrLength=0;
		
		Random rand=new Random();
		for(int j=1;j<totalRunTime;j++){
			currentStrLength=rand.nextInt(strMaxLength-strMinLength);
			StringBuilder currentString=new StringBuilder();
			for(int i=0;i<currentStrLength;i++){
				currentString.append((char) (rand.nextInt(57)+65));
			}
			
			String current= currentString.toString();
			System.out.println("当前加密第 "+ j +" 个字符串 : "+current);
			if(!MD5.getMD5(current.getBytes()).equals(MD5.getMD5(current))){
				logger.info("两张方法加密出来的结果不一样，不能这样修改。。");
				logger.info("MD5.getMD5(bytes)："+ MD5.getMD5(current.getBytes()));
				logger.info("MD5.getMD5(String)："+MD5.getMD5(current));
				return;
			}
		}
		logger.info("测试结果完成，可以这样修改");
		logger.debug("Finish Testing MD5Test.testMD5()....................");
	}
}

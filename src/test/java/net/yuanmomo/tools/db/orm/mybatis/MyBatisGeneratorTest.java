/**
 * Project Name : Tools
 * File Name    : MyBatisGeneratorTest.java
 * Package Name : net.yuanmomo.tools.db.orm.mybatis
 * Created on   : 2013-12-24下午5:13:58
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.db.orm.mybatis;

import net.yuanmomo.tools.db.orm.mybatis.generator.MyBatisGeneratorTool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : MyBatisGeneratorTest 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-24 下午5:13:58 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class MyBatisGeneratorTest {
	private static Logger logger = LoggerFactory.getLogger(MyBatisGeneratorTest.class);
	
	 @Test
	 public void testGenerator(){
		 logger.debug("Start to test MyBatisGeneratorTest.testGenerator()....................");
		 MyBatisGeneratorTool.generate("src\\main\\resources\\net\\yuanmomo\\tools\\db\\orm\\mybatis\\generatorConfig.xml");
		 logger.debug("Finish Testing MyBatisGeneratorTest.testGenerator()....................");
	 }
}

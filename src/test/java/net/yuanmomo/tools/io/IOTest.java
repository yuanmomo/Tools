/**
 * 
 */
package net.yuanmomo.tools.io;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class IOTest {
	private static Logger logger = LoggerFactory.getLogger(IOTest.class);
	@Test
	public void testWrite(){
		logger.debug("Start to test IOTest.testWrite()....................");
		List<String> test=new ArrayList<String>();
		test.add("yuanmomo1");
		test.add("yuanmomo2");
		test.add("yuanmomo3");
		test.add("yuanmomo4");
		try {
			Write.output("out_put.txt", test);
		} catch (Exception e) {
			logger.error("Error",e);
		}
		logger.debug("Finish Testing IOTest.testWrite()....................");
	}
	@Test
	public void testRead(){
		logger.debug("Start to test IOTest.testRead()....................");
		try {
			List<String> list = Read.fileToList("out_put.txt");
			if(list != null){
				for(String line : list){
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Finish Testing IOTest.testRead()....................");
	}
}

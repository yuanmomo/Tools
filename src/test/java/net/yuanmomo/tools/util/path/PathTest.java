/**
 * 
 */
package net.yuanmomo.tools.util.path;

import java.net.URI;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MoMo
 *
 */
public class PathTest {
	private static Logger logger = LoggerFactory.getLogger(PathTest.class);
	
	@Test
	public void testURIFormat(){
		logger.debug("Start to test PathTest.testURIFormat()....................");
		try {
			String path=this.getClass().getClassLoader().getResource("net/yuanmomo/tools/util/properties/bean.properties").toURI().toString();
			logger.info(String.valueOf(path.equals(new URI(path).toString())));
			logger.info(new URI(path).toString());
		} catch (Exception e) {
			logger.error("Error",e);
		}
		logger.debug("Finish Testing PathTest.testURIFormat()....................");
	}
}

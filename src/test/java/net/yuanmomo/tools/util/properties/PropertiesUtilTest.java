/**
 * 
 */
package net.yuanmomo.tools.util.properties;

import java.net.URISyntaxException;

import net.yuanmomo.tools.db.ConnectionTest;
import net.yuanmomo.tools.util.properties.bean.Bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class PropertiesUtilTest {
	private static Logger logger = LoggerFactory.getLogger(ConnectionTest.class);
	
	@Test
	public void testPropertiesToBean(){
		logger.debug("Start to test ConnectionTest.testFetchBoneCPConnection()....................");
		try {
			logger.info(PropertiesUtil.propertiesToBean(Bean.class, "src\\test\\resources\\net\\yuanmomo\\tools\\util\\properties\\bean.properties").toString());
			logger.info(PropertiesUtil.propertiesToBean(Bean.class, this.getClass().getClassLoader().getResource("net/yuanmomo/tools/util/properties/bean.properties").toURI()).toString());
		} catch (URISyntaxException e) {
			logger.error("Error",e);
		} catch (Exception e) {
			logger.error("Error",e);
		}
		logger.debug("Finish Testing ConnectionTest.testFetchBoneCPConnection()....................");
	}
}

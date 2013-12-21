/**
 * 
 */
package net.yuanmomo.tools.properties;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.yuanmomo.tools.io.Write;
import net.yuanmomo.tools.properties.bean.Bean;

import org.junit.Test;

/**
 * @author MoMo
 *
 */
public class URITest {
	
	@Test
	public void testURIFormat(){
		try {
			//file:/F:/Company/Workspace/Tools/target/classes/net/yuanmomo/tools/properties/bean.properties
			//file:/F:/Company/Workspace/Tools/target/classes/net/yuanmomo/tools/properties/bean.properties
			String path=this.getClass().getClassLoader().getResource("net/yuanmomo/tools/properties/bean.properties").toURI().toString();
			System.out.println(path.equals(new URI(path).toString()));
			System.out.println(new URI(path).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToBean(){
		try {
			System.out.println(PropertiesUtil.propertiesToBean(Bean.class, "D:\\Program Files\\bean.properties"));
			System.out.println(PropertiesUtil.propertiesToBean(Bean.class, this.getClass().getClassLoader().getResource("net/yuanmomo/tools/properties/bean.properties").toURI()));
//			Properties pro=new ToBean<Bean>()
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testOutput(){
		List<String> test=new ArrayList<String>();
		test.add("yuanmomo1");
		test.add("yuanmomo2");
		test.add("yuanmomo3");
		test.add("yuanmomo4");
		Write<String> out=new Write<String>();
		try {
			out.output("z:\\s_event.sql", test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

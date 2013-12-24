/**
 * 
 */
package net.yuanmomo.tools.util.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.yuanmomo.tools.util.path.PathUtil;
import net.yuanmomo.tools.util.string.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MoMo
 *
 */
/**
 * @author MoMo
 *
 * @param <T>
 */
/**
 *
 */
public class PropertiesUtil {
	private static Logger logger=LoggerFactory.getLogger(PropertiesUtil.class);
	
	/**
	 * propertiesToMap: 讲一个properties转换成一个map对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param prop
	 * @return
	 * @since JDK 1.6
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static<T> Map<String,String> propertiesToMap(Properties prop){
		logger.debug("Convert the Properties Object back to a Map Object");
		return new HashMap<String, String>((Map) prop);
	}
	
	/**
	 * propertiesToMap: 将一个properties文件加载为map. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static<T> Map<String,String> propertiesToMap(String filePath) throws Exception{
		Properties prop = PropertiesUtil.getProperties(filePath);
		return propertiesToMap(prop);
	}
	
	/**
	 * propertiesToMap: 将一个properties文件加载为map. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param uri
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static<T> Map<String,String> propertiesToMap(URI uri) throws Exception{
		Properties prop = PropertiesUtil.getProperties(uri);
		return propertiesToMap(prop);
	}
	
	/**
	 * mapToBean: 将一个map的value设置到一个指定的bean对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param cla
	 * @param map
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static<T> T mapToBean(Class<T> cla, Map<String,String> map) throws Exception{
		logger.debug("Start to set the value to bean ("+cla+") from map......");
		T bean = null;
		if (map == null) {
			return bean;
		}
		if (bean == null) {
			try {
				bean = cla.newInstance();
			} catch (InstantiationException e) {
				throw e;
			} catch (IllegalAccessException e) {
				throw e;
			}
		}
		Iterator<String> keyIte = map.keySet().iterator();
		while (keyIte.hasNext()) {
			try {
				String key = (String) keyIte.next();
				String value=map.get(key);
				if(value==null || "".equals(value)){
					continue;
				}
				Field field = cla.getDeclaredField(key);
				Class<?> fieldClass = field.getType();
				Method method = cla.getDeclaredMethod("set"
						+ StringUtil.upperFirstChar(key), fieldClass);
				String fieldType= fieldClass.getSimpleName();
				logger.debug("Set value = "+ value+ " to property="+field.getName());
				if ("String".equals(fieldType)) {
					method.invoke(bean, String.valueOf(value));
				} else if("int".equals(fieldType) || "Integer".equals(fieldType)) {
					method.invoke(bean, Integer.parseInt(value));
				} else if ("long".equals(fieldType) || "Long".equals(fieldType)) {
					method.invoke(bean, Long.parseLong(value));
				} else if("boolean".equals(fieldType) || "Boolean".equals(fieldType)) {
					method.invoke(bean, Boolean.parseBoolean(value));
				} else if("char".equals(fieldType) || "Character".equals(fieldType)) {
					method.invoke(bean,value.charAt(0));
				} else if ("byte".equals(fieldType) || "Byte".equals(fieldType)) {
					method.invoke(bean, Byte.parseByte(value));
				} else if ("short".equals(fieldType) || "Short".equals(fieldType)) {
					method.invoke(bean, Short.parseShort(value));
				} else if ("float".equals(fieldType) || "Float".equals(fieldType)) {
					method.invoke(bean, Float.parseFloat(value));
				} else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
					method.invoke(bean, Double.parseDouble((String) value));
				} 
			} catch (NoSuchFieldException ex) {
				continue;
			} catch (Exception ex) {
				throw ex;
			}
		}
		return bean;
	}
	
	/**
	 * propertiesToBean: 将properties对象封装到一个bean对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param cla
	 * @param prop
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static<T> T propertiesToBean(Class<T> cla, Properties prop) throws Exception{
		Map<String, String> map = propertiesToMap(prop);
		return mapToBean(cla, map);
	}
	
	/**
	 * propertiesToBean:	自动将properties文件中的值设置到制定的对象. <br/>
	 * 						properties中的key和T对象的属性名 需要一致. <br/>
	 * @author MoMo
	 * @param cla         T对象的class
	 * @param uri		  properties文件的路径，相对路径，或者绝对路径都可
	 * @return   T        返回自动封后的 T 对象实例
	 * @throws Exception  可能抛出FileNotFoundException 或者 IOException
	 * @since JDK 1.6
	 */
	public static<T> T propertiesToBean(Class<T> cla, String propertiesFilePath) throws Exception {
		URI uri=PathUtil.getURIFromPath(propertiesFilePath);
		if(uri!=null){
			return propertiesToBean(cla,uri);
		}
		throw new Exception("this path cannot be casted to uri.");
	}
	
	
	/**
	 * propertiesToBean:	自动将properties文件中的值设置到制定的对象. <br/>
	 * 						properties中的key和T对象的属性名 需要一致. <br/>
	 * @author MoMo
	 * @param cla         T对象的class
	 * @param uri		  properties文件的uri路径
	 * @return   T        返回自动封后的 T 对象实例
	 * @throws Exception  可能抛出FileNotFoundException 或者 IOException
	 * @since JDK 1.6
	 */
	public static<T> T propertiesToBean(Class<T> cla, URI uri) throws Exception{
		Properties prop=getProperties(uri);
		if(prop!=null){
			return propertiesToBean(cla, prop);
		}
		throw new Exception("Cannot load the properties file.");
	}
	
	/**
	 * getProperties:	根据制定的文件路径得到相应的properties对象. <br/>
	 * 					相对路径，绝对路径都可以. <br/>
	 *
	 * @author MoMo
	 * @param path		properties文件的相对路径或者觉绝对路径
	 * 					如果该路径为null或者空串，使用默认的src路径下的config.properties
	 * @return			生成的properties对象
	 * @since			JDK 1.6
	 */
	public static Properties getProperties(String path) throws Exception{
		logger.debug("Current executing directory is " + new File(".").getAbsolutePath());
		if(path==null || "".equals(path)){
			logger.error("No path of properties file specified.");
			throw new Exception("No path of properties file specified.");
		}
		URI uri=PathUtil.getURIFromPath(path);
		if(uri!=null){
			return getProperties(uri);
		}
		throw new Exception("this path cannot be casted to uri.");
	}
	
	/**
	 * getProperties:	根据制定的URI路径得到相应的properties对象. <br/>
	 *
	 * @author MoMo
	 * @param uri				properties文件的URI
	 * @return					生成的properties对象
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static Properties getProperties(URI uri) throws Exception{
		logger.debug("Current executing directory is " + new File(".").getAbsolutePath());
		logger.debug("start to read the properties file...."+uri);
		Properties pro = new Properties();
		BufferedReader configReader = null;
		try {
			configReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(uri))));
			pro.load(configReader);
		} catch (FileNotFoundException e) {
			logger.error("Connot find the properties file, Please check whether the propertis file exists"+uri);
			throw e;
		} catch (IOException e) {
			
			throw e;
		}finally{
			try {
				configReader.close();
			} catch (IOException e) {
				throw e;
			}
		}
		logger.debug("Finish reading the properties file....");
		return pro;	
	}
}

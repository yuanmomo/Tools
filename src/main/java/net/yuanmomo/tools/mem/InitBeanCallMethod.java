
/**
 * Project Name : Tools
 * File Name    : PBSTrackManagerPostProcessor.java
 * Package Name : net.yuanmomo.tools.mem
 * Created on   : 2014-4-28下午3:32:25
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.mem;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;

/**
 * ClassName : PBSTrackManagerPostProcessor 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-28 下午3:32:25 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class InitBeanCallMethod implements BeanPostProcessor {
	private static Logger log = LoggerFactory.getLogger(InitBeanCallMethod.class);
	
	/**
	 *  初始化的类型.
	 */
	private List<Class<?>> implClassList;
	
	/**
	 *  该对象实现的接口.
	 */
	private Class<?> superInterface;
	
	/**
	 *  调用该接口的方法.
	 */
	private String methodName;
	
	@Override
	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
		if(implClassList != null && implClassList.contains(obj.getClass())){
			// 判断该实现累是否实现了IMemCache接口
			Class<?>[] allImplInterfaces = ClassUtils.getAllInterfaces(obj);
			if(Arrays.asList(allImplInterfaces).contains(superInterface)){ // 该实现类实现了制定接口
				try {
					MethodUtils.invokeMethod(obj,methodName,null);
					if(log.isDebugEnabled()){
						log.debug("调用"+obj.getClass()+"类的"+methodName+"方法，加载数据到内存");
					}
				} catch (NoSuchMethodException | IllegalAccessException
						| InvocationTargetException e) {
					if(log.isErrorEnabled()){
						log.error(e.getMessage());
					}
				}
			}
		}
		return obj;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	public List<Class<?>> getImplClassList() {
		return implClassList;
	}

	public void setImplClassList(List<Class<?>> implClassList) {
		this.implClassList = implClassList;
	}

	public Class<?> getSuperInterface() {
		return superInterface;
	}

	public void setSuperInterface(Class<?> superInterface) {
		this.superInterface = superInterface;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}

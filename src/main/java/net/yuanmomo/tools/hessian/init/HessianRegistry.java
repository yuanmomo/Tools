
/**
 * Project Name : azq-manager
 * File Name    : HessianRegistry.java
 * Package Name : com.azq.manager.hessian
 * Created on   : 2014-3-19下午5:30:15
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.hessian.init;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.yuanmomo.tools.util.bean.BeanUtil;

import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * ClassName : HessianRegistry 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-19 下午5:30:15 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class HessianRegistry {
	private static Logger logger = LoggerFactory.getLogger(HessianRegistry.class);
	
	private String hessianServerUrl=null;
	
	private String hessianProxyPackage = null;
	
	private static HessianProxyFactory hessianProxyFactory = null;

	
	public HessianRegistry(String hessianServerUrl, String hessianProxyPackage) {
		// hessian的设置
		hessianProxyFactory = new HessianProxyFactory();
		hessianProxyFactory.setOverloadEnabled(true); 
		hessianProxyFactory.setDebug(true);
		
		// hessian服务器路径和接口配置
		this.hessianServerUrl = hessianServerUrl;
		this.hessianProxyPackage = hessianProxyPackage;
		
		// 实例化所有的接口
		try {
			addProxy(hessianProxyPackage);
		} catch (MalformedURLException e) {
			if(logger.isErrorEnabled()){
				logger.error("Init hessian proxy error",e);
			}
		}
	}


	private final Map<Class<?>, Object> knownProxys = new HashMap<Class<?>, Object>();

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> type) {
		final Object proxy =  knownProxys
				.get(type);
		if (proxy == null)
			throw new RuntimeException("Type " + type
					+ " is not known to the HessianRegistry.");
		try {
			return (T)knownProxys.get(type);
		} catch (Exception e) {
			throw new RuntimeException("Error getting hessian proxy instance. Cause: "
					+ e, e);
		}
	}

	public <T> boolean hasProxy(Class<T> type) {
		return knownProxys.containsKey(type);
	}

	@SuppressWarnings("unchecked")
	public <T> void addProxy(Class<T> type) throws MalformedURLException {
		if (type.isInterface()) {
			if (hasProxy(type)) {
				throw new RuntimeException("Type " + type
						+ " is already known to the HessianRegistry.");
			}
			// 取得接口代理的path
			String path= hessianServerUrl + type.getAnnotation(Path.class).value();
			T proxy = (T) hessianProxyFactory.create(type, path);
			knownProxys.put(type, proxy);
			BeanUtil.register(type, proxy);
		}
	}


	/**
	 * @throws MalformedURLException 
	 * @throws Exception
	 * @since 3.2.2
	 */
	public void addProxy(String packageName, Class<?> superType) throws MalformedURLException{
		ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();
		resolverUtil.find(new ResolverUtil.IsA(superType), packageName);
		Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses();
		for (Class<?> mapperClass : mapperSet) {
			addProxy(mapperClass);
		}
	}

	/**
	 * @throws MalformedURLException 
	 * @throws Exception 
	 * @since 3.2.2
	 */
	public void addProxy(String packageName) throws MalformedURLException {
		addProxy(packageName, Object.class);
	}

	/**
	 * hessianServerUrl.
	 *
	 * @return  the hessianServerUrl
	 * @since   JDK 1.6
	 */
	public String getHessianServerUrl() {
		return hessianServerUrl;
	}

	/**
	 * hessianServerUrl.
	 *
	 * @param   hessianServerUrl    the hessianServerUrl to set
	 * @since   JDK 1.6
	 */
	public void setHessianServerUrl(String hessianServerUrl) {
		this.hessianServerUrl = hessianServerUrl;
	}

	/**
	 * hessianProxyPackage.
	 *
	 * @return  the hessianProxyPackage
	 * @since   JDK 1.6
	 */
	public String getHessianProxyPackage() {
		return hessianProxyPackage;
	}

	/**
	 * hessianProxyPackage.
	 *
	 * @param   hessianProxyPackage    the hessianProxyPackage to set
	 * @since   JDK 1.6
	 */
	public void setHessianProxyPackage(String hessianProxyPackage) {
		this.hessianProxyPackage = hessianProxyPackage;
	}
}

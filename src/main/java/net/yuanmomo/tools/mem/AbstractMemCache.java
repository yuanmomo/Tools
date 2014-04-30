
/**
 * Project Name : Tools
 * File Name    : AbstractMemCache.java
 * Package Name : net.yuanmomo.tools.mem
 * Created on   : 2014-4-27下午11:50:07
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.mem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.yuanmomo.tools.util.clazz.ClassUtil;
import net.yuanmomo.tools.util.clazz.MethodUtil;
import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.string.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;


/**
 * ClassName : AbstractMemCache 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-27 下午11:50:07 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public abstract class AbstractMemCache<K1,K2,T> implements IMemCache<K1,K2,T> {
	private static Logger log = LoggerFactory.getLogger(AbstractMemCache.class);
	
	/**
	 *  数据是否已经加载.
	 */
	protected boolean isLoaded = false;
	
	/**
	 *  存储数据的map.
	 */
	protected Map<K1,T> dataMap = null;
	
	/**
	 *  当前泛型类中的作为map.key的属性的getter方法.
	 */
	protected Method k1GetterMethod = null;
	
	/**
	 *  将数据按照指定的类型分组存放.
	 */
	protected Map<K2,Map<K1,T>> dataCategoryMap = null;

	/**
	 *  当前泛型类中的作为分类存放的map.key的属性的getter方法.
	 */
	protected Method k2GetterMethod = null;
	
	public AbstractMemCache(String k1) throws NoSuchFieldException {
		if(StringUtil.isNotBlank(k1)){
			Class<T> clazz = ClassUtil.getGnericClass(this);
			k1GetterMethod = ClassUtils.getMethodIfAvailable(clazz, MethodUtil.getGetter(k1));
		}
	}
	public AbstractMemCache(String k1, String k2) throws NoSuchFieldException {
		if(StringUtil.isNotBlank(k1)){
			Class<T> clazz = ClassUtil.getGnericClass(this);
			k1GetterMethod = ClassUtils.getMethodIfAvailable(clazz, MethodUtil.getGetter(k1));
			k2GetterMethod = ClassUtils.getMethodIfAvailable(clazz, MethodUtil.getGetter(k2));
		}
	}

	@Override
	public boolean init() throws Exception{
		this.beforeInit();		// 初始化数据前的回调函数
		if(this.isLoaded){ 		// 数据已经加载过了，返回
			return true;
		}
		if(this.k1GetterMethod == null){
			if(log.isErrorEnabled()){
				log.error("加载数据库对象到内存，未指定map的key; class = "+ ClassUtil.getGnericClass(this));
			}
			return false;
		}
		List<T> list = this.load(); // 从数据库加载数据
		if(CollectionUtil.isNull(list)){
			return true;
		}
		
		// 数据库加载数据不为空，存放到内存
		this.dataMap = new ConcurrentHashMap<K1,T>();
		if(this.k2GetterMethod != null){ // 判断是否需要分类存放数据
			this.dataCategoryMap = new ConcurrentHashMap<K2,Map<K1,T>>();
		}
		for(T t : list){	// 循环处理加载的数据
			this.add(t);
		}
		this.afterInit(); // 调用初始化成功后的回调函数
		this.isLoaded = true;
		return true;
	}
	@Override
	public void beforeInit() {}
	@Override
	public void afterInit() {
		
	}
	
	@Override
	public boolean refresh() throws Exception {
		// 刷新前的自定义操作
		this.beforeRefresh();				
		// 设置数据未被加载
		this.isLoaded = false;
		// 清空存放数据的map
		this.dataMap = null;	
		this.dataCategoryMap = null;
		// 从数据库重新加载渠道数据
		boolean flag = this.init();						
		// 刷新后的自定义操作
		this.afterRefresh();				
		return flag;
	}

	@Override
	public void beforeRefresh()  throws Exception{}
	
	@Override
	public void afterRefresh() throws Exception {}
	
	@Override
	public T handle(T t) {
		return t;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(t != null){
			// 对该条记录进行处理
			t = this.handle(t);  
			// 取得该记录的主键值
			K1 k1 = (K1) this.k1GetterMethod.invoke(t);
			// 存放到数据map
			this.dataMap.put(k1, t);
			if(this.k2GetterMethod != null){
				K2 k2 =(K2) this.k2GetterMethod.invoke(t);
				Map<K1,T> typeList = this.dataCategoryMap.get(k2); // 获取当前类型的存放列表
				if(typeList == null){ // 初始化同类型列表
					typeList = new ConcurrentHashMap<K1,T>();
					dataCategoryMap.put(k2, typeList);
				}
				typeList.put(k1, t);
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(t != null){
			K1 k1 = (K1) this.k1GetterMethod.invoke(t);
			this.dataMap.remove(k1);
			if(this.k2GetterMethod != null){
				K2 k2 =(K2) this.k2GetterMethod.invoke(t);
				Map<K1,T> typeList = this.dataCategoryMap.get(k2);
				if(typeList != null){
					typeList.remove(k1); // 删除
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean update(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(t != null){
			return this.add(t);		// 刷新到内存
		}
		return false;
	}
	
	@Override
	public T get(K1 key)  throws Exception{
		if(this.isLoaded){ // 内存已经加载过了，从内存获取
			return this.dataMap.get(key);
		}
		return null;
	}
	
	public Map<K1,T> selectTypeList(K2 key)  throws Exception{
		if(this.isLoaded){ // 内存已经加载过了，从内存获取
			return this.dataCategoryMap.get(key);
		}
		return null;
	}
}
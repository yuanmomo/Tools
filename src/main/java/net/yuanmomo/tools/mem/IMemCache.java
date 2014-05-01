
/**
 * Project Name : Tools
 * File Name    : IMemCache.java
 * Package Name : net.yuanmomo.tools.mem
 * Created on   : 2014-4-27下午11:16:12
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.mem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * ClassName : IMemCache 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-27 下午11:16:12 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public interface IMemCache<K1,K2,T> {
	
	/**
	 *  从数据库加载数据. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 */
	public List<T> load() throws Exception;
	
	/**
	 *  初始化加载数据到内存. <br/>
	 *
	 * @author Hongbin Yuan
	 * @return
	 * @throws Exception 
	 */
	public boolean init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception; 
	
	/**
	 *  初始化数据前执行的方法. <br/>
	 *
	 * @author Hongbin Yuan
	 */
	public void beforeInit() throws Exception;
	/**
	 *  初始化成功后执行的方法.. <br/>
	 *
	 * @author Hongbin Yuan
	 */
	public void afterInit() throws Exception;
	
	/**
	 * 刷新所有记录。
	 * @param e
	 * @throws Exception 
	 */
	public boolean refresh()  throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception ;
	
	/**
	 *  整体刷新数据前执行的方法. <br/>
	 *
	 * @author Hongbin Yuan
	 */
	public void beforeRefresh() throws Exception;
	/**
	 *  整体刷新数据成功后执行的方法.. <br/>
	 *
	 * @author Hongbin Yuan
	 */
	public void afterRefresh() throws Exception;
	
	/**
	 *  添加一条记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param t
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public boolean add(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	/**
	 *  添加一条记录到内存时，对记录进行处理. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param t
	 * @return
	 */
	public T handle(T t) ;
	
	/**
	 *  更新一条记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param t
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public boolean update(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	/**
	 *  删除一条记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param t
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public boolean delete(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	/**
	 *  取得一条记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param key
	 * @return
	 */
	public T get(K1 key) throws Exception;
	
	/**
	 *  批量取得记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Map<K1,T> selectTypeList(K2 key)  throws Exception;
	/**
	 *  批量取得记录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Map<K1,T> selectTypeList(List<K2> keys)  throws Exception;
}

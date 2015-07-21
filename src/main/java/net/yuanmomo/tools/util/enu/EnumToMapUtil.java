/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package net.yuanmomo.tools.util.enu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * 枚举类转换为Map结构操作。
 * 
 * @author fish 2015/7/15 13:20
 */
public class EnumToMapUtil {
	/**  
	 *	将指定的{@link Class}类转换为一个{@link Map}对象。
	 *
	 * @param enumObj	转换的枚举，必须包含getType方法和getDescription方法。
	 * @return	返回一个{@link Map}对象，key是getType取得的值，value是getDescription方法返回的值。
	 */
	public static Map<Integer, String> toMap(Class<? extends Enum<?>> clazz ) throws Exception{ 
        return toMap(clazz,"getType","getDescription");
    }

	/**  
	 *	将指定的枚举类转换为一个{@link Map}对象。
	 *
	 * @param enumObj				转换的枚举类型，该类型必须包含一个int的编号和一个String的描述信息。
	 * @param getKeyMethodName		取得编号int的方法。
	 * @param getValueMethodName	取得描述信息String的方法。
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, String> toMap(
			Class<? extends Enum<?>> clazz , 
			String getKeyMethodName,
			String getValueMethodName) throws Exception{ 
        Map<Integer, String> map = new TreeMap<Integer, String>(); 
        try { 
        	Method toCode = clazz.getMethod(getKeyMethodName); 
        	Method toName = clazz.getMethod(getValueMethodName); 
            //得到enum的所有实例 
            Object[] objs = clazz.getEnumConstants(); 
            for (Object obj : objs) {
            	if(toCode.invoke(obj) instanceof Integer 
            			&& toName.invoke(obj) instanceof String){
            		map.put((Integer)toCode.invoke(obj),
            				(String)toName.invoke(obj)); 
            	}
            } 
            return map;
        } catch (NoSuchMethodException e) { 
            throw e;
        } catch (InvocationTargetException e) { 
        	throw e;
        } catch (IllegalAccessException e) { 
        	throw e;
        } 
	}
}

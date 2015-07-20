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
package net.yuanmomo.tools.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.string.StringUtil;

/**
 * <p>
 * 	指定一个Class，new 一个对象，生成所有set值的代码。
 * </p>
 *
 * @author dell
 * @date 2015年7月20日 下午4:47:41
 */

public class BeanCreateUtil {
	
	/**  
	 *	生成该类的set代码。
	 *
	 * @param clazzArray	目标类数组。
	 * @return
	 * @throws Exception
	 */
	public static String generate (Class<?> ... clazzArray) throws Exception{
		if(CollectionUtil.isNull(clazzArray)){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(Class<?> clazz : clazzArray){
			String className = clazz.getSimpleName();
			
			sb.append("\t\t").append(className).append(" ").append(StringUtil.lowerFirstChar(className));
			sb.append(" = new ").append(className).append("();");
			
			Field[] fields = clazz.getDeclaredFields();
			if(CollectionUtil.isNull(fields)){
				return sb.toString();
			}
			for(Field f : fields){
				f.setAccessible(true);
				// 判断是否有该属性的set方法
				String setName = "set" + StringUtil.upperFirstChar(f.getName());
				Method m = clazz.getMethod(setName, f.getType());
				if( m != null){
					sb.append("\n\t\t").append(StringUtil.lowerFirstChar(className));
					sb.append(".").append(setName).append("(").append(f.getName()).append(");");
				}
			}
		}
		return  sb.toString();
	}
}

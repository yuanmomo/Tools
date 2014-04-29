
/**
 * Project Name : Tools
 * File Name    : BeanConverter.java
 * Package Name : net.yuanmomo.tools.util.bean
 * Created on   : 2014-3-14下午3:30:31
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.yuanmomo.tools.util.clazz.ClassUtil;
import net.yuanmomo.tools.util.clazz.MethodUtil;
import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : BeanConverter 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-14 下午3:30:31 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class BeanConverterUtil {
	private List<String> notConverteField = null;
	
	public BeanConverterUtil(){
		notConverteField = new ArrayList<>();
	}
	
	public void addNotConverteField(String field){
		if(StringUtil.isBlank(field)){
			return;
		}
		notConverteField.add(field);
	}
	
	/**
	 * generate:  生成bean类的适配器转换代码. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param sourcePkg		源包
	 * @param targetPkg		目标包
	 * @param methodType	生成的方法名  getXXXXbean
	 * @param sb			存放生成的源代码
	 * @since JDK 1.6
	 */
	public void generate(String sourcePkg,String targetPkg, String methodType,StringBuilder sb){
		// 取得source package下面所有的类
		Map<String,Class<? extends Class<?>>> sourceClasses = ClassUtil.getClassesMap(sourcePkg);
		if(CollectionUtil.isNull(sourceClasses)){
			return;
		}
		
		Map<String,Class<? extends Class<?>>> targetClasses = ClassUtil.getClassesMap(targetPkg);
		if(CollectionUtil.isNull(sourceClasses)){
			return;
		}
		
		Set<String> sourceKeySet = sourceClasses.keySet();
		for(String sourceClassName : sourceKeySet){
			Class<?> targetClazz = targetClasses.get(sourceClassName);
			if(targetClazz == null){ // target 包中不包含当前类型
				continue;
			}
			Class<?> sourceClazz = sourceClasses.get(sourceClassName);
			this.generate(sourceClazz,targetClazz,methodType,sb);
		}
	}
	
	
	/**
	 * generate: 生成bean类的适配器转换代码. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param source		源类
	 * @param target		目标类
	 * @param methodType	生成的方法名  getXXXXbean
	 * @param sb			存放生成的源代码
	 * @since JDK 1.6
	 */
	public void generate(Class<?> source, Class<?> target, String methodType,StringBuilder sb){
		if(sb == null){
			sb = new StringBuilder();
		}
		
		// 取得当前源类包名和类名
		String sourcePackageClassName = source.getName();
		String sourceClassName = source.getSimpleName();
		// 取得当前目标类包名和类名
		String targetPackageClassName = target.getName();
		String targetClassName = target.getSimpleName();
		
		// 生成的方法内的变量名
		String oldObjectName = StringUtil.lowerFirstChar(sourceClassName);
		String newObjectName = " new"+targetClassName;
		
		sb.append(" public static " ).append(targetPackageClassName).append(" get").append(methodType).append(targetClassName)
			.append("("+sourcePackageClassName).append(" ").append(oldObjectName).append("){\n");
		sb.append("if(").append(oldObjectName).append(" == null){\n").append("return null;\n").append("}\n");
		sb.append(targetPackageClassName).append(newObjectName).append(" = new ")
		.append(targetPackageClassName).append("();\n");
		
		// 取得源类的所有属性
		Field[] sourceFields = source.getDeclaredFields();
		// 取得目标类的所有属性
		Field[] targetFields = target.getDeclaredFields();
		
		// 判断属性的格式
		for(Field tf : targetFields){
			if(notConverteField.contains(tf.getName())){
				continue; // 不转换的属性
			}
			String targetFieldName = tf.getName();
			String sourceFieldName = null;
			
			boolean isSourceFieldExist = false; // 源类中是否存在该属性
			for(Field sf : sourceFields){
				if(sf.getName().equalsIgnoreCase(targetFieldName)){
					isSourceFieldExist = true;
					sourceFieldName = sf.getName();
					break;
				}
			}
			if(isSourceFieldExist){ // 源类中存在该属性
				targetFieldName = MethodUtil.getSetter(targetFieldName);
				sourceFieldName = MethodUtil.getGetter(sourceFieldName);
				
				sb.append(newObjectName).append(".").append(targetFieldName)
				.append("(").append(oldObjectName).append(".").append(sourceFieldName).append("()")
				.append(");\n");
			}
		}
		
		sb.append("return ").append(newObjectName).append(";\n");
		sb.append("}\n");
	}
}

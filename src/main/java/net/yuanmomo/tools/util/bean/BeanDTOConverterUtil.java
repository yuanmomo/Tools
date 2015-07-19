
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
import java.util.Arrays;
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
public class BeanDTOConverterUtil {
	private List<String> notConverteField = new ArrayList<>();

	 /**
	  *		生成 bean 类和 DTO bean类之间互相转换的代码。
	  *
	  * @param bean								 一般为数据库类。
	  * @param beanDTO						一个 DTO 类。
	  * @param toDTOMethodName		从bean 转换到 DTO 时的方法前缀名称。
	  * @param fromDTOMethodName	从 DTO 转换到 bean 类的方法前缀名称。
	  * @return
	  */
	 public  String generate(Class<?> bean, Class<?> beanDTO, String toDTOMethodName, String fromDTOMethodName){
		 StringBuilder sb = new StringBuilder();
		 // 生成bean 到 DTO 的转换
		 sb.append(generate(bean, beanDTO, toDTOMethodName));
		 // 生成 DTO 到 bean 的转换
		 sb.append(generate(beanDTO, bean, fromDTOMethodName));
		 return sb.toString();
	 }

	 /**
	  *		生成 bean 类和 DTO bean类之间互相转换的代码。
	  *
	  * @param bean								 一般为数据库类。
	  * @param beanDTO						一个 DTO 类。
	  * @param toDTOMethodName		从bean 转换到 DTO 时的方法前缀名称。
	  * @param fromDTOMethodName	从 DTO 转换到 bean 类的方法前缀名称。
	  * @param notConverteField			一个字符串数组，表示不生成该字段的转换代码。
	  * @return
	  */
	 public   String generate(Class<?> bean, Class<?> beanDTO, String toDTOMethodName, String fromDTOMethodName, String ... notConverteField){
		 if(CollectionUtil.isNotNull(notConverteField)){
			this.notConverteField = Arrays.asList(notConverteField);
		 }

		 StringBuilder sb = new StringBuilder();
		 // 生成bean 到 DTO 的转换
		 sb.append(generate(bean, beanDTO, toDTOMethodName));
		 // 生成 DTO 到 bean 的转换
		 sb.append(generate(beanDTO, bean, fromDTOMethodName));
		 return sb.toString();
	 }
	/**
	 *  遍历target 类中所有的属性，如果source 类中也存在，则生成设置值代码。 <br/>
	 *
	 * @author MoMo.Yuan
	 * @param source				源类
	 * @param target				目标类
	 * @param methodType	生成的方法名 xxx + targetClassName
	 * @return		返回生成的转换代码
	 */
	private String generate(Class<?> source, Class<?> target, String methodName){
		StringBuilder sb = new StringBuilder();

		// 取得当前源类包名和类名
		String sourcePackageClassName = source.getName();
		String sourceClassName = source.getSimpleName();
		// 取得当前目标类包名和类名
		String targetPackageClassName = target.getName();
		String targetClassName = target.getSimpleName();

		// 生成的方法内的变量名
		String oldObjectName = StringUtil.lowerFirstChar(sourceClassName);
		String newObjectName = " new"+targetClassName;

		sb.append("\tpublic  " ).append(targetPackageClassName).append(" ").append(methodName).append(targetClassName)
			.append("("+sourcePackageClassName).append(" ").append(oldObjectName).append("){\n");
		sb.append("\t\tif(").append(oldObjectName).append(" == null){\n").append("\t\t\treturn null;\n").append("\t\t}\n\t\t");
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

				sb.append("\t\t").append(newObjectName).append(".").append(targetFieldName)
				.append("(").append(oldObjectName).append(".").append(sourceFieldName).append("()")
				.append(");\n");
			}
		}

		sb.append("\t\treturn ").append(newObjectName).append(";\n");
		sb.append("\t}\n");

		return sb.toString();
	}



	/**
	 *	循环beanPkg中所有的bean，于dtoPkg中的 bean 相对应，如果匹配到，则生成相应的转换代码。
	 *
	 * @param beanPkg							bean 的包路径。
	 * @param dtoPkg							dto 的包路径。
	  * @param toDTOMethodName		从bean 转换到 DTO 时的方法前缀名称。
	  * @param fromDTOMethodName	从 DTO 转换到 bean 类的方法前缀名称。
	  * @return
	  */
	public String generate( String beanPkg, String dtoPkg, String toDTOMethodName, String fromDTOMethodName){
		StringBuilder sb = new StringBuilder ();

		// 取得source package下面所有的类
		Map<String,Class<? extends Class<?>>> sourceClasses = ClassUtil.getClassesMap(beanPkg);
		if(CollectionUtil.isNull(sourceClasses)){
			return "";
		}

		Map<String,Class<? extends Class<?>>> targetClasses = ClassUtil.getClassesMap(dtoPkg);
		if(CollectionUtil.isNull(sourceClasses)){
			return "";
		}

		Set<String> sourceKeySet = sourceClasses.keySet();
		for(String sourceClassName : sourceKeySet){
			Class<?> targetClazz = targetClasses.get(sourceClassName);
			if(targetClazz == null){ // target 包中不包含当前类型
				continue;
			}
			Class<?> sourceClazz = sourceClasses.get(sourceClassName);
			sb.append(generate(sourceClazz, targetClazz, toDTOMethodName, fromDTOMethodName));
		}

		return sb.toString();
	}

	/**
	 *	循环beanPkg中所有的bean，于dtoPkg中的 bean 相对应，如果匹配到，则生成相应的转换代码。
	 *
	 * @param beanPkg							bean 的包路径。
	 * @param dtoPkg							dto 的包路径。
	  * @param toDTOMethodName		从bean 转换到 DTO 时的方法前缀名称。
	  * @param fromDTOMethodName	从 DTO 转换到 bean 类的方法前缀名称。
	  * @param notConverteField			一个字符串数组，表示不生成该字段的转换代码。
	  * @return
	  */
	public  String generate( String beanPkg, String dtoPkg, String toDTOMethodName, String fromDTOMethodName, String ... notConverteField){
		if(CollectionUtil.isNotNull(notConverteField)){
			this.notConverteField = Arrays.asList(notConverteField);
		 }
		return generate(beanPkg, dtoPkg, toDTOMethodName, fromDTOMethodName);
	}
}

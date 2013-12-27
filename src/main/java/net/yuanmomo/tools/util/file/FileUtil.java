/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.util.file
 * Created on   : 2013-12-26下午12:03:42
 * File Name    : FileUtil.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */
/**
 * Project Name : Tools
 * File Name    : FileUtil.java
 * Package Name : net.yuanmomo.tools.util.file
 * Created on   : 2013-12-26下午12:03:42
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.file;

import java.io.File;

import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : FileUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-26 下午12:03:42 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class FileUtil {
	/**
	 * getNameWithoutExtend: 取得文件的名称，不含扩展名. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param file
	 * @return
	 * @since JDK 1.6
	 */
	public static String getNameWithoutExtend(File file){
		if(file != null && !file.isDirectory()){
			String fileFullName = file.getName();
			return getNameWithoutExtend(fileFullName);
		}else{
			return "";
		}
	}
	/**
	 * getNameWithoutExtend: 取得文件的名称，不含扩展名. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param fileFullName
	 * @return
	 * @since JDK 1.6
	 */
	public static String getNameWithoutExtend(String fileFullName){
		if(StringUtil.isBlank(fileFullName)){
			return "";
		}
		return fileFullName.substring(0,fileFullName.lastIndexOf('.'));
	}
	/**
	 * getExtend:取得文件的扩展名. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param file
	 * @return
	 * @since JDK 1.6
	 */
	public static String getExtend(File file){
		if(file != null && !file.isDirectory()){
			String fileFullName = file.getName();
			return getExtend(fileFullName);
		}else{
			return "";
		}
	}
	/**
	 * getExtend: 取得文件的扩展名. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param fileFullName
	 * @return
	 * @since JDK 1.6
	 */
	public static String getExtend(String fileFullName){
		if(StringUtil.isBlank(fileFullName)){
			return "";
		}
		return fileFullName.substring(fileFullName.lastIndexOf('.')+1);
	}
}

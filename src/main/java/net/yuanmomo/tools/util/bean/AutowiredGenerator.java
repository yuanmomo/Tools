
/**
 * Project Name : Tools
 * File Name    : AutowiredGenerator.java
 * Package Name : net.yuanmomo.tools.util.bean
 * Created on   : 2014-4-23下午9:49:23
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.yuanmomo.tools.util.file.FileUtil;
import net.yuanmomo.tools.util.string.StringUtil;

/**
 * ClassName : AutowiredGenerator 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-4-23 下午9:49:23 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class AutowiredGenerator {
	private static final String path="E:\\Code\\BlogWorkspace\\yzq-bean\\src\\main\\java\\com\\tianhaoera\\yzq\\service\\db\\mapper";
	
	public static void main(String[] args) {
		// 找到该目录下面制定的所有文件
		File direcotry = new File(path);
		
		List<String> result = new ArrayList<String>();
		if(!direcotry.isDirectory()){
			result.add(generate(direcotry));
		}else{
			File[] files = direcotry.listFiles();
			for(File f : files){
				result.add(generate(f));
			}
		}
		for(String str : result){
			System.out.println(str);
		}
	}
	public static String generate(File f){
		// 判断文件是不是java文件
		String extend = FileUtil.getExtend(f);
		if("java".equalsIgnoreCase(extend)){
			return "\t@Autowired\n\tprivate " + FileUtil.getNameWithoutExtend(f) + " " + 
					StringUtil.lowerFirstChar(FileUtil.getNameWithoutExtend(f))+";";
		}
		return "";
	}
}


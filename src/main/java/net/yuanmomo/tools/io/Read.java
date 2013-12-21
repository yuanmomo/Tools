/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.io
 * Created on   : 2013-12-20下午4:06:42
 * File Name    : Reade.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */
/**
 * Project Name : Tools
 * File Name    : Reade.java
 * Package Name : net.yuanmomo.tools.io
 * Created on   : 2013-12-20下午4:06:42
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.yuanmomo.tools.path.PathUtil;
import net.yuanmomo.tools.string.StringUtil;

/**
 * ClassName : Reade 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2013-12-20 下午4:06:42 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class Read {
	// 系统默认编码
	public static Charset charset = Charset.defaultCharset();
	
	/**
	 * readFile: 指定文件路径和编码，解析文件为reader对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param path
	 * @param encoding
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static BufferedReader readFile(String path,String encoding) throws Exception{
		URI uri = PathUtil.getURIFromPath(path);
		return readFile(uri,encoding);
	}
	/**
	 * readFile: 指定文件URI路径和编码，解析文件为reader对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param uri
	 * @param encoding
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static BufferedReader readFile(URI uri,String encoding) throws FileNotFoundException{
		if(StringUtil.isNotBlank(encoding)){
			charset = Charset.forName(encoding);
		}
		return new BufferedReader(new InputStreamReader(new FileInputStream(new File(uri)),charset));
	}
	/**
	 * readFile: 指定文件路径，使用默认编码解析文件为reader对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param path
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static BufferedReader readFile(String path) throws Exception{
		URI uri = PathUtil.getURIFromPath(path);
		return readFile(uri,null);
	}
	
	/**
	 * readFile: 指定文件URI路径，按默认编码解析文件为reader对象. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param uri
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static BufferedReader readFile(URI uri) throws FileNotFoundException, UnsupportedEncodingException{
		return readFile(uri, null);
	}
	
	/**
	 * fileToList: 读取文件，按每行转换成一个String List. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param path
	 * @param encoding
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static List<String> fileToList(String path,String encoding) throws Exception{
		BufferedReader reader = readFile(path,encoding);
		String strLine = null;
		List<String> list = new ArrayList<String>();
		while((strLine = reader.readLine()) !=null){
			list.add(strLine);
		}
		return list;
	}
}

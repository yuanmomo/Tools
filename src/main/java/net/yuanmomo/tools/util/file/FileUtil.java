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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 *	判断指定的文件是否存在<br/>
	 *
	 * @author Hongbin Yuan
	 * @param filePath
	 * @return
	 *		
	 *		
	 */
	public File isFileExists(String filePath){
		File f = new File(filePath);
		if(f.exists()){
			return f;
		}
		return null;
	}
	
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
	
	/**
	 * 复制一个目录及其子目录、文件到另外一个目录
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFolder(File src, File dest) throws IOException {
		if(src == null){
			throw new NullPointerException("src file is null");
		}
		if(!src.exists()){
			throw new FileNotFoundException(src.getAbsolutePath() + " is not exists.");
		}
		if(dest == null){
			throw new NullPointerException("dest file is null");
		}
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// 递归复制
				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length = 0;
			
			try {
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				in.close();
				out.close();
			}
		}
	}
	
	/**
	 * copyFolder: 复制一个目录及其子目录、文件到另外一个目录. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param src
	 * @param dest
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static void copyFolder(String src, String dest) throws IOException {
		copyFolder(new File(src),new File(dest));
	}
	
	
	/**
	 * @param directory
	 * @return
	 */
	public static List<File> traverseFolder(String directory) {
		// 返回结果
		List<File> fileList = new ArrayList<File>();
		
		// 判断是否是目录
		File rootDirec = new File(directory);
		if (!rootDirec.exists()) {
			logger.info(directory +" doesn't exists. return.... ");
			return fileList;
		}
		if(! rootDirec.isDirectory()){
			logger.info("' " + directory + " ' is not a directory. return.... ");
			return fileList;
		}
		// 遍历目录
		File[] files = rootDirec.listFiles();
		if (files.length == 0) {
			logger.info("' " + directory + " ' is empty. return.... ");
			return fileList;
		} else {
			for (File file2 : files) {
				if (!file2.isDirectory()) {
					fileList.add(file2);
				} else {
					traverseFolder(file2.getAbsolutePath());
				}
			}
		}
		return fileList;
	}
}

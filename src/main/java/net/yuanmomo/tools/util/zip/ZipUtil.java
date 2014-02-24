/**
 * Project Name : Tools
 * File Name    : ZipUtil.java
 * Package Name : net.yuanmomo.tools.util.zip
 * Created on   : 2014-2-24下午3:15:53
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.yuanmomo.tools.util.string.StringUtil;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;

/**
 * ClassName : ZipUtil Function : TODO ADD FUNCTION. Reason : TODO ADD REASON.
 * Date : 2014-2-24 下午3:15:53
 * 
 * @author : Hongbin Yuan
 * @version
 * @since JDK 1.6
 * @see
 */
public class ZipUtil {
	
	private static final String defaultInsertFileName="newFile.txt";
	
	private static final int MARK_MAX_LENGTH = 1024 * 1024 * 1024;
	private static final int READ_LENGTH = 1024 * 10;
	
	/**
	 *  读取一个zip文件,将每个文件读入为一个InputStream,<br />
	 * 	放入map,key为该文件的名字,value为当前文件的输入流.<br/>
	 *
	 * @author Hongbin Yuan
	 * @param zipFilePath	zip文件的路径
	 * @return				zip文件中每个文件的输入流map
	 * @throws IOException
	 */
	public static Map<String, InputStream> zipToInputStreamMap(String zipFilePath) throws Exception {
		File file = new File(zipFilePath);
		if (file.exists()) {
			// 返回的文件名与其对应的内存流
			Map<String, InputStream> inStreamMap = null;
			// zip文件实例
			ZipFile zipFile = null;
			try {
				zipFile = new ZipFile(file);
			} catch (IOException e1) {
				throw e1; // 初始化zip文件异常
			}
			try {
				inStreamMap = new LinkedHashMap<String, InputStream>();
				// 把zip包中的每个文件读取出来,同时将每个文件的输入流放入map表
				Enumeration<ZipArchiveEntry>  archiveEntries = zipFile.getEntries();
				while(archiveEntries.hasMoreElements()){
					// 取得当前文件
					ZipArchiveEntry entry = archiveEntries.nextElement();
					// 获取文件名, 作为map的key
					String entryFileName = entry.getName();
					// 保存每个文件的内存输入流
					InputStream content = new BufferedInputStream(zipFile.getInputStream(entry));
					inStreamMap.put(entryFileName, content);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);// 解析zip文件异常
			} finally {
			}
			return inStreamMap;
		}
		return null;
	}
	
	/**
	 * addEntryToInputStreamMap: 向Map的inputStream流中加入一个新的流. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param inStreamMap	被插入的输入流map表,如果为空,则会实例化一个新的 {@link LinkedHashMap}对象.
	 * @param fileName		如果为空,则使用默认的新插入文件名{@link defaultInsertFileName}
	 * @param content		如果对象为空,则赋值一个"",空串
	 */
	public static void addEntryToInputStreamMap(Map<String, InputStream> inStreamMap,String fileName, String content){
		if(inStreamMap == null){
			inStreamMap = new LinkedHashMap<String, InputStream>(); // map为空,实例化一个新的map
		}
		if(StringUtil.isBlank(fileName)){
			fileName = defaultInsertFileName;
		}
		if(content == null){
			content = "";
		}
		// 添加一个文本文件的输出流
		InputStream in =  new ByteArrayInputStream(content.getBytes());
		inStreamMap.put(fileName,in);
	}
	
	
	/**
	 * inputStreamMapToOutputStream: 将Map的输入流转换为一个输出流 <br/>
	 * 
	 * @author Hongbin Yuan
	 * @param inStreamMap	map输入流
	 * @since JDK 1.6
	 */
	public static void inputStreamMapToOutputStream(Map<String, InputStream> inStreamMap,OutputStream outStream) {
		if (inStreamMap != null && !inStreamMap.isEmpty()) {
			ZipArchiveOutputStream zipOutputStream = null;
			try {
				// zip输出流
				zipOutputStream = new ZipArchiveOutputStream(outStream);
				// Use Zip64 extensions for all entries where they are required
				zipOutputStream.setUseZip64(Zip64Mode.AsNeeded);

				// 遍历输入流
				Set<String> fileNameSet = inStreamMap.keySet();
				for (String fileName : fileNameSet) {
					if (fileName != null && inStreamMap.get(fileName)!=null) {
						ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(fileName);
						zipOutputStream.putArchiveEntry(zipArchiveEntry);
						InputStream in = null;
						try {
							in = inStreamMap.get(fileName); // 取得当前文件的输入流
							in.mark(MARK_MAX_LENGTH); // 设置标记的读取限制,重置输入流
							byte[] buffer = new byte[READ_LENGTH];  
                            int len = -1;
                            while((len = in.read(buffer)) != -1) {
                                //把缓冲区的字节写入到ZipArchiveEntry
                            	zipOutputStream.write(buffer, 0, len);
                            }
                            // 完成当前entry的输出流
                            zipOutputStream.closeArchiveEntry();  
						} catch (Exception e) {
							throw new RuntimeException(e);
						} finally {
							// 重置该输入流,但是不关闭, 重复使用
							if(in.markSupported()){
								in.reset();
							}else{
								System.err.println("该文件输入流没有启用reset:"+fileName);
							}
						}
					}
				}
				zipOutputStream.finish();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (zipOutputStream != null) {
						zipOutputStream.close();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

}

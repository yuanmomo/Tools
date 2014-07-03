
/**
 * Project Name : Tools
 * File Name    : POIUtil.java
 * Package Name : net.yuanmomo.tools.excel.poi
 * Created on   : 2014-6-26上午11:00:53
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.excel.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : POIUtil 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-6-26 上午11:00:53 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
/**
 *
 */
public class ExcelGenerator<T> {
	private static final Logger logger = LoggerFactory.getLogger(ExcelGenerator.class);
	
	/**
	 * path: 生成的Excel文件存放路径,包括文件名.
	 * @since JDK 1.6
	 */
	private String fileAbsolutePath;
	
	
	/**
	 * headerCreater: 生成Excel 头.
	 * @since JDK 1.6
	 */
	private IExcelHeader headerCreater;
	
	/**
	 * bodyCreater: 生成Excel 体.
	 * @since JDK 1.6
	 */
	private IExcelBody<T> bodyCreater;
	
	public void generate() throws IOException{
		XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
		
        if(headerCreater != null){
//        	headerCreater.create(header);
        }
        if(bodyCreater != null){
//        	bodyCreater.create(header);
        }
	    // Write the output to a file
	    OutputStream  fileOut = null;
	    try {
	    	fileOut = new FileOutputStream(fileAbsolutePath);
	    	wb.write(fileOut);
		} catch (Exception e) {
			logger.error("生成Excel文件失败;File="+ fileAbsolutePath +";" + e.getMessage());
		} finally {
			if(fileOut != null){
				try {
					fileOut.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
}

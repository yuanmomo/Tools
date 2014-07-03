
/**
 * Project Name : Tools
 * File Name    : IHeader.java
 * Package Name : net.yuanmomo.tools.excel.poi
 * Created on   : 2014-6-26上午11:01:07
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.excel.poi;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * ClassName : IHeader 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-6-26 上午11:01:07 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public interface IExcelHeader {
	/*
	 * 创建Excel 头
	 */
	public void create(XSSFSheet sheet) throws Exception;
	
	public void createHeadCell() throws Exception;
}

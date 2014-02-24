
/**
 * Project Name : Tools
 * File Name    : ZipTest.java
 * Package Name : net.yuanmomo.tools.util.zip
 * Created on   : 2014-2-24下午5:07:31
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;

/**
 * ClassName : ZipTest 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-24 下午5:07:31 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class ZipTest {
	@Test
	public void test(){
		Map<String, InputStream> in = null;
		try {
			long now = System.currentTimeMillis();
			for(int i = 0; i < 10;i++){
				// 读取zip文件到输入流
				if(in == null){
					in = ZipUtil.zipToInputStreamMap("E:\\zip\\old.apk");
				}
				
				// 添加一个新的文件
				ZipUtil.addEntryToInputStreamMap(in, "res/xml/inviter.txt", ""+(1000000+i));
				
				// 转换为文件输出流
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(new File("E:\\zip\\new_"+i+".apk"));
					ZipUtil.inputStreamMapToOutputStream(in, out);
					// 输出文件输出流
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					out.close();
				}
			}
			System.out.println( System.currentTimeMillis() - now);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


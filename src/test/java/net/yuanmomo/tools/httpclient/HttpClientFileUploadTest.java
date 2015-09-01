
/**
 * Project Name : dooliuManager
 * File Name    : HttpClientFileUpload.java
 * Package Name : com.clou.douliu.server.util.fileupload
 * Created on   : 2014-1-18下午4:10:14
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.yuanmomo.tools.httpclient.upload.HttpClientFileUpload;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ClassName : HttpClientFileUpload 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-1-18 下午4:10:14 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class HttpClientFileUploadTest {
	@Test
	public static void test() {
		List<File> files = new ArrayList<File>();
		files.add(new File("C:\\Users\\Administrator\\Desktop\\tavm78jzhqosklwdou15_mid.jpg"));
		files.add(new File("C:\\Users\\Administrator\\Desktop\\tavm78jzhqosklwdou15_mid.jpg"));
		files.add(new File("C:\\Users\\Administrator\\Desktop\\tavm78jzhqosklwdou15_mid.jpg"));
		try {
			String url = "http://10.98.87.108:8080/douliu/uploadFile";
			String str = HttpClientFileUpload.send(url,4,files);
			
			Map<String, List<String>> obj = new Gson().fromJson(str,new TypeToken<Map<String, List<String>>>() {}.getType());
			List<String> result = obj.get("names");
			for(String s : result){
				System.out.println(s);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


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

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class HttpClientFileUpload {
	private static Logger logger=LoggerFactory.getLogger(HttpClientFileUpload.class);
	
	private static CloseableHttpClient client = HttpClients.createDefault();
	
	/**
	 * send: 请求上传指定类型的文件. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param type
	 * @param files
	 * @throws IOException,ClientProtocolException 
	 * @since JDK 1.6
	 */
	public static String send(String url,int type,List<File> files) 
			throws IOException,ClientProtocolException{
		// 先判断client是否被关闭
		if(client == null){
			client = HttpClients.createDefault();
		}
		// 返回结果
		String responseBody = null;
		try {
			logger.info("请求上传文件，type="+type);
            HttpPost httppost = new HttpPost(url);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create().addTextBody("type", type+"");
            for(File file : files){
            	FileBody fileBody = new FileBody(file);
            	builder.addPart("file", fileBody);
            }
            // 构造请求参数
            HttpEntity reqEntity = builder.build();

            //　设置请求参数
            httppost.setEntity(reqEntity);

            // 发送请求，并取得返回值
            responseBody = client.execute(httppost, FileUploadResponseHandler.responseHandler);
            
            logger.info("文件上传完成");
            return responseBody;
        } catch (ClientProtocolException e) {
        	throw e;
        } catch (IOException e) {
        	throw e;
		} finally {
			// 连接不关闭,复用连接
        }
	}
	
	/**
	 * send: 请求上传指定类型的文件. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param type
	 * @param file
	 * @throws IOException,ClientProtocolException 
	 * @since JDK 1.6
	 */
	public static String send(String url,int type,File file) 
			throws IOException,ClientProtocolException{
		List<File> fileList = new ArrayList<File>();
		fileList.add(file);
		return send(url, type, fileList);
	}
}

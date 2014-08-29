
/**
 * Project Name : dooliuManager
 * File Name    : ResponseHandler.java
 * Package Name : com.clou.douliu.server.util.fileupload
 * Created on   : 2014-1-20上午10:51:16
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient.upload;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : ResponseHandler 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-1-20 上午10:51:16 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class FileUploadResponseHandler implements ResponseHandler<String> {
	private static Logger logger=LoggerFactory.getLogger(FileUploadResponseHandler.class);
	
	public static ResponseHandler<String> responseHandler = new FileUploadResponseHandler();

	/**
	 * handleResponse:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
	 */
	@Override
	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		logger.info("文件上传请求结果状态,status = "+statusLine.toString());
	        
		int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
        	HttpEntity entity = null;
        	try {
        		entity = response.getEntity();
	            return entity != null ? EntityUtils.toString(entity) : null;
			} catch (Exception e) {
				throw new IOException("Format response body failed");
			}finally{
				EntityUtils.consume(entity);
			}
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
	}

}

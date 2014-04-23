
/**
 * Project Name : dooliuManager
 * File Name    : ResponseHandler.java
 * Package Name : com.clou.douliu.server.util.fileupload
 * Created on   : 2014-1-20上午10:51:16
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient.downloadapp;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
public class AppPaginationResponseHandler implements ResponseHandler<String> {
	private static Logger logger=LoggerFactory.getLogger(AppPaginationResponseHandler.class);
	
	public static ResponseHandler<String> responseHandler = new AppPaginationResponseHandler();

	@Override
	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		logger.info("请求结果状态,status = "+statusLine.toString());
	        
		int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
        	HttpEntity entity = null;
        	try {
        		entity = response.getEntity();
        		if(entity == null){
        			return "请求结果返回空";
        		}
        		String html = EntityUtils.toString(entity);
        		Document document = Jsoup.parse(html);
        		
        		Elements elems = document.select("");
        		System.out.println(elems);
        		return null;
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


/**
 * Project Name : azq-manager
 * File Name    : HttpDownload.java
 * Package Name : com.azq.manager.util
 * Created on   : 2014-8-29下午2:44:25
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient.downloadapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * ClassName : HttpDownload 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-8-29 下午2:44:25 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class HttpDownload {
	
	private static PoolingHttpClientConnectionManager cm = null;
	private static CloseableHttpClient httpclient = null;
	
	static{
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(20);
		
		httpclient = HttpClients.custom()
				.setConnectionManager(cm).build();
	}
	
	/**
	 * parseURL: 制定一个URL地址，通过xpath截取匹配到的结果地址. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param url		请求的页面地址
	 * @param xpath		目的URL的xpath
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String parseURL(final String url, final String xpath, final String attr,HttpContext context) throws ClientProtocolException, IOException{
		if(context == null){
			context = new BasicHttpContext();
		}
		HttpGet httpget = new HttpGet(url);
		return httpclient.execute(httpget,new ResponseHandler<String>(){
			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
//				StatusLine statusLine = response.getStatusLine();
			        
				int status = response.getStatusLine().getStatusCode();
		        if (status >= 200 && status < 300) {
		        	HttpEntity entity = null;
		        	try {
		        		entity = response.getEntity();
		        		if(entity != null){
			        		String html = EntityUtils.toString(entity);
			        		Document document = Jsoup.parse(html);
			        		Elements elements =  document.select(xpath);
			        		if(elements != null && elements.size() > 0){
			        			return elements.get(0).attr(attr);
			        		}
		        		}
		        		return null;
					} catch (Exception e) {
						e.printStackTrace();
						throw new IOException("Format response body failed");
					}finally{
						EntityUtils.consume(entity);
					}
		        } else {
		            throw new ClientProtocolException("Unexpected response status: " + status);
		        }
			}
			
		}, context );
	}
	
	public static void downLoad(String url, String dst) throws Exception { 
		InputStream in = null;
		 OutputStream out = null;
        try {  
            HttpGet httpGet = new HttpGet(url);  
            HttpResponse httpResponse = httpclient.execute(httpGet);  
            HttpEntity entity = httpResponse.getEntity();  
            in = entity.getContent();  
            long length=entity.getContentLength();  
            if(length <= 0){  
                throw new RuntimeException("下载文件不存在！");  
            }  
            out = new FileOutputStream(new File(dst));
            
            byte[] data = new byte[1024*1024];  
            int index =0;  
            while ((index=in.read(data) )!= -1) {  
                out.write(data,0,index);  
            }  
        } catch (Exception e) {  
            throw e;
        } finally{
        	if(in != null){
        		in.close();
        	}
        	if(out != null ){
        		out.close();
        	}
        }
    }  
}

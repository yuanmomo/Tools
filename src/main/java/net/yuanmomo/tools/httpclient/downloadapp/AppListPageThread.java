
/**
 * Project Name : Tools
 * File Name    : AppListPageThread.java
 * Package Name : net.yuanmomo.tools.httpclient.downloadapp
 * Created on   : 2014-3-4下午7:30:14
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient.downloadapp;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/**
 * ClassName : AppListPageThread 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-4 下午7:30:14 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class AppListPageThread extends Thread {
	private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;
    private final int id;

    public AppListPageThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
        this.httpClient = httpClient;
        this.context = new BasicHttpContext();
        this.httpget = httpget;
        this.id = id;
    }

    /**
     * Executes the GetMethod and prints some status information.
     */
    @Override
    public void run() {
        try {
            String result = httpClient.execute(httpget,AppPaginationResponseHandler.responseHandler, context);
            System.out.println("Thread "+ id + ": " + result);
        } catch (Exception e) {
        }
    }
}

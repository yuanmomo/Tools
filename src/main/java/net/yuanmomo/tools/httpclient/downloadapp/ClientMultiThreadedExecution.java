/**
 * Project Name : Tools
 * File Name    : ClientMultiThreadedExecution.java
 * Package Name : net.yuanmomo.tools.httpclient.downloadapp
 * Created on   : 2014-3-4下午7:29:16
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.httpclient.downloadapp;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * ClassName : ClientMultiThreadedExecution Function : TODO ADD FUNCTION. Reason
 * : TODO ADD REASON. Date : 2014-3-4 下午7:29:16
 * 
 * @author : Hongbin Yuan
 * @version
 * @since JDK 1.6
 * @see
 */
public class ClientMultiThreadedExecution {
	private static int capturePagesCount = 3;
	
	public static void main(String[] args) throws Exception {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);

		CloseableHttpClient httpclient = HttpClients.custom()
				.setConnectionManager(cm).build();
		try {
			String appPaginationURL = "http://as.baidu.com/a/software?cid=101&s=1&pn=";

			// create a thread for each URI
			AppListPageThread[] threads = new AppListPageThread[capturePagesCount];
			for (int i = 0; i < capturePagesCount; i++) {
				HttpGet httpget = new HttpGet(appPaginationURL + (i+1));
				threads[i] = new AppListPageThread(httpclient, httpget, i + 1);
			}

			// start the threads
			for (int j = 0; j < threads.length; j++) {
				threads[j].start();
			}

			// join the threads
			for (int j = 0; j < threads.length; j++) {
				threads[j].join();
			}
		} finally {
			httpclient.close();
		}
	}
}


/**
 * Project Name : Tools
 * File Name    : TES.java
 * Package Name : net.yuanmomo.tools.proxy
 * Created on   : 2014-2-28下午1:52:27
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.proxy.jdk;
import java.io.FileOutputStream;
import java.io.IOException;

import net.yuanmomo.tools.proxy.jdk.impl.HelloImpl;
import sun.misc.ProxyGenerator;

/**
 * 代理类的生成工具
 * @author Hongbin Yuan
 * @since 2014-02-28
 */
public class ProxyGeneratorUtils {

	/**
	 * 把代理类的字节码写到硬盘上
	 * @param path 保存路径
	 */
	public static void writeProxyClassToHardDisk(String path) {
		// 第一种方法，这种方式在刚才分析ProxyGenerator时已经知道了
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
		
		// 第二种方法
		
		// 获取代理类的字节码
		byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", HelloImpl.class.getInterfaces());
		
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(path);
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}




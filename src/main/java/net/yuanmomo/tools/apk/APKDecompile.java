
/**
 * Project Name : Tools
 * File Name    : APKDecompile.java
 * Package Name : net.yuanmomo.tools.apk
 * Created on   : 2014-5-4下午2:55:15
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.apk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : APKDecompile 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-5-4 下午2:55:15 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.7
 * @see 	 
 */
public class APKDecompile {
	private static Logger logger = LoggerFactory.getLogger(APKDecompile.class);

	/**
	 *	使用制定的jar文件反编译apk文件<br/>
	 *
	 * @author Hongbin Yuan
	 * @param apkToolPath		反编译工具apktool.jar的路径
	 * @param apkFilePath		待反编译的apk文件路径
	 * @return					返回反编译后生成的文件目录
	 * @throws Exception
	 *		
	 *		
	 */
	public static String decompile(String apkToolPath, String apkFilePath) throws Exception{
		File apkTool = new File(apkToolPath);
		if( !apkTool.exists() ){
			throw new FileNotFoundException("反编译工具 apkTool.jar不存在：" + apkToolPath );
		}
		File apkFile = new File(apkFilePath);
		if( !apkFile.exists() ){
			throw new FileNotFoundException("待反编译的apk文件不存在：" + apkFilePath );
		}
		
		ProcessBuilder pb = null;
		try {
			if   ("\\".equals(File.separator))  {
				pb = new ProcessBuilder("cmd");
			}   else   if   ("/".equals(File.separator))  {
				pb = new ProcessBuilder("/bin/sh");
			} else{
				throw new Exception("不支持的操作系统");
			}
			//java.lang.ProcessBuilder:  Creates operating system processes. 
			pb.directory(new File(apkFile.getParent()));//设置shell的当前目录。   
			PrintWriter out = null;
			Process proc = null;
			BufferedReader err = null;
			try {  
			    proc = pb.start();  
			    //获取输出流，可以通过它向SHELL发送命令。   
			    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc 
			                    .getOutputStream())), true); 
			    err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			    String command2 = " java -jar \"" + apkTool.getAbsolutePath() + "\" d -f " + apkFilePath;
			    out.println(command2); 
			    out.println("exit"); 
			    proc.waitFor();
			    
			    String line;  
			    while ((line = err.readLine()) != null) {  
			        System.out.println(line);  // 打印错误输出结果
			    }  
				//if(exitCode == 0){// 脚本执行成功
				return apkFilePath.replace(".apk", "");
				//}
			} catch (Exception e) {  
			    throw e;
			} finally {
				proc.destroy();
				if(out != null){
					out.close();
				}
				if(err != null){
					err.close();
				}
			}
		} catch (Exception e) {
			// 反编译apk包出现异常
			if(logger.isErrorEnabled()){
				logger.error("反编译apk包出现异常;apkeFile = " +apkFilePath);
			}
			throw e;
		}
	}
}

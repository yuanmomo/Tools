
/**
 * Project Name : Tools
 * File Name    : BatchCall.java
 * Package Name : net.yuanmomo.tools.bat
 * Created on   : 2014-7-3上午10:58:20
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.bat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import net.yuanmomo.tools.util.string.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : BatchCall 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-7-3 上午10:58:20 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class BatchCall {
	private static Logger logger = LoggerFactory.getLogger(BatchCall.class);
	
	/**
	 * call:. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param direcotry		运行目录
	 * @param command		调用的参数，命令
	 * @param params
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void call(String direcotry,String command,Object ... params) throws Exception{
		if(StringUtil.isBlank(command)){
			throw new NullPointerException("command is null ");
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
			if(StringUtil.isNotBlank(direcotry)){ 
				File direct = new File(direcotry); // 设置了运行目录，别且是一个目录	
				if(direct.isDirectory()){
					pb.directory(direct);//设置shell的当前目录。   
				}else{
					pb.directory(direct.getParentFile());
				}
			}
			PrintWriter out = null;
			Process proc = null;
			BufferedReader err = null;
			try {  
			    proc = pb.start();  
			    //获取输出流，可以通过它向SHELL发送命令。   
			    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc 
			                    .getOutputStream())), true); 
			    err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			    // 拼凑运行命令
			    StringBuilder commandToRun = new StringBuilder(command + " ");
			    if(params != null && params.length > 0){
			    	for(Object param : params){
			    		commandToRun.append(param).append(" ");
			    	}
			    }
			    out.println(commandToRun); 
			    out.println("exit"); 
			    int exitCode = proc.waitFor();
			    
			    String line;  
			    while ((line = err.readLine()) != null) {  
			    	logger.error(line);  // 打印错误输出结果
			    }  
				if(exitCode == 0){// 脚本执行成功
					return;
				}
				throw new RuntimeException("command exec with error code :" + exitCode);
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
			if(logger.isErrorEnabled()){
				logger.error("调用系统命令异常; " + e.getMessage());
				logger.error("命令：command = " + command );
				logger.error("参数： \n");
				if(params != null && params.length > 0){
			    	for(int i = 0; i<params.length; i++){
			    		logger.error( i + " : " + params[i]);
			    	}
			    }
			}
			throw e;
		}
	}
}


/**
 * Project Name : azq-maven
 * File Name    : APKGenerator.java
 * Package Name : com.azq.service.util
 * Created on   : 2014-3-31下午2:05:59
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.apk;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ClassName : APKGenerator 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-3-31 下午2:05:59 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class APKGenerator {
	private static Logger logger = LoggerFactory.getLogger(APKGenerator.class);
	
	/**
	 * generator: 运行脚本默认放在WEB-INF目录下面 xxx.sh. <br/>
	 *			生成的新APK文件:  + userId + postFix
	 * @author Hongbin Yuan
	 * @param shPath			生成APK文件脚本的路径	
	 * @param originalAPKPath	原始的APK文件绝对		
	 * @param invitePath		invite文件生成的根目录				
	 * @param userId			邀请用户id
	 * @param prefix			生成的新APK文件前缀
	 * @param postFix			生成的新APK文件后缀,不能含.apk扩展名
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	public static boolean generator(String shPath,String originalAPKPath,
			String invitePath,Integer userId,
			String prefix,String postfix) throws Exception{
		File isGenerated = new File(invitePath + File.separator +"generated" +File.separator
				+userId + File.separator +  + userId + prefix + ".apk");
		if(isGenerated.exists()){ // 判断文件是否已经生成
			if(logger.isInfoEnabled()){
				logger.info("已经生成了;userId = "+userId);
			}
			return true;
		}
		
		// 判断脚本文件是否存在
		File shell  = new File(shPath);
		if(!shell.exists() || !shell.canExecute()){
			if(logger.isErrorEnabled()){
				logger.error("shell脚本文件不存在或没有执行权限;shPath ="+shPath);
			}
			throw new Exception("shell脚本文件不存在或没有执行权限");
		}
		
		File invite = new File(invitePath);
		if(!invite.exists()){
			invite.mkdirs(); // 创建生成目录
		}
		
		File originalAPK= new File(originalAPKPath);
		if(!originalAPK.exists()){
			if(logger.isErrorEnabled()){
				logger.error("原始的apk文件不存在:"+originalAPK);
			}
			throw new Exception("原始的apk文件不存在");
		}
		
		File meta_inf = new File(invitePath + File.separator + "META-INF");
		if(!meta_inf.exists()){
			meta_inf.mkdirs(); // 创建生成目录
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(shPath).append(" ").append(originalAPKPath).append(" ").append(invitePath);
		sb.append(" ").append(userId).append(" ").append(prefix).append(" ").append(postfix);
		if(logger.isInfoEnabled()){
			logger.info("执行脚本命令;command=" + sb.toString());
		}
		
		try {
			Process ps = Runtime.getRuntime().exec(sb.toString());
			int exitCode = ps.waitFor();
			if(exitCode == 0){// 脚本执行成功
				return true;
			}
			if(logger.isErrorEnabled()){
				logger.error("生成该用户对应的邀请apk包错误;userId ="+userId+";code="+exitCode);
			}
		} catch (Exception e) {
			// 生成该用户对应的邀请apk包出现异常
			if(logger.isErrorEnabled()){
				logger.error("生成该用户对应的邀请apk包出现异常;userId ="+userId,e);
			}
			throw e;
		}
		throw new Exception("文件生成失败");
	}
	public static void main(String[] args) {
		try {
			generator("/root/apk/generte.sh","/root/apk/izq.apk","/download/invite/",123,"izq","888");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}

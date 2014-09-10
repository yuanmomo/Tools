
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs.PKCS7;

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
public class APKUtil {
	private static Logger logger = LoggerFactory.getLogger(APKUtil.class);

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
	
	private static final int BUFFER = 1024;  
	
	public static String unZip(String apkFilePath) throws Exception {
		ZipFile zipFile = new ZipFile(apkFilePath);
		Enumeration<ZipArchiveEntry> emu = zipFile.getEntries();

		String outPath = apkFilePath.replace(".apk", "") + File.separator;
		try {
			while (emu.hasMoreElements()) {
				ZipArchiveEntry entry = emu.nextElement();
				if (entry.isDirectory()) {
					new File(outPath + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bis = new BufferedInputStream(
							zipFile.getInputStream(entry));
					
					File file = new File(outPath + entry.getName());
					File parent = file.getParentFile();
					if (parent != null && !parent.exists()) { // 不存在父目录，创建
						parent.mkdirs();
					}
					
					bos = new BufferedOutputStream(
							new FileOutputStream(file), BUFFER);
					
					byte[] buf = new byte[BUFFER];
					int len = 0;
					while ((len = bis.read(buf, 0, BUFFER)) != -1) {
						bos.write(buf, 0, len);
					}
					bos.flush();
				} catch (Exception e) { // 个别文件生成失败
					e.printStackTrace();
					continue;
				} finally {
					if(bis != null){
						bis.close();
					}
					if(bos != null){
						bos.close();
					}
				}
			}
			return outPath;
		} catch (Exception e) {
			throw e;
		} finally {
			zipFile.close();
		}
	}
	
	
    public static Map<String,String> getSign(String apkFilePath) throws Exception { 
    	String decompiledPath = APKUtil.unZip(apkFilePath);
    	
    	InputStream in = null;
    	X509Certificate publicKey = null;
    	try {
    		
    		File metaInfDirec = new  File(decompiledPath + File.separator + "META-INF");
    		if(metaInfDirec == null || !metaInfDirec.exists()){
    			throw new FileNotFoundException("META-INF in " + apkFilePath + " not found");
    		}
    		File[] fileList = metaInfDirec.listFiles();
    		File rsaFile = null;
    		if(fileList != null && fileList.length > 0){
    			for(File f : fileList){
    				if(f.getName().toUpperCase().endsWith(".RSA")){
    					rsaFile = f; 
    					break;
    				}
    			}
    		}
    		if(rsaFile == null || !rsaFile.exists()){
    			throw new FileNotFoundException("No sign file(*.RSA) in " + apkFilePath + " found");
    		}
    		in = new FileInputStream(rsaFile);
    		PKCS7 pkcs7 = new PKCS7(in);  
    		publicKey = pkcs7.getCertificates()[0];  
		} catch (Exception e) {
			throw e;
		} finally {
			if(in != null){
				in.close();
			}
		}
        if(publicKey != null){
        	Map<String,String> signMap = new HashMap<>();
        	signMap.put("key", publicKey.getPublicKey().toString());
        	signMap.put("issuer", publicKey.getIssuerDN().toString());
        	signMap.put("subject", publicKey.getSubjectDN().toString());
        	return signMap;
        }
        return null;
    }  
    
    public static boolean verifyApk(String apk1, String apk2) throws Exception{
    	Map<String,String> sign1 = getSign(apk1);
		Map<String, String> sign2 = getSign(apk2);
		return sign1.equals(sign2);
    }
    
    public static void main(String[] args) {
		try {
			System.out.println(verifyApk("E:\\sign\\com.adesk.pictalk_105417.apk","E:\\sign\\PicTalk_7.apk"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * Project Name : azq-maven
 * File Name    : tEST.java
 * Package Name : com.azq.service.util
 * Created on   : 2014-2-26下午5:30:22
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.cipher;

import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * ClassName : DESCoding
 * Function  : 3DES加解密的工具类
 * Reason    : TODO ADD REASON. 
 * Date      : 2014-2-26 下午5:30:22 
 *
 * @author   : Hongbin Yuan
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class DESCoding {
	
	private static DESCoding des = null;
	
	private String Algorithm = "DESede"; // 定义 加密算法，默认为DESede
	
	/**
	 * CIPHER_KEY: URL加密密匙.
	 * @since JDK 1.6
	 */
	public static final byte[] CIPHER_KEY="3g&hmae#faEm@3#$%jLM3g**".getBytes();
	
	private byte[] keyBytes = null;

	/**
	 * 用默认的算法DESede，传入密钥，生成工具类
	 * 
	 * @param keyBytes
	 *            密钥，必须是24字节
	 * @throws Exception
	 */
	private DESCoding(byte[] keyBytes) throws Exception {
		if (keyBytes.length != 24) {
			throw new Exception("the keys's length must be 24!");
		}
		this.keyBytes = keyBytes;
	}

	/**
	 * 用指定的算法，传入加密的key，生成工具类
	 * 
	 * @param keyBytes
	 *            密钥，必须是24字节
	 * @param Algorithm
	 *            算法
	 * @throws Exception
	 */
	private DESCoding(byte[] keyBytes, String Algorithm) throws Exception {
		if (keyBytes.length != 24) {
			throw new Exception("the keys's length must be 24!");
		}
		this.keyBytes = keyBytes;
		this.Algorithm = Algorithm;
	}

	public static DESCoding getCoder() throws Exception{
		if(des==null){
			return  new DESCoding(CIPHER_KEY);
		}
		return des;
	}
	
	/**
	 * 对指定的字节数组进行加密
	 * 
	 * @param src
	 *            需要进行加密的字节数组
	 * @return byte[] 加密后的字节数组，若加密失败，抛出异常
	 * @throws Exception 
	 */
	public byte[] encode(byte[] src) throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(this.keyBytes, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.lang.Exception e) {
			throw e;
		}
	}

	/**
	 * 加密并转换成hex Str
	 * 
	 * @param src
	 * @return String
	 * @throws Exception 
	 */
	public String encode2HexStr(byte[] src) throws Exception {
		return HexUtil.bytes2HexStr(encode(src));
	}

	/**
	 * 加密并转换成BASE64编码的字符串
	 * 
	 * @param src
	 * @return String
	 * @throws Exception 
	 */
	public String encode2Base64(byte[] src) throws Exception {
		return BASE64Coding.encode(encode(src));
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            用3DES加密后的字节数组
	 * @return byte[] 解密后的字节数组
	 * @throws Exception 
	 */
	public byte[] decode(byte[] src) throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(this.keyBytes, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw e;
		}
	}

	
	
	// DES,DESede,Blowfish
	public static void main(String[] args) throws Exception {
		try {
			ByteBuffer buf = ByteBuffer.allocate(12);
//			buf.putInt(9043801);
			buf.putInt(123456789);
//			buf.putInt(12345678);
			// buf.flip();

			 String encodeStr = BASE64Coding.encode(DESCoding.getCoder().encode(buf.array()));
			 System.out.println("encodeStr:" + encodeStr);

			 byte[] decodeBytes = DESCoding.getCoder().decode( BASE64Coding.decode(encodeStr));
			 ByteBuffer buf2 = ByteBuffer.wrap(decodeBytes);

			 System.out.println("QQ:" + buf2.getInt());
			 System.out.println("Time:" + buf2.getInt());
			 System.out.println("skey:" + buf2.getInt());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

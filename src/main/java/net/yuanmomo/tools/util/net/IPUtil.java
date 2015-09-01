/**
 * Project Name : Tools
 * File Name    : Test.java
 * Package Name : net.yuanmomo.tools.util.net
 * Created on   : 2014-3-15上午10:35:32
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.util.net;
/**
 * ClassName : Test Function : TODO ADD FUNCTION. Reason : TODO ADD REASON. Date
 * : 2014-3-15 上午10:35:32
 * 
 * @author : Hongbin Yuan
 * @version
 * @since JDK 1.6
 * @see
 */
public class IPUtil {

	public static void main(String[] args) {

		IPUtil obj = new IPUtil();

		System.out.println("iptoLong  : " + obj.ipToLong("192.168.1.2"));

		System.out.println("longToIp  : " + obj.longToIp(3232235778L));

	}

	public long ipToLong(String ipAddress) {
		long result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		for (int i = 3; i >= 0; i--) {
			long ip = Long.parseLong(ipAddressInArray[3 - i]);
			// left shifting 24,16,8,0 and bitwise OR
			// 1. 192 << 24
			// 1. 168 << 16
			// 1. 1 << 8
			// 1. 2 << 0
			result |= ip << (i * 8);
		}
		return result;
	}

	public String longToIp(long i) {
		StringBuilder sb = new StringBuilder(15);
		sb.append((i >> 24) & 0xFF).append(".").append((i >> 16) & 0xFF)
			.append(".").append((i >> 8) & 0xFF).append(".").append(i & 0xFF);
		return sb.toString();
	}
}
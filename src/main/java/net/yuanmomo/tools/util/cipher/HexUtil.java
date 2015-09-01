package net.yuanmomo.tools.util.cipher;

public class HexUtil {
	private static String HexCode[] = {"0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F"};

	/**
	 * 将单个字节转成Hex String
	 * 
	 * @param b
	 *            字节
	 * @return String Hex String
	 */
	public static String byte2HexStr(byte b) {
		int n = (int) b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HexCode[d1] + HexCode[d2];
	}

	/**
	 * 将字节数组转成Hex String
	 * 
	 * @param b
	 * @return String
	 */
	public static String bytes2HexStr(byte[] b) {
		if (b == null || b.length == 0) {
			return null;
		}
		StringBuilder strBuf = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			strBuf.append(byte2HexStr(b[i]));
		}
		return strBuf.toString();
	}

	/**
	 * 将单个hex Str转换成字节
	 * 
	 * @param str
	 * @return byte
	 */
	public static byte hexStr2Byte(String str) {
		byte ret = 0;
		for (byte i = 0; i < HexCode.length; i++) {
			if (HexCode[i].equalsIgnoreCase(str)) {
				return i;
			}
		}
		return ret;
	}

	/**
	 * 将hex Str转换成字节数组
	 * 
	 * @param str
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String str) {
		if (str == null || str.equals("")) {
			byte bArray[] = new byte[0];
			return bArray;
		}

		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			String high = str.substring(i * 2, i * 2 + 1);
			String low = str.substring(i * 2 + 1, i * 2 + 2);
			bytes[i] = (byte) (hexStr2Byte(high) * 16 + hexStr2Byte(low));
		}
		return bytes;
	}
}

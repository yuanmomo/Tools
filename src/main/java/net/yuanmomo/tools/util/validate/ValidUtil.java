package net.yuanmomo.tools.util.validate;

import java.util.regex.Pattern;

public class ValidUtil {
	public static boolean checkCheckcode(String verifyCode) {
		if (verifyCode == null || verifyCode.length() == 0)
			return false;
		return isVerifyCode(verifyCode);
	}

	public static boolean checkPhoneNumber(String phone) {
		if (phone == null || phone.length() == 0)
			return false;
		return isMobileNO(phone);
	}

	public static boolean checkLoginPassword(String password) {
		if (password == null || password.length() == 0)
			return false;
		return isPassword(password);
	}

	public static boolean isVerifyCode(String verifyCode) {
		return Pattern.compile("[0-9a-zA-Z]{4}").matcher(verifyCode).matches();
	}

	public static boolean isRealName(String realName) {
		return Pattern.compile("^[\\u4E00-\\u9FA5A-Z]{2,32}$")
				.matcher(realName).matches();
	}

	public static boolean isNumeric(String str) {
		return Pattern.compile("[0-9]*").matcher(str).matches();
	}

	public static boolean isEmail(String email) {
		return Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")
				.matcher(email).matches();
	}

	public static boolean isMobileNO(String str) {
		return Pattern.compile("1\\d{10}").matcher(str).matches();
	}

	public static boolean isMoney(String str) {
		return Pattern
				.compile(
						"^[1-9]\\d*(([\\.]?[0-9]{1,2})?)$|^[0][\\.][1-9]$|^[0][\\.]([0-9][1-9])$")
				.matcher(str).matches();
	}

	public static boolean isPassword(String password) {
		return Pattern
				.compile(
						"^[0-9a-zA-Z`~!@#%&:;\"'_=<,>\\.\\$\\?\\^\\/\\*\\(\\)\\-\\+\\{\\}\\[\\]\\|\\\\]{6,20}$")
				.matcher(password).matches();
	}

	public static boolean isPassword(String password, int min, int max) {
		String s1 = "^[0-9a-zA-Z`~!@#%&:;\"'_=<,>\\.\\$\\?\\^\\/\\*\\(\\)\\-\\+\\{\\}\\[\\]\\|\\\\]{";
		StringBuilder builder = new StringBuilder(s1);
		builder.append(Integer.toString(min));
		builder.append(",");
		builder.append(Integer.toString(max) + "}$");
		return Pattern.compile(builder.toString()).matcher(password).matches();
	}
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input.trim()))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
}

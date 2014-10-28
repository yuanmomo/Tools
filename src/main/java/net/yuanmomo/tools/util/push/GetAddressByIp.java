package net.yuanmomo.tools.util.push;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.yuanmomo.tools.json.GsonUtil;

public class GetAddressByIp {
	public static void main(String[] args) {
		System.out.println("8.8.8.8 == " + getAddressString("8.8.8.8"));
		System.out.println("61.139.2.69 ==" + getAddressString("61.139.2.69"));
	}
	
	public static String getAddressString(String ip){
		try {
			StringBuilder sb = new StringBuilder();
			Address address = getAddress(ip);
			if(address  != null){
				Data data = address.getData();
				sb.append(data.getCountry()).append(data.getRegion()).append(data.getCity()).append(data.getIsp());
			}
			return sb.toString();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static Address getAddress(String ip){
		try {
			String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			Address address = null;
			if(str != null){
				address = GsonUtil.getGson().fromJson(str,Address.class);
			}
			return address;
		} catch (Exception e) {
			throw new RuntimeException("获取IP地址异常", e);
		}
	}
	
	public static String getJsonContent(String urlStr) throws IOException {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				InputStream inputStream  = httpConn.getInputStream();
				// ByteArrayOutputStream相当于内存输出流
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					out.write(buffer, 0, len);
				}
				// 将内存流转换为字符串
				return new String(out.toByteArray());
			}
			return null;
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
}

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
		System.out.println("边边===便便");
		System.out.println("8.8.8.8 == " + getAddress("8.8.8.8"));
		System.out.println("61.139.2.69 ==" + getAddress("61.139.2.69"));
		System.out.println("边边===便便");
	}
	
	public static String getAddress(String ip){
		StringBuilder sb = new StringBuilder();
		try {
			String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			Address address = GsonUtil.getGson().fromJson(str,Address.class);
			if ("0".equals(address.getCode())) {
				Data data = address.getData();
				sb.append(data.getCountry()).append(data.getRegion()).append(data.getCity()).append(data.getIsp());
			} else {
				sb.append("IP地址有误");
			}
		} catch (Exception e) {
			sb.append("获取IP地址异常：").append(e.getMessage());
			
		}
		return sb.toString();
	}

	public static String getJsonContent(String urlStr) {
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
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
		return null;
	}
}

class Address {
	private String code;
	private Data data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Address [code=" + code + ", data=" + data + "]";
	}
	
}

class Data {
	private String ip;
	private String country;
	private String area;
	private String region;
	private String city;
	private String county;
	private String isp;
	private String country_id;
	private String area_id;
	private String region_id;
	private String city_id;
	private String county_id;
	private String isp_id;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCounty_id() {
		return county_id;
	}
	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}
	public String getIsp_id() {
		return isp_id;
	}
	public void setIsp_id(String isp_id) {
		this.isp_id = isp_id;
	}
	@Override
	public String toString() {
		return "Data [ip=" + ip + ", country=" + country + ", area=" + area
				+ ", region=" + region + ", city=" + city + ", county="
				+ county + ", isp=" + isp + ", country_id=" + country_id
				+ ", area_id=" + area_id + ", region_id=" + region_id
				+ ", city_id=" + city_id + ", county_id=" + county_id
				+ ", isp_id=" + isp_id + "]";
	}
}


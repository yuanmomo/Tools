package net.yuanmomo.tools.httpclient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.yuanmomo.tools.db.dataSource.DataSource;
import net.yuanmomo.tools.db.dataSource.bonecp.BoneCPDataSource;
import net.yuanmomo.tools.redis.json.GsonUtil;
import net.yuanmomo.tools.util.collention.CollectionUtil;
import net.yuanmomo.tools.util.properties.PropertiesUtil;
import net.yuanmomo.tools.util.string.StringUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class TudouCapture {
	private static String url = "http://www.tudou.com/s3portal/service/pianku/data.action?pageSize=100" +
    		"&app=mainsitepc&deviceType=1&tags=42&tagType=3&firstTagId=3" +
    		"&areaCode=440300&initials=&hotSingerId=" +
    		"&sortDesc=quality&qq-pf-to=pcqq.group&pageNo=";
	
	private static String insertSql = "insert into tudou (album_Id, item_Title, alias, "+
      "hd_Type, play_Url, album_Short_Desc, "+
      "need_Money, pic_Url_200x112, pic_Url_200x300, "+
      "pic_Url_448x252, pic_Url_448x672, title, "+
      "playtimes, is_Serial, pay_Info, "+
      "updateInfo)"+
      "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static DataSource ds = new BoneCPDataSource();
	static {
		Map<String, String> params;
		try {
			params = PropertiesUtil.propertiesToMap("src/main/resources/net/yuanmomo/tools/db/BoneCP.properties");
			ds.init(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static int i;
	public final static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	for(i = 1 ;i <65 ; i ++ ){
	            HttpGet httpget = new HttpGet(url + i);
	            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	                public String handleResponse(
	                        final HttpResponse response) throws ClientProtocolException, IOException {
	                    int status = response.getStatusLine().getStatusCode();
	                    if (status >= 200 && status < 300) {
	                        HttpEntity entity = response.getEntity();
	                        String res =  entity != null ? EntityUtils.toString(entity) : null;
	                        if(res  != null && StringUtil.isNotBlank(res)){
	                        	Response  resJson = GsonUtil.fromJson(res, Response.class);
	                        	if(resJson != null && CollectionUtil.isNotNull(resJson.getItems())){
	                        		System.out.println("正在处理第"+ i +"页返回的数据；该页一共返回了" + resJson.getItems().size() + "条信息");
	                        		for(Item item : resJson.getItems()){
	                        			Connection con = null;
	                        			PreparedStatement psmt =  null;
	                        			try {
	                        				con = ds.getConnection();
	                        				psmt = con.prepareStatement(insertSql);
	                        				psmt.setInt(1, item.getAlbumId());
	                        				psmt.setString(2, item.getItemTitle());
	                        				psmt.setString(3, item.getAlias());
	                        				psmt.setInt(4, item.getHdType());
	                        				psmt.setString(5, item.getPlayUrl());
	                        				psmt.setString(6, item.getAlbumShortDesc());
	                        				psmt.setString(7, item.getNeedMoney());
	                        				psmt.setString(8, item.getPicUrl_200x112());
	                        				psmt.setString(9, item.getPicUrl_200x300());
	                        				psmt.setString(10, item.getPicUrl_448x252());
	                        				psmt.setString(11, item.getPicUrl_448x672());
	                        				psmt.setString(12, item.getTitle());
	                        				psmt.setInt(13, item.getPlaytimes());
	                        				psmt.setInt(14, item.getIsSerial());
	                        				psmt.setString(15, item.getPayInfo());
	                        				psmt.setString(16, item.getUpdateInfo());
	                        				
	                        				psmt.execute();
										} catch (Exception e) {
											e.printStackTrace();
										} finally{
											try {
												ds.returnConnection(con);
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
	                        		}
	                        	}
	                        }
	                        return null;
	                    } else {
	                        throw new ClientProtocolException("Unexpected response status: " + status);
	                    }
	                }
	            };
	            httpclient.execute(httpget, responseHandler);
        	}
        } finally {
            httpclient.close();
        }
    }
}

class Response{
	private int total;
	private List<Item> items;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}

class Item {
	private int albumId ;
	public List<Actor> actors;
	private String itemTitle;
	private String alias;
	private  int hdType;
	private String playUrl;
	private String albumShortDesc;
	private String needMoney;
	private String picUrl_200x112;
	private String picUrl_200x300;
	private String picUrl_448x252;
	private String picUrl_448x672;
	private String title;
	private int playtimes;
	private int isSerial;
	private String payInfo;
	private String updateInfo;
	
	
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getHdType() {
		return hdType;
	}
	public void setHdType(int hdType) {
		this.hdType = hdType;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	public String getAlbumShortDesc() {
		return albumShortDesc;
	}
	public void setAlbumShortDesc(String albumShortDesc) {
		this.albumShortDesc = albumShortDesc;
	}
	public String getNeedMoney() {
		return needMoney;
	}
	public void setNeedMoney(String needMoney) {
		this.needMoney = needMoney;
	}
	public String getPicUrl_200x112() {
		return picUrl_200x112;
	}
	public void setPicUrl_200x112(String picUrl_200x112) {
		this.picUrl_200x112 = picUrl_200x112;
	}
	public String getPicUrl_200x300() {
		return picUrl_200x300;
	}
	public void setPicUrl_200x300(String picUrl_200x300) {
		this.picUrl_200x300 = picUrl_200x300;
	}
	public String getPicUrl_448x252() {
		return picUrl_448x252;
	}
	public void setPicUrl_448x252(String picUrl_448x252) {
		this.picUrl_448x252 = picUrl_448x252;
	}
	public String getPicUrl_448x672() {
		return picUrl_448x672;
	}
	public void setPicUrl_448x672(String picUrl_448x672) {
		this.picUrl_448x672 = picUrl_448x672;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPlaytimes() {
		return playtimes;
	}
	public void setPlaytimes(int playtimes) {
		this.playtimes = playtimes;
	}
	public int getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(int isSerial) {
		this.isSerial = isSerial;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public String getUpdateInfo() {
		return updateInfo;
	}
	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}
}

class Actor{
	private int id ;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
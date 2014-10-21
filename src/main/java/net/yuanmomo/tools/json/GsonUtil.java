package net.yuanmomo.tools.json;

import com.google.gson.Gson;

public class GsonUtil {
	private static Gson gson = new Gson();

	public static Gson getGson() {
		return gson;
	}

	public static void setGson(Gson gson) {
		GsonUtil.gson = gson;
	}
}

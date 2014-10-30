package net.yuanmomo.tools.util.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;



public class ApplePush {
	private static Logger logger = LoggerFactory.getLogger(ApplePush.class);
	public static void main(String[] args) {
		send("/Users/bianzhifu/Documents/workspace_new/testdoc/aps_development.p12",
				"bianzhifu", "理工男fuck",
				"f9ecc60e7e8bd308177e6269fe624b74bbd9fbca1674866e8bfd0b577f044445","afasd");
	}

	public static void send(String path, String passowrd, String content,
			String deviceToken,String idfa) {
		try {
			ApnsService service = APNS.newService().withCert(path, passowrd)
					.withSandboxDestination().build();
			String payload = APNS.newPayload().alertBody(content).sound("default")
					.build();
			service.push(deviceToken, payload);
			logger.info("推送成功" + "\t" + content  + "\t" + idfa);
		} catch (Exception e) {
			logger.error("推送失败" + "\t" + content  + "\t" + idfa);
		}
	}
}

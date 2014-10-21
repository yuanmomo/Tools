package net.yuanmomo.tools.util.push;



public class ApplePush {
    public static void send(String certificatePath,String certificatePassword,
    		String content,String deviceToken,String device){
//    	ApnsService service = APNS.newService()
//    		    .withCert(certificatePath, certificatePassword)
//    		    .withSandboxDestination().build();
//    	
//    	
//    	String payload = APNS.newPayload()
//                .badge(1)
//                .customField("secret", "what do you think?")
//                .localizedKey("GAME_PLAY_REQUEST_FORMAT")
//                .localizedArguments("Jenna", "Frank")
//                .actionKey("Play").build();
//
//    	String token = "fedfbcfb....";
//    	service.push(token, payload);
//    	
//    	Map<String, Date> inactiveDevices = service.getInactiveDevices();
//    	for (String deviceToken : inactiveDevices.keySet()) {
//    	    Date inactiveAsOf = inactiveDevices.get(deviceToken);
//    	}
    }
}

package com.pcm.mina.service.handler;

import com.pcm.mina.service.RequestHandler;
import com.pcm.mina.service.session.DefaultSessionManager;
import com.pcm.mina.service.session.PcmSession;
import com.pcm.util.ContextHolder;
import org.apache.log4j.Logger;

/**
 * @author ZERO
 * @Description  打开消息
 */ 
public class CloseHandler implements RequestHandler {

	protected final Logger logger = Logger.getLogger(CloseHandler.class);
	public String[] process(PcmSession ios, String[] message) {
		String[] reply = new String[2];
		String account= ios.getAccount();
		DefaultSessionManager sessionManager=(DefaultSessionManager) ContextHolder.getBean("PcmSessionManager");
		PcmSession session=sessionManager.getSession(account);
		if(session !=null){
			if (message[1].equals("ok"))
			{
				logger.info("推送的关闭消息是: ok");
			}
//			String[] rb = new String[2];
//			rb[0] = "close";
//			rb[1] = "0";
//			session.write(rb); //转发获取的消息
//			logger.info("推送的消息是:"+reply.toString());
		}else{
//			reply.setCode(Message.ReturnCode.CODE_403);
//			reply.setMessage("推送失败");
		}
		return reply;
	}
}
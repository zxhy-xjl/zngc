package com.pcm.mina.service.handler;

import com.pcm.mina.service.RequestHandler;
import com.pcm.mina.service.session.DefaultSessionManager;
import com.pcm.mina.service.session.PcmSession;
import com.pcm.util.ContextHolder;
import net.chenlin.dp.watersaving.service.FlushRecordService;
import org.apache.log4j.Logger;

/**
 * @author ZERO
 * @Description  开关状态（已关闭）
 */ 
public class ClosedHandler implements RequestHandler {

	protected final Logger logger = Logger.getLogger(ClosedHandler.class);
	public String[] process(PcmSession ios, String[] message) {
		String[] reply = new String[2];
		String account= ios.getAccount();
		logger.info("已关闭:opened" + account);
		DefaultSessionManager sessionManager=(DefaultSessionManager) ContextHolder.getBean("PcmSessionManager");
		PcmSession session=sessionManager.getSession(account);
		logger.info("已关闭:session" + session !=null);
		if(session !=null){
			FlushRecordService flushRecordService = (FlushRecordService) ContextHolder.getBean("flushRecordService");
			flushRecordService.saveClosedFlish(account,message);
//			String[] rb = new String[2];
//			rb[0] = "close";
//			rb[1] = "0";
//			session.write(rb); //转发获取的消息
			logger.info("接收的消息是:closeed");
		}else{
//			reply.setCode(Message.ReturnCode.CODE_403);
//			reply.setMessage("推送失败");
		}
		return reply;
	}
}
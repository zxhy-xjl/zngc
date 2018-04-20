package com.pcm.mina.service.handler;

import java.net.InetAddress;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pcm.mina.service.RequestHandler;
import com.pcm.mina.service.model.Message;
import com.pcm.mina.service.session.DefaultSessionManager;
import com.pcm.mina.service.session.PcmSession;
import com.pcm.util.ContextHolder;
 
/**
 * @author ZERO
 * @Description  账号绑定实现
 */ 
public class BindHandler implements RequestHandler {
	protected final Logger logger = Logger.getLogger(BindHandler.class);
	public String[] process(PcmSession newSession, String[] message) {
		String[] reply = new String[2];
		DefaultSessionManager sessionManager= ((DefaultSessionManager) ContextHolder.getBean("PcmSessionManager"));
		try { 
			String account = message[1];
			newSession.setAccount(account);
			newSession.setMessage(message[0]);
			newSession.setGid(UUID.randomUUID().toString());
			newSession.setHost(InetAddress.getLocalHost().getHostAddress());
            //第一次设置心跳时间为登录时间
			newSession.setBindTime(System.currentTimeMillis());
			newSession.setHeartbeat(System.currentTimeMillis());
			/**
			 * 由于客户端断线服务端可能会无法获知的情况，客户端重连时，需要关闭旧的连接
			 */
			PcmSession oldSession  = sessionManager.getSession(account);
            //如果是账号已经在另一台终端登录。则让另一个终端下线
			if(oldSession!=null&&!oldSession.equals(newSession))
			{
				oldSession.removeAttribute(Message.SESSION_KEY);
//				String[] rb = new String[2];
//				rb[0] = "close";
//				rb[1] = "ok";
				if(!oldSession.isLocalhost())
				{
					/*
					判断当前session是否连接于本台服务器，如不是发往目标服务器处理
					MessageDispatcher.execute(rb, oldSession.getHost());
					*/
				}else
				{
					System.out.println("关闭oldSession");
					oldSession.write("关闭oldSession");
					oldSession.close(true);
					oldSession = null;
				}
				oldSession = null;
			}
			if(oldSession==null)
			{
				sessionManager.addSession(account, newSession);
			}
			reply[0] = "init";
			reply[1] = "ok";
		} catch (Exception e) {
			//Message.ReturnCode.CODE_500;
			e.printStackTrace();
		}
		logger.debug("绑定账号:" + message[1]);
		return reply;
	}
	
}
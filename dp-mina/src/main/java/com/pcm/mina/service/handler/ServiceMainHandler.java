package com.pcm.mina.service.handler;

import java.net.InetSocketAddress;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;
import com.pcm.mina.service.RequestHandler;
import com.pcm.mina.service.model.Message;
import com.pcm.mina.service.model.SentBody;
import com.pcm.mina.service.session.PcmSession;

/**
 * @author ZERO
 * @Description  I/O 处理器 客户端请求的入口，所有请求都首先经过它分发处理 业务逻辑实现
 */ 

@Component("sercixeMainHandler")
public class ServiceMainHandler extends IoHandlerAdapter{
	protected final Logger logger = Logger.getLogger(ServiceMainHandler.class);
	
    //本地handler请求
	private HashMap<String, RequestHandler> handlers = new HashMap<String, RequestHandler>();
			
	//出错时
	@Override
	public void exceptionCaught(IoSession session, Throwable cause){
		logger.error("exceptionCaught()... from "+session.getRemoteAddress());
		logger.error(cause);
		cause.printStackTrace();
	}
	
	//接收到消息时
	@Override
	public void messageReceived(IoSession iosession,Object message){		
		logger.info("服务端接收到的消息..."+message.toString());
		String[] rb = new String[2];;
		PcmSession session = new PcmSession(iosession);
		String[] cmdData = message.toString().split(":");
		if (cmdData.length>1) {
			String key = cmdData[0];
			String cmd = cmdData[1];

			if("quit".equals(key)){ //服务器断开的条件
				try {
					sessionClosed(iosession);
				} catch (Exception e) {
					//rb = Message.ReturnCode.CODE_500;
					e.printStackTrace();
				}
			}else{
				//根据key的不同调用不同的handler
				RequestHandler rhandler=handlers.get(key);
				if(rhandler==null){//如果没有这个handler
//					rb.setCode(Message.ReturnCode.CODE_405);
//					rb.setMessage("服务端未定义!");
					//rb = Message.ReturnCode.CODE_405;
				}else{//有的话
					rb = rhandler.process(session, cmdData);
				}

				if (key.equals("init")){
					if(rb !=null){
						session.write(StringUtils.join(rb,":"));
//						session.write("ok");
						logger.info("服务端发送的消息: " + "ok");
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						rb[0] = "open";
//						rb[1] = "0";
//						session.write(StringUtils.join(rb,":"));
//						logger.info("服务端发送的消息: " + StringUtils.join(rb,":"));
					}
				}
			}
		} else {
			if (cmdData[0].equals("ok")){

			}
		}
	}
	
	//发送消息
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
   //	session.close(); //发送成功后主动断开与客户端的连接 实现短连接
       logger.info("服务端发送信息成功...");
	    }
	
	//建立连接时
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		 	InetSocketAddress sa=(InetSocketAddress)session.getRemoteAddress();
			String address=sa.getAddress().getHostAddress(); //访问的ip
			session.setAttribute("address", address);
			//将连接的客户端ip保存到map集合中
			SentBody body=new SentBody();
			body.put("address", address);
			logger.info("访问的ip:"+address);
	    }
	
	//关闭连接时   
	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
			PcmSession session=new PcmSession(iosession);
			logger.debug("sessionClosed()... from "+session.getRemoteAddress());
			try {
				RequestHandler hand=handlers.get("close");
				if(hand !=null && session.containsAttribute(Message.SESSION_KEY)){
					hand.process(session, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close(true);  
			logger.info("连接关闭");
	    }

	//空闲时
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.debug("sessionIdle()... from "+session.getRemoteAddress());
	    }
	
	//打开连接时   
	@Override
	 public void sessionOpened(IoSession session) throws Exception {
		 	logger.info("开启连接...");
	    }
	
	public HashMap<String, RequestHandler> getHandlers() {
		return handlers;
	}
	public void setHandlers(HashMap<String, RequestHandler> handlers) {
		this.handlers = handlers;
	}
	 
} 

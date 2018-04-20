package com.pcm.mina.client.MinaDemo;

import java.net.InetSocketAddress;
import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import com.pcm.mina.service.model.SentBody;


/**
 * @author ZERO
 * @Description mina 客户端
 */
	public class MinaClient {
	    private static Logger logger = Logger.getLogger(MinaClient.class);
	    private static String HOST = "47.98.200.5";
	    private static int PORT = 9900;
	    private static  IoConnector connector=new NioSocketConnector();
	    private static   IoSession session;
	    public static IoConnector getConnector() {
			return connector;
		}

		public static void setConnector(IoConnector connector) {
			MinaClient.connector = connector;
		}

		/* 
	    * 测试服务端与客户端程序！
	    a. 启动服务端，然后再启动客户端
	    b. 服务端接收消息并处理成功;
	    */
	    @SuppressWarnings("deprecation")
		public static void main(String[] args) {
	    	   // 设置链接超时时间
	        connector.setConnectTimeout(30000);
	        // 添加过滤器  可序列话的对象 
	        connector.getFilterChain().addLast(
	                "codec",
	                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
	        // 添加业务逻辑处理器类
	        connector.setHandler(new MinaClientHandler());
	        ConnectFuture future = connector.connect(new InetSocketAddress(
	                HOST, PORT));// 创建连接
	        future.awaitUninterruptibly();// 等待连接创建完成
	        session = future.getSession();// 获得session
	        
	     	bindstart();
	   // 	pushstart();
	    }
	    
	    public static void bindstart(){
	    	logger.info("客户端A绑定服务端");
	        try {
	            SentBody sy=new SentBody();
	            sy.put("message", "这是个测试账号");
	            sy.put("account", "123456");
	            sy.setKey("client_bind");
	            session.write(sy);// 发送消息
	            System.out.println("客户端A与服务端建立连接成功...发送的消息为:"+sy);
	      //      logger.info("客户端A与服务端建立连接成功...发送的消息为:"+sy);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            logger.error("客户A端链接异常...", e);
	        }
	        session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
	        connector.dispose();
	    }
	    
	    public static void pushstart(){
	    	logger.info("客户端B请求服务端推送");
	       try {
	           SentBody sy=new SentBody();
	           sy.put("message", "推送测试消息");
	           sy.put("account", "123456"); //账号
	           sy.setKey("client_push");
	           logger.info("SentBody:"+sy);
	           session.write(sy);// 发送消息
	           logger.info("客户端B与服务端建立连接成功...发送的消息为:"+sy);
	       } catch (Exception e) {
	       	e.printStackTrace();
	           logger.error("客户B端链接异常...", e);
	       }
	       session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
	       connector.dispose();
	   }
}

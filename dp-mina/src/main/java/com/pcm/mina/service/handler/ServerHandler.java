package com.pcm.mina.service.handler;

import com.pcm.mina.service.RequestHandler;
import com.pcm.mina.service.session.PcmSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;


public class ServerHandler extends IoHandlerAdapter {
    protected final Logger logger = Logger.getLogger(ServerHandler.class);
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public  void messageReceived(IoSession session, Object message)
            throws Exception {
        System.out.println(System.currentTimeMillis());
        session.write(message);

        logger.info("服务端接收到的消息..."+message.toString());
        String[] rb = new String[2];;
        String[] cmdData = message.toString().split(":");
        if (cmdData.length>1) {
            String key = cmdData[0];
            String cmd = cmdData[1];

            if (key.equals("init")) {
                if (rb != null) {
                    session.write("ok");
                    logger.info("服务端发送的消息: " + "ok");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rb[0] = "open";
                    rb[1] = "0";
                    session.write(StringUtils.join(rb, ":"));
                    logger.info("服务端发送的消息: " + StringUtils.join(rb, ":"));
                }
            }
        } else {
            if (cmdData[0].equals("ok")){

            }
        }
    }
    //System.out.println(message.toString());
    //session.write(message);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("创建一个连接！");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub

    }

}

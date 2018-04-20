package com.pcm.mina.service.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

public class LCLTextLineCodecFactory implements ProtocolCodecFactory {

    private Charset charset; // 编码格式
    private String delimiter; // 文本分隔符

    public LCLTextLineCodecFactory(Charset charset, String delimiter) {
        this.charset = charset;
        this.delimiter = delimiter;
    }

    //读取信息执行
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return new LCLTextLineCodecDecoder(charset, delimiter);
    }

    //发送编写信息执行
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new LCLTextLineCodecEncoder(charset, delimiter);
    }
}

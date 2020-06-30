package com.nantian.jms;

import ch.qos.logback.core.util.FileUtil;
import com.nantian.configuration.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname SendMessage
 * @Description TODO
 * @Date 2020/6/29 17:09
 * @Created by Administrator
 */

@Component
public class SendMessage {
    @Autowired
    JmsOperations jmsOperations;

    @Autowired
    private JmsConfiguration jmsConfiguration;

    //@PostConstruct在服务器加载Servle的时候运行，并且只会被服务器执行一次, @PreDestroy在destroy()方法执行执行之后执行
    @PostConstruct
    public void send() throws Exception {
        System.out.println("开始发送消息");
        String messageType = jmsConfiguration.getMessageType();
        if ("message".equals(messageType)) {
            String message = jmsConfiguration.getMessage();
            System.out.println("put message:" + message);
//          jmsOperations.convertAndSend(jmsConfiguration.getQueue(), messages);
            jmsOperations.convertAndSend("queue:///"+jmsConfiguration.getQueue()+"?targetClient=1",message);
        } else{
            String messagePath = jmsConfiguration.getMessagePath();
            File file = new File(messagePath);
            File[] files = file.listFiles();
            for (File tempFile : files) {
                System.out.println("deal file:" + tempFile.getName());
                if (tempFile.isFile()) {
                    String message = readFileContext(tempFile);
//                    System.out.println("put message:" + message);
                    jmsOperations.convertAndSend("queue:///"+jmsConfiguration.getQueue()+"?targetClient=1",message);
                }

            }
        }

    }

    private String readFileContext(File file) throws Exception{
        InputStream is = null;
        BufferedInputStream bis = null;
        Reader instream = null;
        is = new FileInputStream(file);
        bis = new BufferedInputStream(is);
        StringBuilder sb = new StringBuilder();
        instream = new InputStreamReader(bis, "UTF-8");
        char[] chars = new char[1024];
        int len = 0;
        while ((len = instream.read(chars)) >= 0) {
            sb.append(new String(chars, 0, len));
        }
        return sb.toString();
    }
}

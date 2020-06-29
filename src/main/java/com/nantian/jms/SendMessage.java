package com.nantian.jms;

import com.nantian.configuration.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    public void send(){
        System.out.println("开始发送消息");
        String messages = jmsConfiguration.getMessages();
//        while (iterator.hasNext()) {
//            System.out.println("put message:" + iterator.next());
            jmsOperations.convertAndSend(jmsConfiguration.getQueue(), messages);
//        }
    }
}

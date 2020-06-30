package com.nantian.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @Classname ReceiveMessage
 * @Description TODO
 * @Date 2020/6/29 20:57
 * @Created by Administrator
 */
//@Component
public class ReceiveMessage extends MessageListenerAdapter {
    @Autowired
    JmsOperations jmsOperations;

    @Override
//    @JmsListener(destination = "PBC.C1010246000016.BATCH.IN")
    public void onMessage(Message message) {
        String messageBody = new String(message.toString());
        System.out.println("成功监听Q1消息队列，传来的值为:" + messageBody);
        try {
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


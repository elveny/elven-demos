/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.config.rabbitmq;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author wei.tang
 * @Filename KryoMessageConvertor.java
 * @description 使用kryo作为序列化工具的消息转换类
 * @Version 1.0
 * @History <li>Author: wei.tang</li>
 * <li>Date: 2017/1/10 15:10</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class KryoMessageConvertor implements MessageConverter {
    /**
     * Convert a Java object to a Message.
     *
     * @param object            the object to convert
     * @param messageProperties The message properties.
     * @return the Message
     * @throws MessageConversionException in case of conversion failure
     */
    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        Output output = new Output(2048,-1);
        try {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(object.getClass(), new JavaSerializer());
            kryo.writeClassAndObject(output, object);
        }finally {
            output.close();
        }
        Message message = new Message(output.toBytes(),messageProperties);
        return message;
    }

    /**
     * Convert from a Message to a Java object.
     *
     * @param message the message to convert
     * @return the converted Java object
     * @throws MessageConversionException in case of conversion failure
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        byte[] bytes = message.getBody();
        if (bytes == null){
            return null;
        }
        Kryo kryo = new Kryo();
        Input input = new Input(bytes);
        return kryo.readClassAndObject(input);
    }
}

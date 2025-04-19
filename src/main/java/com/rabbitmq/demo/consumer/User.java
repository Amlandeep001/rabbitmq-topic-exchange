package com.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.demo.config.MessagingConfig;
import com.rabbitmq.demo.dto.OrderStatus;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class User
{
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus)
	{
		log.info("Message recieved from queue : {}", orderStatus);
	}
}
